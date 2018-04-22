package com.cmartin.learn.mybank.service;

import org.apache.commons.text.RandomStringGenerator;

public class ServiceHelper {
    static String buildSeudoIBANString() {
        String letters = new RandomStringGenerator.Builder()
                .withinRange('A', 'Z')
                .build().generate(2);
        String numbers = new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .build().generate(22);

        return String.format("%s%s", letters, numbers);
    }

}
