package org.kuali.student.lum.ui.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleWebDriverTest {

    @Test
    public void googleTest() {
        try {
            // Create a new instance of the Firefox driver
            // Notice that the remainder of the code relies on the interface,
            // not the implementation.
            WebDriver driver = new FirefoxDriver();

            // And now use this to visit Google
            driver.get("http://www.google.com");
            // Alternatively the same thing can be done like this
            // driver.navigate().to("http://www.google.com");

            // Find the text input element by its name
            WebElement element = driver.findElement(By.name("q"));

            // Enter something to search for
            element.sendKeys("Cheese!");

            // Now submit the form. WebDriver will find the form for us from the element
            element.submit();

            // Check the title of the page
            System.out.println("Page title is: " + driver.getTitle());

            // Google's search is rendered dynamically with JavaScript.
            // Wait for the page to load, timeout after 10 seconds
            Wait<WebDriver> wait = new WebDriverWait(driver, 10);
            ExpectedCondition<Boolean> condition = new CheeseTitlePresentCondition();
            wait.until(condition);

            // Should see: "cheese! - Google Search"
            System.out.println("Page title is: " + driver.getTitle());

            // Close the browser
            driver.quit();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
