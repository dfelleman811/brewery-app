package com.revature.breweryapp.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitWebConfig(BasicWebTests.TestConfig.class)
public class BasicWebTests {

    @Configuration
    static class TestConfig {}

    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    public void webContextLoads() {
        assertNotNull(webApplicationContext);
    }
}
