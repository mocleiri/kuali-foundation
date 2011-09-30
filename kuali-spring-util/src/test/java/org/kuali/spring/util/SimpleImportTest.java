package org.kuali.spring.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SimpleImportTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void importTest() {
        String name = (String) applicationContext.getBean("name");
        System.out.println(name);
    }
}
