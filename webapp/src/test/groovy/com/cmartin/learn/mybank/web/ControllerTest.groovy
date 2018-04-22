package com.cmartin.learn.mybank.web

import com.cmartin.learn.mybank.api.BankService
import com.cmartin.learn.mybank.api.ServiceException
import com.cmartin.learn.mybank.test.TestUtils
import com.cmartin.learn.mybank.web.controller.BankController
import com.cmartin.learn.mybank.web.controller.FilterManager
import io.vavr.control.Try
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification
import spock.lang.Subject

import static com.cmartin.learn.mybank.test.TestUtils.*
import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.lessThanOrEqualTo
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class ControllerTest extends Specification {

    // HTTP codes
    def statusOk = status().isOk()
    def statusCreated = status().isCreated()
    def statusNoContent = status().isNoContent()
    def statusNotFound = status().isNotFound()
    def statusConflict = status().isConflict()

    // encoding
    def contentTypeJson = content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)

    // business data
    def accountId = UUID.randomUUID()

    @Subject
    private BankController bankController

    def converter = new MappingJackson2HttpMessageConverter()
    private MockMvc mockMvc

    def bankService = Stub(BankService)
    def filterManager = Mock(FilterManager)

    def setup() {
        bankController = new BankController(bankService)
        bankController.setFilterManager(filterManager)
        converter.setPrettyPrint(true)
        mockMvc = standaloneSetup(bankController)
                .setMessageConverters(converter)
                .build()
    }

    def "get account"() {
        given: "an account identifier"
        bankService.getAccount(_) >> Optional.of(TestUtils.newAccountDto(
                accountId, "account-alias", makePseudoIBANAccount(), makeBigDecimal(100d)))

        when:
        ResultActions result = mockMvc.perform(get("/accounts/{accountId}", accountId))
                .andDo(print())

        then:
        with(result) {
            result.andExpect(statusOk)
                    .andExpect(contentTypeJson)
        }
    }

    def "get account list"() {
        given:
        bankService.getAccounts(_) >> TestUtils.createAccounts(COLLECTION_SIZE_5)

        when:
        ResultActions result = mockMvc.perform(get("/accounts/"))
                .andDo(print())

        then:
        with(result) {
            result.andExpect(statusOk)
                    .andExpect(contentTypeJson)
                    .andExpect(jsonPath("\$").isNotEmpty())
                    .andExpect(jsonPath("\$.accounts").isArray())
                    .andExpect(jsonPath("\$.accounts", hasSize(lessThanOrEqualTo(COLLECTION_SIZE_5))))
                    .andExpect(jsonPath("\$.localDate").isNotEmpty())
        }

        1 * filterManager.buildAccoutFilter()
    }

    def "create account 'created"() {
        given:
        def accountDto = TestUtils.newAccountDto(accountId, "account-alias", makePseudoIBANAccount(), makeBigDecimal(100d))

        and:
        def accountJson = TestUtils.objectToJson(
                TestUtils.newAccountDto("account-alias", makePseudoIBANAccount(), makeBigDecimal(100d)))

        and:
        bankService.createAccount(_) >> Try.success(accountDto)

        when:
        def result = mockMvc.perform(post("/accounts")
                .content(accountJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())

        then:
        with(result) {
            result.andExpect(statusCreated)
        }
    }

    def "create account 'conflict'"() {
        given:
        def accountJson = TestUtils.objectToJson(
                TestUtils.newAccountDto("account-alias", makePseudoIBANAccount(), makeBigDecimal(100d)))

        and:
        bankService.createAccount(_) >> Try.failure(new ServiceException("the account already exists"))

        when:
        def result = mockMvc.perform(post("/accounts")
                .content(accountJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())

        then:
        with(result) {
            result.andExpect(statusConflict)
        }
    }

    def "update account 'no content"() {
        given:
        def accountDto = TestUtils.newAccountDto(accountId, "account-alias", makePseudoIBANAccount(), makeBigDecimal(300d))

        and:
        def accountJson = TestUtils.objectToJson(accountDto)

        and:
        bankService.findAccountById(_) >> Try.success(accountId)

        and:
        bankService.updateAccount(_) >> Try.success(accountId)

        when:
        def result = mockMvc.perform(put("/accounts/{account}", accountId)
                .content(accountJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())

        then:
        with(result) {
            result.andExpect(statusNoContent)
        }
    }

    def "update account 'not found"() {
        given:
        def accountJson = TestUtils.objectToJson(
                TestUtils.newAccountDto(accountId, "account-alias", makePseudoIBANAccount(), makeBigDecimal(300d)))

        and:
        bankService.findAccountById(_) >> Try.failure(new ServiceException("Entity not found"))

        when:
        def result = mockMvc.perform(put("/accounts/{account}", accountId)
                .content(accountJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())

        then:
        with(result) {
            result.andExpect(statusNotFound)
        }
    }

    def "update account 'conflict"() {
        given:
        def accountDto = TestUtils.newAccountDto(accountId, "account-alias", makePseudoIBANAccount(), makeBigDecimal(300d))

        and:
        def accountJson = TestUtils.objectToJson(accountDto)

        and:
        bankService.findAccountById(_) >> Try.success(accountId)

        and:
        bankService.updateAccount(_) >> Try.failure(new ServiceException("Constraint violation"))

        when:
        def result = mockMvc.perform(put("/accounts/{account}", accountId)
                .content(accountJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())

        then:
        with(result) {
            result.andExpect(statusConflict)
        }
    }

    def "delete account 'no content"() {
        given:
        bankService.deleteAccount(_) >> Try.success(accountId)

        when:
        def result = mockMvc.perform(delete("/accounts/{accountId}", accountId))
                .andDo(print())

        then:
        with(result) {
            result.andExpect(statusNoContent)
        }
    }

    def "delete account 'not found"() {
        given:
        bankService.deleteAccount(_) >> Try.failure(new ServiceException("the account does not exist"))

        when:
        def result = mockMvc.perform(delete("/accounts/{accountId}", accountId))
                .andDo(print())

        then:
        with(result) {
            result.andExpect(statusNotFound)
        }
    }


}
