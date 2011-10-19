package org.kuali.student.lum.ui.selenium.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class TitlePresentCondition implements ExpectedCondition<Boolean> {
    String expectedTitle;

    public TitlePresentCondition() {
        this(null);
    }

    public TitlePresentCondition(String expectedTitle) {
        super();
        this.expectedTitle = expectedTitle;
    }

    @Override
    public Boolean apply(WebDriver d) {
        String title = d.getTitle();
        if (title == null) {
            return false;
        }
        if (expectedTitle == null) {
            return false;
        }
        return d.getTitle().toLowerCase().equals(expectedTitle.toLowerCase());
    }

    public String getExpectedTitle() {
        return expectedTitle;
    }

    public void setExpectedTitle(String expectedTitle) {
        this.expectedTitle = expectedTitle;
    }

}
