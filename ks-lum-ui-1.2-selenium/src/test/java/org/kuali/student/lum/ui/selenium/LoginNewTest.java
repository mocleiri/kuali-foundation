package org.kuali.student.lum.ui.selenium;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginNewTest {

    @Test
    public void simpleTest() {
        WebDriver driver = null;
        try {
            driver = new FirefoxDriver();
            driver.get("http://dev.ks.kuali.org/login.jsp");
            WebElement logonUserName = driver.findElement(By.id("j_username"));
            WebElement logonPassword = driver.findElement(By.id("j_password"));
            typeValue(logonUserName, "admin");
            typeValue(logonPassword, "admin");
            logonPassword.submit();
            Wait<WebDriver> wait = new WebDriverWait(driver, 10);
            ExpectedCondition<Boolean> condition = new TitlePresentCondition("Kuali Student: Home");
            boolean conditionMet = wait.until(condition);
            Assert.assertTrue(conditionMet);
        } finally {
            quitQuietly(driver);
        }
    }

    private void quitQuietly(WebDriver driver) {
        if (driver == null) {
            return;
        } else {
            driver.quit();
        }
    }

    private void typeValue(final WebElement webElement, CharSequence... keysToSend) {
        webElement.clear();
        webElement.sendKeys(keysToSend);
    }
}
