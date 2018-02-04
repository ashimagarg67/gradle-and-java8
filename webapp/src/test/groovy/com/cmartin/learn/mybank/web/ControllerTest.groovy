package com.cmartin.learn.mybank.web

import com.cmartin.learn.mybank.api.BankService
import com.cmartin.learn.mybank.test.TestUtils
import com.cmartin.learn.mybank.web.controller.BankController
import com.cmartin.learn.mybank.web.controller.FilterManager
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.ResultMatcher
import spock.lang.Specification
import spock.lang.Subject

import static com.cmartin.learn.mybank.test.TestUtils.*
import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.lessThanOrEqualTo
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class ControllerTest extends Specification {

    def ResultMatcher statusOk = status().isOk()
    def ResultMatcher contentTypeJson = content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
    def MockMvc mockMvc
    def MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter()

    @Subject
    BankController bankController

    def BankService bankService = Stub()
    def FilterManager filterManager = Mock()
    def UUID accountId = UUID.randomUUID()

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
}
