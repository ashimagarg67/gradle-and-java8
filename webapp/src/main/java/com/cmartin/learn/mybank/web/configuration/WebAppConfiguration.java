//package com.cmartin.learn.mybank.web.configuration;
//
//
//import com.cmartin.learn.mybank.service.ServiceConfig;
//import com.cmartin.learn.mybank.web.controller.BankController;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
///**
// * Created by cmartin on 22/07/16.
// */
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackageClasses = {BankController.class, ServiceConfig.class})
//public class WebAppConfiguration implements WebMvcConfigurer {
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setPrettyPrint(true);
//        converters.add(converter);
//    }
//
//}