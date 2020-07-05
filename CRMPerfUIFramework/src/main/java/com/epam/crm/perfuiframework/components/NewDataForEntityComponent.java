package com.epam.crm.perfuiframework.components;

import com.epam.crm.perfuiframework.pages.AbstractPage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.crm.perfuiframework.util.CustomWaiters.*;

@Log4j2
public class NewDataForEntityComponent extends AbstractPage {
    public NewDataForEntityComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "subspecimens_addImageButtonImage")
    private WebElement createSubSpecimenButton;

    @FindBy(id = "NavBarGloablQuickCreate")
    private WebElement navBarGlobalQuickCreateFrame;

    @FindBy(id = "fmi_name")
    private WebElement subSpecimenId;

    @FindBy(id = "fmi_name_i")
    private WebElement subSpecimenIdInput;

    @FindBy(id = "globalquickcreate_save_button_NavBarGloablQuickCreate")
    private WebElement saveNewDataForEntityButton;

    @FindBy(xpath = "//span[@class='navStatusText']")
    private WebElement subSpecimenStatusText;

    @FindBy(id = "fmi_testid")
    private WebElement testOrderedTestId;

    @FindBy(id = "fmi_testid_ledit")
    private WebElement testOrderedTestInput;

    @FindBy(xpath = "//a[@title='FoundationOne']")
    private WebElement testOrderedTestChoice;

    @FindBy(id = "fmi_orderedtestid")
    private WebElement coverageOrderedTestId;

    @FindBy(id = "fmi_orderedtestid_ledit")
    private WebElement coverageOrderedTestIdInput;

    @FindBy(xpath = "//li/a[contains(@title,'ORD-')]")
    private WebElement coverageOrderedTestIdChoice;

    @FindBy(id = "fmi_accessionedsite")
    private WebElement accessionedSiteSubSpecimenField;

    @FindBy(xpath = "//option[@title='Cambridge']")
    private WebElement accessionedSiteSubSpecimenChoice;

    @FindBy(id = "fmi_matrixtype")
    private WebElement matrixTypeSubSpecimenField;

    @FindBy(id = "fmi_matrixtype_ledit")
    private WebElement matrixTypeSubSpecimenInput;

    @FindBy(xpath = "//span[text()='Blood']")
    private WebElement matrixTypeSubSpecimenChoice;

    @FindBy(id = "fmi_containertype")
    private WebElement containerTypeSubSpecimenField;

    @FindBy(xpath = "//select[@id='fmi_containertype_i']//option[contains(text(),'Other')]")
    private WebElement containerTypeSubSpecimenChoice;

    @FindBy(id = "fmi_trackingnumber")
    private WebElement extTrackingNumberSubSpecimenField;

    @FindBy(id = "fmi_trackingnumber_i")
    private WebElement extTrackingNumberSubSpecimenInput;


    public void createSubSpecimen(WebDriver driver) {
        driver.switchTo().frame(contentIFrame0);
        wait.until(ExpectedConditions.elementToBeClickable(createSubSpecimenButton));
        createSubSpecimenButton.click();
        log.info("Clicked on 'Сreate Sub-Specimen' button");
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(navBarGlobalQuickCreateFrame));
        waitForJavascript(20000, 1000, driver);
        chooseAccessionedSite(driver);
        fillAndChooseField(matrixTypeSubSpecimenField, matrixTypeSubSpecimenInput, matrixTypeSubSpecimenChoice, "Blood"); //    Fill And Choose Matrix type field
        containerTypeSubSpecimenChoice.click();
        //chooseField(containerTypeSubSpecimenField, containerTypeSubSpecimenChoice); //  Choose container type
        fillField(extTrackingNumberSubSpecimenField, extTrackingNumberSubSpecimenInput,"PerformanceUITest"); // Fill extTracking Number field
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(saveNewDataForEntityButton));
        saveNewDataForEntityButton.click();

//        wait.until(ExpectedConditions.visibilityOf(subSpecimenStatusText));
//        Assert.assertTrue(subSpecimenStatusText.getText().contains("was created successfully"));
    }

    public void createOrderedTest(WebDriver driver) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(navBarGlobalQuickCreateFrame));
        wait.until(ExpectedConditions.elementToBeClickable(testOrderedTestId));
        waitForJavascript(20000, 1000, driver);
        clickOnFieldViaJavascript(driver, testOrderedTestId);
        fillAndChooseField(testOrderedTestId, testOrderedTestInput, testOrderedTestChoice, "FoundationOne");
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(saveNewDataForEntityButton));
        saveNewDataForEntityButton.click();
        log.info("Clicked on 'save new ordered test' button");
    }

    public void createCoverageDetermination(WebDriver driver) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(navBarGlobalQuickCreateFrame));
        wait.until(ExpectedConditions.elementToBeClickable(coverageOrderedTestId));
        waitForJavascript(20000, 1000, driver);
//        clickOnFieldViaJavascript(driver, coverageOrderedTestId);
//        coverageOrderedTestIdInput.sendKeys(Keys.ENTER);
//        wait.until(ExpectedConditions.elementToBeClickable(coverageOrderedTestIdChoice));
//        coverageOrderedTestIdChoice.click();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(saveNewDataForEntityButton));
        saveNewDataForEntityButton.click();
        log.info("Clicked on 'Save Coverage Determination' button");

        //div[@id='Coverage_Records_d']
    }

    private void chooseAccessionedSite(WebDriver driver) {
        wait.until(ExpectedConditions.elementToBeClickable(accessionedSiteSubSpecimenField));
        clickOnFieldViaJavascript(driver, accessionedSiteSubSpecimenField);
        log.info("Clicked on 'accessionedSiteSubSpecimenField'");
        wait.until(ExpectedConditions.elementToBeClickable(accessionedSiteSubSpecimenChoice));
        accessionedSiteSubSpecimenChoice.click();
        log.info("Element is chosen");
    }

}
