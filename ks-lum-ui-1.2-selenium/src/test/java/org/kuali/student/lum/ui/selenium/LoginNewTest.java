package org.kuali.student.lum.ui.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginNewTest {

    @Test
    public void simpleTest() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://dev.ks.kuali.org/login.jsp");
        WebElement logonUserName = driver.findElement(By.id("j_username"));
        typeValue(logonUserName, "admin");
        WebElement logonPassword = driver.findElement(By.id("j_password"));
        typeValue(logonPassword, "admin");
        logonPassword.submit();
        String title = driver.getTitle();
        driver.quit();
    }

    private void typeValue(final WebElement webElement, CharSequence... keysToSend) {
        webElement.clear();
        webElement.sendKeys(keysToSend);
    }
}
