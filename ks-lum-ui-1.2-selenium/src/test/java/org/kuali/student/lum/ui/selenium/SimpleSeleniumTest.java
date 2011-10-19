package org.kuali.student.lum.ui.selenium;

import junit.framework.Assert;

import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class SimpleSeleniumTest {

    protected Selenium createSeleniumClient(String url) throws Exception {
        return new DefaultSelenium("localhost", 4444, "*firefox", url);
    }

    @Test
    public void testSomethingSimple() throws Exception {
        Selenium selenium = createSeleniumClient("http://www.google.com");
        selenium.start();

        //
        // This is an exmaple of testing the Apache Geroniom Welcome page for specific text
        //

        selenium.click("link=JVM");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Welcome");
        selenium.waitForPageToLoad("30000");
        Assert.assertEquals("Geronimo Console", selenium.getTitle());
        Assert.assertEquals(
                "Welcome",
                selenium.getText("xpath=/html/body/table[@id='rootfragment']/tbody/tr[2]/td/table/tbody/tr[2]/td[4]/table/tbody/tr[1]/td/table/tbody/tr[1]/td/div/table/tbody/tr/td[2]/table/tbody/tr/td[1]/strong"));

        // Test help link
        selenium.click("link=help");
        selenium.waitForPageToLoad("30000");
        selenium.isTextPresent("This is the help for the Geronimo Administration Console Welcome.");

        selenium.stop();
    }
}
