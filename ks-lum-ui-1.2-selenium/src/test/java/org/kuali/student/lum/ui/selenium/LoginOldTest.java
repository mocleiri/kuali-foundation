package org.kuali.student.lum.ui.selenium;

import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class LoginOldTest {

    @Test
    public void simpleTest() throws Exception {
        String baseUrl = System.getProperty("selenium.baseurl");
        Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", baseUrl);
        selenium.open("/login.jsp");
        selenium.type("name=j_username", "admin");
        selenium.type("name=j_password", "admin");
        selenium.waitForPageToLoad("10000");
        selenium.stop();
    }
}
