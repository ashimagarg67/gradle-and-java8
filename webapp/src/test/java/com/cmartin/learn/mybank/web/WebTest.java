package com.cmartin.learn.mybank.web;

import com.cmartin.learn.mybank.test.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class WebTest {
    /**
     * Rigourous Test :-)
     */
    @Test
    public void testWebAPp() {
        Assert.assertTrue(true);
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
