package com.epam.crm.perfuiframework.util;

import org.openqa.selenium.*;


import java.util.concurrent.TimeUnit;

public class CustomWaiters {

    public static void waitForJavascript(int maxWaitMillis, int pollDelimiter, WebDriver webDriver) {
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + maxWaitMillis) {
            String prevState = webDriver.getPageSource();
            try {
                Thread.sleep(pollDelimiter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (prevState.equals(webDriver.getPageSource())) {
                return;
            }
        }
    }

    public static void sleepWait(int waitInMillis) {
        try {
            Thread.sleep(waitInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForElementToDisappear(WebDriver driver, WebElement element) {
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        int attempts = 0;
        while (attempts < 100) {
            try {
                if (element.isDisplayed()) {
                    sleepWait(500);
                }
            } catch (NoSuchElementException ex) {
                driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
                break;
            }
            catch (StaleElementReferenceException ex) { continue; }
            catch (UnhandledAlertException ex) {
                Alert alert = driver.switchTo().alert();
                alert.dismiss();
            }
            attempts++;
        }
    }

    public static boolean waitForAndCheckIfElementPresent(WebDriver driver, WebElement element) {
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        int attempts = 0;
        while (attempts < 3) {
            try {
                if (element.isDisplayed()) {
                    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
                    return true;
                }
            } catch (NoSuchElementException | StaleElementReferenceException ex) {
                sleepWait(1000);
            }
            attempts ++;
        }
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        return false;
    }

    public static void clickOnFieldViaJavascript(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView()", element);
        jsExecutor.executeScript("arguments[0].click()", element);
    }


}
