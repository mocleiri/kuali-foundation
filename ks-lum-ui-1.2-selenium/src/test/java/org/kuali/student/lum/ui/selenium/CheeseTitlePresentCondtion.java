package org.kuali.student.lum.ui.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CheeseTitlePresentCondtion implements ExpectedCondition<Boolean> {
    @Override
    public Boolean apply(WebDriver d) {
        return d.getTitle().toLowerCase().startsWith("cheese!");
    }

}
