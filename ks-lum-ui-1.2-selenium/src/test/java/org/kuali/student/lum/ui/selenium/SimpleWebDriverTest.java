package org.kuali.student.lum.ui.selenium;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SimpleWebDriverTest {

    @Test
    public void googleTest() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");
        String title = driver.getTitle();
        Assert.assertEquals("Google", title);
        driver.quit();
    }
}
