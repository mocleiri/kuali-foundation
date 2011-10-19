package org.kuali.student.lum.ui.selenium;

import junit.framework.Assert;

import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class Google1Test {

    @Test
    public void simpleTest() throws Exception {
        Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.google.com");
        selenium.open("/");
        selenium.waitForPageToLoad("10000");
        Assert.assertEquals("Google", selenium.getTitle());
        selenium.stop();
    }
}
