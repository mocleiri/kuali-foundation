package org.kuali.student.lum.ui.selenium.old;

import junit.framework.Assert;

import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class LoginTest {

    @Test
    public void loginTest() throws Exception {
        String baseUrl = System.getProperty("selenium.baseurl");
        Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", baseUrl);
        selenium.start();
        selenium.open("/login.jsp");
        selenium.type("name=j_username", "admin");
        selenium.type("name=j_password", "admin");
        selenium.click("css=input[type=\"submit\"]");
        selenium.waitForPageToLoad("10000");
        String title = selenium.getTitle();
        Assert.assertEquals("Kuali Student: Home", title);
        selenium.stop();
    }
}
