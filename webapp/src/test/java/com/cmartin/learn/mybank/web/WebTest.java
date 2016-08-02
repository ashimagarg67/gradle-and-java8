package com.cmartin.learn.mybank.web;

import com.cmartin.learn.mybank.api.BankService;
import com.cmartin.learn.mybank.api.UserFilter;
import com.cmartin.learn.mybank.test.TestUtils;
import com.cmartin.learn.mybank.web.controller.BankController;
import com.cmartin.learn.mybank.web.controller.FilterManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.ArgumentMatchers.anyCollectionOf;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class WebTest {
    protected static final ResultMatcher statusOk = status().isOk();

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
        this.mockMvc.perform(get("/accounts"))
                .andDo(print())
                .andExpect(statusOk);
    }


    @Test
    public void testGetUsers() throws Exception {
        UserFilter userFilter = this.filterManger.buildUserFilter();

        when(this.bankApi.getUsers(any(UserFilter.class)))
                .thenReturn(TestUtils.createUsers(2));

        this.mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(statusOk);

//        verify(this.bankApi).getUsers(any(UserFilter.class));
    }

    @Test
    public void testGetContracts() throws Exception {
        this.mockMvc.perform(get("/contracts"))
                .andDo(print())
                .andExpect(statusOk);
    }

    /**
     * Check test-utils project dependencies
     */
    @Test
    public void testOne() {
        TestUtils.createEntities(7);
        Assert.assertTrue(true);
    }
}
