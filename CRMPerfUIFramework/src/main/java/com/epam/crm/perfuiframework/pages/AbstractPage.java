package com.epam.crm.perfuiframework.pages;

import com.epam.crm.perfuiframework.navigationtiming.PerfNavigationTiming;
import com.epam.crm.perfuiframework.util.TestData;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.epam.crm.perfuiframework.util.Constants.*;
import static com.epam.crm.perfuiframework.util.CustomWaiters.*;
import static com.epam.crm.perfuiframework.util.TestData.*;

@Log4j2
public abstract class AbstractPage {

    protected WebDriverWait wait;


    public AbstractPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 50);
        PageFactory.initElements(driver, this);
    }

    protected PerfNavigationTiming perfNavigationTiming = new PerfNavigationTiming();
    /* Common section */

    @FindBy(xpath = "//button[@id='butBegin']")
    protected WebElement butBegin;

    @FindBy(xpath = "//span[@class='ms-crm-CommandBar-Menu' and contains(text(),'New')]")
    protected WebElement createNewEntityButton;

    @FindBy(xpath = "//a[@id='CS']")
    protected WebElement serviceCategoryButton;

    @FindBy(xpath = "//span[@id='TabSFA']/a")
    private WebElement categoryButton;

    @FindBy(id = "contentIFrame0")
    protected WebElement contentIFrame0;

    @FindBy(id = "contentIFrame1")
    protected WebElement contentIFrame1;

    @FindBy(id = "contentIFrame2")
    protected WebElement contentIFrame2;

    @FindBy(id = "InlineDialog_Iframe")
    protected WebElement inlineDialogIframe;

    @FindBy(css = "#header_crmFormSelector")
    protected WebElement formTypeDropdown;

    @FindBy(xpath = "//span[contains(text(),'Save')][1]")
    protected WebElement saveNewEntityButton;

    @FindBy(xpath = "//input[@id='crmGrid_findCriteria']")
    protected WebElement searchField;

    @FindBy(xpath = "//nobr/a[@href = 'javascript:;'][1]")
    protected WebElement searchedRecord;

    @FindBy(xpath = "//img[@title = 'Save']")
    protected WebElement saveAfterUpdateButton;

    @FindBy(xpath = "//iframe[contains(@id, 'contentIFrame')]")
    protected List<WebElement> contentIframes;

    @FindBy(xpath = "//span[@id='TabnavTabLogoTextId']")
    protected WebElement homePageButton;

    @FindBy(xpath = "//div[@id='FormTitle']/h1")
    protected WebElement createdEntity;

    @FindBy(id = "navTabGlobalCreateImage")
    protected WebElement createNewDataForEntityButton;

    @FindBy(xpath = " //span[contains(text(),'Submit')]")
    protected WebElement submitEntityButton;

    @FindBy(id = "alertJs-iFrame")
    private WebElement alertJsIFrame;

    @FindBy(xpath = "//*[@id='alertJs-close']")
    private WebElement alertJsClose;

    @FindBy(id = "okta-signin-username")
    private WebElement oktaEmail;

    @FindBy(id = "okta-signin-password")
    private WebElement oktaPassword;

    @FindBy(id = "okta-signin-submit")
    private WebElement oktaLoginButton;

    @FindBy(xpath = "//label[contains(text(),'Created On')]")
    private WebElement createdOnColumn;

    @FindBy(xpath = "//span[@id='titlefooter_statuscontrol']")
    protected WebElement statusControl;

    protected static String entityNumber;

    protected long openCreateNewEntityForm(WebDriver driver) {
        Actions actions = new Actions(driver);
        long difference;
        long startDifference = System.currentTimeMillis();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(createNewEntityButton));
            clickOnFieldViaJavascript(driver, createNewEntityButton);
            difference = System.currentTimeMillis() - startDifference;
            log.info("Click on Create new entity button");
        } catch (StaleElementReferenceException ex) {
            sleepWait(1500);
            WebElement createNewPatientButton = driver.findElement(By.xpath("//span[@class='ms-crm-CommandBar-Menu' and contains(text(),'New')]"));
            wait.until(ExpectedConditions.elementToBeClickable(createNewPatientButton));
            actions.moveToElement(createNewPatientButton).click().perform();
            difference = System.currentTimeMillis() - startDifference;
            log.info("Click on Create new entity button via action");
        }

        if(waitForAndCheckIfElementPresent(driver, alertJsIFrame)) {
            startDifference = System.currentTimeMillis();
            log.info("Alert JS Dialog is Displayed");
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(alertJsIFrame));
            wait.until(ExpectedConditions.elementToBeClickable(oktaEmail));
            oktaEmail.sendKeys(EMAIL);
            oktaPassword.sendKeys(PASSWORD);
            oktaLoginButton.click();
            waitForElementToDisappear(driver, oktaEmail);
            log.info("Successfully logged in via alert JS dialog");
            driver.switchTo().defaultContent();
            difference = System.currentTimeMillis() - startDifference;
        }
        return difference;
    }

    protected void goToCategories() {
        wait.until(ExpectedConditions.visibilityOf(categoryButton));
        categoryButton.click();
        log.info("Clicked on category button");
    }

    protected AbstractPage openEntitiesPage(WebElement entityCategoryButton) {
        goToCategories();
        wait.until(ExpectedConditions.elementToBeClickable(entityCategoryButton));
        entityCategoryButton.click();
        log.info("Clicked on entity '" + getLocatorFromWebElement(entityCategoryButton) + "' button");
        return this;
    }

    protected AbstractPage saveNewEntity(WebDriver driver) {
        driver.switchTo().defaultContent();
        saveNewEntityButton.click();
        log.info("Clicked on save new entity button");
        waitForElementToDisappear(driver, saveNewEntityButton);
        log.info("Save new entity button has disappeared");
        return this;
    }

    protected void getEntityNumber(WebDriver driver, WebElement frame) {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
        wait.until(ExpectedConditions.visibilityOf(createdEntity));
        entityNumber = createdEntity.getText();
    }

    protected void fillField(WebElement elementToClick, WebElement elementToInput, String valueToFill) {
        wait.until(ExpectedConditions.elementToBeClickable(elementToClick));
        elementToClick.click();
        log.info("Fill field: clicked on " + getLocatorFromWebElement(elementToClick));
        wait.until(ExpectedConditions.elementToBeClickable(elementToInput));
        elementToInput.sendKeys(valueToFill);
        log.info("Fill field: sent keys to " + getLocatorFromWebElement(elementToInput) + "|| Value: " + valueToFill);
    }

    protected void fillAndChooseField(WebElement elementToClick, WebElement elementToInput, WebElement elementToChoose,
                                      String valueToFill) {
        wait.until(ExpectedConditions.elementToBeClickable(elementToClick));
        elementToClick.click();
        log.info("Fill and choose field: clicked on " + getLocatorFromWebElement(elementToClick));
        elementToInput.sendKeys(valueToFill);
        elementToInput.sendKeys(Keys.ENTER);
        log.info("Fill and choose field: sent keys to " + getLocatorFromWebElement(elementToInput) + "|| Value: " + valueToFill);
        wait.until(ExpectedConditions.elementToBeClickable(elementToChoose));
        elementToChoose.click();
        log.info("Fill and choose field: chosen the element " + getLocatorFromWebElement(elementToChoose));
    }

    protected void chooseField(WebElement elementToClick, WebElement elementToChoose) {
        wait.until(ExpectedConditions.elementToBeClickable(elementToClick));
        elementToClick.click();
        log.info("Choose field: clicked on " + getLocatorFromWebElement(elementToClick));
        wait.until(ExpectedConditions.elementToBeClickable(elementToChoose));
        elementToChoose.click();
        log.info("Choose field: chosen the element " + getLocatorFromWebElement(elementToChoose));
    }

    protected AbstractPage searchForRecord(WebDriver driver, String searchValue) {
        if (contentIframes.size() <= 2)
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIframes.get(0)));
        else
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIframes.get(2)));
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        log.info("Clicked on search field");
        waitForJavascript(20000, 500, driver);
        searchField.sendKeys(searchValue);
        searchField.sendKeys(Keys.ENTER);
        log.info("Sent keys: 'Performance' to the search field");
        wait.until(ExpectedConditions.elementToBeClickable(searchedRecord));
        log.info("Search results appeared");
        if(waitForAndCheckIfElementPresent(driver, createdOnColumn)) {
            wait.until(ExpectedConditions.elementToBeClickable(createdOnColumn));
            createdOnColumn.click();
            wait.until(ExpectedConditions.elementToBeClickable(createdOnColumn));
            createdOnColumn.click();
            log.info("Ordered by rule 'Newest First' to open previously created record");
        }
        return this;
    }

}
