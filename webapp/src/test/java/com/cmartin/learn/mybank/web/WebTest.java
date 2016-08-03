package com.cmartin.learn.mybank.web;

import com.cmartin.learn.mybank.api.AccountFilter;
import com.cmartin.learn.mybank.api.BankService;
import com.cmartin.learn.mybank.api.ContractFilter;
import com.cmartin.learn.mybank.api.UserFilter;
import com.cmartin.learn.mybank.test.TestUtils;
import com.cmartin.learn.mybank.web.controller.BankController;
import com.cmartin.learn.mybank.web.controller.FilterManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class WebTest {

    protected static final ResultMatcher statusOk = status().isOk();
    protected static final ResultMatcher contentTypeJson = content()
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    public static final int COLLECTION_SIZE_5 = 5;

    @Mock
    protected BankService bankApi;
    protected FilterManager filterManger = new FilterManager();

    protected BankController bankController;

    private MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.bankController = new BankController(bankApi);
        this.bankController.setFilterManager(filterManger);

        converter.setPrettyPrint(true);
        this.mockMvc = standaloneSetup(this.bankController)
                .setMessageConverters(converter)
                .build();
    }

    @Test
    public void testGetAccounts() throws Exception {
        when(this.bankApi.getAccounts(any(AccountFilter.class)))
                .thenReturn(TestUtils.createAccounts(COLLECTION_SIZE_5));

        this.mockMvc.perform(get("/accounts"))
                .andDo(print())
                .andExpect(statusOk)
                .andExpect(contentTypeJson)
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(lessThanOrEqualTo(COLLECTION_SIZE_5))));

        verify(this.bankApi).getAccounts(any(AccountFilter.class));
    }


    @Test
    public void testGetUsers() throws Exception {
        when(this.bankApi.getUsers(any(UserFilter.class)))
                .thenReturn(TestUtils.createUsers(COLLECTION_SIZE_5));

        this.mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(statusOk)
                .andExpect(contentTypeJson)
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(lessThanOrEqualTo(COLLECTION_SIZE_5))));

        verify(this.bankApi).getUsers(any(UserFilter.class));
    }

    @Test
    public void testGetContracts() throws Exception {
        when(this.bankApi.getContracts(any(ContractFilter.class)))
                .thenReturn(TestUtils.createContracts(COLLECTION_SIZE_5));

        this.mockMvc.perform(get("/contracts"))
                .andDo(print())
                .andExpect(statusOk)
                .andExpect(contentTypeJson)
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(lessThanOrEqualTo(COLLECTION_SIZE_5))));

        verify(this.bankApi).getContracts(any(ContractFilter.class));
    }

}
