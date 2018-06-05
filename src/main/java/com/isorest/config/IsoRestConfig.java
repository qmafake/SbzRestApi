package com.isorest.config;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package zw.co.stewardbank.instantaccount.config;
//
///**
// *
// * @author Artwell Mamvura
// */
////public class IsoRestConfig {
////    
////}
//import java.io.Serializable;
//import java.util.Properties;
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//import org.apache.commons.dbcp.BasicDataSource;
//import org.hibernate.ejb.HibernatePersistence;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
///**
// *
// * @author Artwell Mamvura
// */
//@Configuration
//@ComponentScan(basePackages = {"zw.co.stewardbank.dao",
//    "zw.co.stewardbank.service"})
//@PropertySource("classpath:zw/co/stewardbank/config/jdbc.properties")
//@EnableTransactionManagement
//public class IsoRestConfig implements Serializable {
//
//    @Resource
//    Environment environment;
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//
//        LocalContainerEntityManagerFactoryBean entityManagerFactory
//                = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setDataSource(dataSource());
//        entityManagerFactory.setPackagesToScan(new String[]{"zw.co.stewardbank.domain"});
//        entityManagerFactory.setPersistenceProviderClass(HibernatePersistence.class);
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//        jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql", Boolean.class));
//        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
//        jpaProperties.put("hibernate.connection.tinyInt1isBit", true);
//        jpaProperties.put("hibernate.connection.transformedBitIsBoolean", true);
//        entityManagerFactory.setJpaProperties(jpaProperties);
//        return entityManagerFactory;
//
//    }
//
//    @Bean(destroyMethod = "close")
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
//        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
//        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
//        return dataSource;
//    }
//}
