package com.cmartin.learn.mybank.web

import com.cmartin.learn.mybank.api.BankService
import com.cmartin.learn.mybank.test.TestUtils
import com.cmartin.learn.mybank.web.controller.BankController
import com.cmartin.learn.mybank.web.controller.FilterManager
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification
import spock.lang.Subject

import static com.cmartin.learn.mybank.test.TestUtils.*
import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.lessThanOrEqualTo
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class ControllerTest extends Specification {

    def statusOk = status().isOk()
    def contentTypeJson = content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
    def MockMvc mockMvc
    def converter = new MappingJackson2HttpMessageConverter()

    @Subject
    BankController bankController

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
        def UUID accountId = UUID.randomUUID()

        and:
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
        ResultActions result = mockMvc.perform(get("/accounts"))
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

    def "create account"() {
        given:
        def UUID accountId = UUID.randomUUID()
        def accountJson = TestUtils.objectToJson(TestUtils.newAccountDto(
                accountId, "account-alias", makePseudoIBANAccount(), makeBigDecimal(100d)))

        when:
        ResultActions result = mockMvc.perform(post("/accounts")
                .content(accountJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())

        then:
        with(result) {
            result.andExpect(status().isCreated())
        }

    }
}
