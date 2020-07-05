package com.epam.crm.perfuiframework.pages;

import com.epam.crm.perfuiframework.navigationtiming.PerfNavigationTiming;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

import static com.epam.crm.perfuiframework.util.Constants.ENV_NAME;
import static com.epam.crm.perfuiframework.util.Constants.SCENARIO_NAME;
import static com.epam.crm.perfuiframework.util.Constants.WRITE_POINT;

@Log4j2
public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver);
    }
    private PerfNavigationTiming perfNavigationTiming = new PerfNavigationTiming();

    public MainPage closePendingEmailWarning(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        if (driver.findElements(By.id("InlineDialog_Iframe")).size() != 0) {
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(inlineDialogIframe));
            wait.until(ExpectedConditions.elementToBeClickable(butBegin));
            butBegin.click();
            log.info("Clicked on dismiss email remember");
            driver.switchTo().defaultContent();
            perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "MainPage");
        } else {
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "MainPage");
        }
        log.info("Main page is loaded");
        return this;
    }
}
