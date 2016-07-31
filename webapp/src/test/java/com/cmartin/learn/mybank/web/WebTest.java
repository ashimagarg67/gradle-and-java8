package com.cmartin.learn.mybank.web;

import com.cmartin.learn.mybank.api.BankService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class WebTest {

    @Mock
    protected BankService bankApi;
    protected FilterManager filterManger = new FilterManager();

    //    @Autowired
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
    public void test() throws Exception {
        this.mockMvc.perform(get("/accounts"))
                .andDo(print());
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
