//package com.cmartin.learn.mybank.web.configuration;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
///**
// * Created by cmartin on 17/07/16.
// */
//public class BankWebAppInitializer implements WebApplicationInitializer {
//
//    public static final String SERVLET_NAME = "dispatcher";
//    public static final int LOAD_ON_STARTUP = 1;
//    public static final String ROOT_MAPPING = "/";
//
//    @Override
//    public void onStartup(final ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext dispatcherContext =
//                new AnnotationConfigWebApplicationContext();
//        dispatcherContext.register(WebAppConfiguration.class);
//
//        ServletRegistration.Dynamic dispatcher =
//                servletContext.addServlet(SERVLET_NAME, new DispatcherServlet(dispatcherContext));
//        dispatcher.setLoadOnStartup(LOAD_ON_STARTUP);
//        dispatcher.addMapping(ROOT_MAPPING);
//    }
//
//}
