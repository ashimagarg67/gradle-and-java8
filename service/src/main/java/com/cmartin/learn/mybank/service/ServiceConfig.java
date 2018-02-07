package com.cmartin.learn.mybank.service;

import com.cmartin.learn.mybank.api.BankService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cmartin on 27/07/16.
 */
@Configuration
public class ServiceConfig {

    @Bean
    public BankService bankServiceImpl() {
        return new BankServiceImpl(null);
    }
}
