package com.revature.breweryapp.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(BasicTests.TestConfig.class)
public class BasicTests {
    @Configuration
    static class TestConfig {}

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        assertNotNull(applicationContext);
    }
}
