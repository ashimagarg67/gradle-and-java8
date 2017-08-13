package com.cmartin.learn.mybank.service;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.Assert;
import org.junit.Test;

/**
 */
public class TestUtilsTest {
    @Test
    public void testStringGenerator() {
        RandomStringGenerator g1 = new RandomStringGenerator.Builder()
                .withinRange('A', 'Z')
                .build();
        String result = g1.generate(2);

        Assert.assertFalse(result.isEmpty());
        Assert.assertTrue(result.length() == 2);
    }

    @Test
    public void testNumberGenerator() {
        RandomStringGenerator g1 = new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .build();
        String result = g1.generate(22);

        Assert.assertFalse(result.isEmpty());
        Assert.assertTrue(result.length() == 22);
    }

}
