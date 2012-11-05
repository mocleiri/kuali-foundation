package org.kuali.db.jdbc;

import java.util.Properties;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationTest {
    final Logger logger = LoggerFactory.getLogger(ApplicationTest.class);

    @Test
    public void appTest1() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("org/kuali/db/jdbc/dba.xml");
            showContext(context);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void appTest2() {
        String key = "kuali.jdbc.dba.properties";
        String location = "classpath:org/kuali/db/jdbc/dba-properties-test.xml";
        Properties systemProperties = System.getProperties();
        System.setProperty(key, location);
        ApplicationContext context = new ClassPathXmlApplicationContext("org/kuali/db/jdbc/dba-test.xml");
        showContext(context);
        systemProperties.remove(key);
    }

    protected void showContext(ApplicationContext context) {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            Object bean = context.getBean(name);
            logger.info(name + "=" + bean);
        }
    }

    protected void showProperties(ApplicationContext context) {
    }
}
