package com.epam.crm.perfuiframework.pages;


import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.crm.perfuiframework.util.Constants.*;
import static com.epam.crm.perfuiframework.util.CustomWaiters.*;

@Log4j2
public class SpecimenProcurementRequestPage extends AbstractPage {
    public SpecimenProcurementRequestPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "specimens_addImageButtonImage")
    private WebElement specimensAddImageButton;

    @FindBy(id = "lookup_specimens_i")
    private WebElement lookupSpecimenImageButton;

    @FindBy(xpath = "//a[@title='Create a new Specimen.']")
    private WebElement specimenAddNewButton;

    @FindBy(xpath = "//a[contains(@aria-label,'Create Document')]")
    private WebElement createDocumentButton;

    @FindBy(xpath = "//div[contains(text(),'Path Lab Packet and Order Received')]")
    private WebElement pathLabAndOrderReceivedLetterButton;

    @FindBy(id = "lblRunWorkflow")
    private WebElement labelRunWorkflow;

    @FindBy(xpath = "//button[@id='btnNext']")
    private WebElement buttonNextDCP;

    @FindBy(xpath = "//a[contains(@title,'SPR-')]//span[@class='navTabButtonImageContainer']")
    private WebElement entityDataButton;

    @FindBy(xpath = "//span[contains(text(), 'Background Processes')]")
    private WebElement backgroundProcessesButton;

    @FindBy(xpath = "//iframe[@id='areaAsyncOperationsFrame']")
    private WebElement areaAsyncOperationsFrame;

    @FindBy(id = "refreshButtonLink")
    private WebElement refreshBackgroundProcessesButton;

    @FindBy(xpath = "//a[@title='Specimen Procurement: Path Lab Packet']/ancestor::tr[@class]//div[@class='ms-crm-data-format-string'][1]")
    private WebElement pathLabStatus;

    @FindBy(xpath = "//a[@title='Specimen Procurement: Order Received']/ancestor::tr[@class]//div[@class='ms-crm-data-format-string'][1]")
    private WebElement orderReceivedStatus;


    @FindBy(xpath = "//span[contains(text(),'SPR-')]")
    private WebElement backToSpecimenFormButton;


    public SpecimenProcurementRequestPage openNewSpecimenCreationPage(WebDriver driver) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(specimensAddImageButton));
        specimensAddImageButton.click();
        log.info("Clicked on 'Add Specimens' button");
        boolean isLookupAppeared = waitForAndCheckIfElementPresent(driver, lookupSpecimenImageButton);
        if (isLookupAppeared){
            wait.until(ExpectedConditions.elementToBeClickable(lookupSpecimenImageButton));
            lookupSpecimenImageButton.click();
            log.info("Clicked on 'Lookup Specimen' button");
            wait.until(ExpectedConditions.elementToBeClickable(specimenAddNewButton));
            specimenAddNewButton.click();
            log.info("Clicked on 'Specimen Add New' button");
        }
        return this;
    }

    public SpecimenProcurementRequestPage createNewSPR(WebDriver driver) {
        ORDER_PAGE = new OrderPage(driver);
        ORDER_PAGE.clickOnCreateNewSPR();
        String winHandleBefore = driver.getWindowHandle();

        for (String winHandle: driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            log.info("Switched to a new window with SPR");
        }

        wait.until(ExpectedConditions.elementToBeClickable(saveNewEntityButton));
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CreateSPR");
        saveNewEntityButton.click();
        log.info("Clicked on 'Save new SPR' button");
        waitForElementToDisappear(driver, saveNewEntityButton);
        log.info("SPR was saved");
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CreateSPR");
        driver.close();
        driver.switchTo().window(winHandleBefore);
        log.info("Switched back to the main window");
        driver.switchTo().defaultContent();
        return this;
    }

    public SpecimenProcurementRequestPage createPathLabAndOrderReceivedLetter(WebDriver driver) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(specimensAddImageButton));
        driver.switchTo().defaultContent();
        createDocumentButton.click();
        log.info("Clicked on 'Create Document' button");
        wait.until(ExpectedConditions.elementToBeClickable(pathLabAndOrderReceivedLetterButton));
        pathLabAndOrderReceivedLetterButton.click();
        log.info("Clicked on 'PathLab And Order Received Letter' button");
        wait.until(ExpectedConditions.visibilityOf(labelRunWorkflow));
        buttonNextDCP.click();
        log.info("Clicked on 'Next' button in DCP");
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CreatePathLabAndOrderReceivedLetter");
        waitForElementToDisappear(driver, buttonNextDCP);
        checkIfDocumentIsCreated(driver);
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CreatePathLabAndOrderReceivedLetter");
        driver.switchTo().defaultContent();
        backToSpecimenFormButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(createNewEntityButton));
        return this;
    }

    private void checkIfDocumentIsCreated(WebDriver driver) {
        wait.until(ExpectedConditions.elementToBeClickable(entityDataButton));
        entityDataButton.click();
        log.info("Clicked on 'Entity data' button");
        wait.until(ExpectedConditions.elementToBeClickable(backgroundProcessesButton));
        backgroundProcessesButton.click();
        log.info("Clicked on 'Background Processes' button");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(areaAsyncOperationsFrame));
        while(!pathLabStatus.getText().equals("Succeeded")) {
            clickOnFieldViaJavascript(driver, refreshBackgroundProcessesButton);
            if (!waitForAndCheckIfElementPresent(driver, pathLabStatus)) {return; }
        }
        log.info("Status of the Path Lab is 'Succeeded'");
        while(!orderReceivedStatus.getText().equals("Succeeded")) {
            clickOnFieldViaJavascript(driver, refreshBackgroundProcessesButton);
            if (!waitForAndCheckIfElementPresent(driver, orderReceivedStatus)) {return; }
        }
        log.info("Status of the Order Received is 'Succeeded'");
    }

}
