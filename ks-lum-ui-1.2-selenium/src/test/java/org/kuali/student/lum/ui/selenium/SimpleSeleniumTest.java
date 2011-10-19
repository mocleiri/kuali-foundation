package org.kuali.student.lum.ui.selenium;

import junit.framework.Assert;

import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class SimpleSeleniumTest {

    @Test
    public void googleTest() throws Exception {
        Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.google.com");
        selenium.start();
        selenium.waitForPageToLoad("30000");
        Assert.assertEquals("Google", selenium.getTitle());
        selenium.stop();
    }
}
