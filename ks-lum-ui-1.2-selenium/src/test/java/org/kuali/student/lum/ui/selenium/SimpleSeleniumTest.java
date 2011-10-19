package org.kuali.student.lum.ui.selenium;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class SimpleSeleniumTest {
    Selenium selenium;

    @Before
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.google.com");
        selenium.start();
    }

    @Test
    public void googleTest() throws Exception {
        selenium.open("/");
        selenium.waitForPageToLoad("10000");
        Assert.assertEquals("Google", selenium.getTitle());
        selenium.stop();
    }
}
