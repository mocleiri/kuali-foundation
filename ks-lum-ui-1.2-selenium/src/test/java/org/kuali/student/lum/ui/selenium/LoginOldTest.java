package org.kuali.student.lum.ui.selenium;

import junit.framework.Assert;

import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class LoginOldTest {

    @Test
    public void simpleTest() throws Exception {
        Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://dev.ks.kuali.org");
        selenium.open("/login.jsp");
        selenium.waitForPageToLoad("10000");
        Assert.assertEquals("Google", selenium.getTitle());
        selenium.stop();
    }
}
