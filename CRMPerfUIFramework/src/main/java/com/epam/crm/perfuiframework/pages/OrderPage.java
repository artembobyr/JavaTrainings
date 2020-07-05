package com.epam.crm.perfuiframework.pages;

import com.epam.crm.perfuiframework.components.NewDataForEntityComponent;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

import static com.epam.crm.perfuiframework.util.Constants.*;
import static com.epam.crm.perfuiframework.util.CustomWaiters.*;
import static com.epam.crm.perfuiframework.util.TestData.*;

@Log4j2
public class OrderPage extends AbstractPage {
    public OrderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "WebResource_StudyTypeahead")
    private WebElement limsStudyIDFrame;

    @FindBy(xpath = "//a[@title='Orders']")
    private WebElement ordersCategoryButton;

    @FindBy(xpath = "//span[@title='Pharma Order']")
    private WebElement pharmaOrderForm;

    @FindBy(xpath = "//span[@title='Clinical Order']")
    private WebElement clinicalOrderForm;

    @FindBy(id = "order_tab_header_h2")
    private WebElement orderTypeDeterminer;

    @FindBy(id = "com_study_id")
    private WebElement fmiStudyIDField;

    @FindBy(id = "com_study_id_ledit")
    private WebElement fmiStudyIDInput;

    @FindBy(xpath = "//a[@title='CLINICAL-T7']")
    private WebElement fmiStudyIDChoice;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/input[1]")
    private WebElement limsStudyIDField;

    @FindBy(xpath = "//a[@title='IT Test FACT']/..")
    private WebElement limsStudyIdEntity;

    @FindBy(xpath = "//div[@id='com_patient_id']")
    private WebElement patientField;

    @FindBy(xpath = "//input[@id='com_patient_id_ledit']")
    private WebElement patientFieldInput;

    @FindBy(xpath = "//div[@colname='fmi_studyid' and @title = 'ACT-AMC-PRO-16-239'][1]")
    private WebElement patientPharmaChoice;

    @FindBy(xpath = "//span[@title='Beth K Bonds']")
    private WebElement patientFieldChoice;

    @FindBy(xpath = "//div[@id='com_customerordernumber']")
    private WebElement partnerAccessionNoField;

    @FindBy(xpath = "//input[@id='com_customerordernumber_i']")
    private WebElement partnerAccessionNoFieldInput;

    @FindBy(xpath = "//a[contains(@title,'Go to Orders Area')]")
    private WebElement ordersList;

    @FindBy(xpath = "//li[@id='com_order|NoRelationship|Form|Mscrm.Form.com_order.Delete']")
    private WebElement deleteButton;

    @FindBy(xpath = "//span[@id='titlefooter_statuscontrol']")
    private WebElement statusControl;

    @FindBy(id = "com_orderingclinician_id")
    private WebElement orderingClinicianIdField;

    @FindBy(id = "com_orderingclinician_id_ledit")
    private WebElement orderingClinicianIdInput;

    @FindBy(id = "fmi_profile")
    private WebElement fmiProfileField;

    @FindBy(id = "fmi_profile_ledit")
    private WebElement fmiProfileInput;

    @FindBy(id = "Tests_Ordered_Order_addImageButtonImage")
    private WebElement testsOrderedAddButton;

    @FindBy(xpath = "//div[@id='Tests_Ordered_Order_ccDiv']//label[contains(text(),'ORD-')][1]")
    private WebElement testOrderedListElement;

    @FindBy(id = "Specimens_Order_addImageButtonImage")
    private WebElement specimensInOrderAddButton;

    @FindBy(id = "Coverage_Records_addImageButtonImage")
    private WebElement coveregeRecordsAddButton;

    @FindBy(xpath = "//div[@id='Coverage_Records_d']//a[contains(@title,'ORD-')]")
    private WebElement coverageRecordsListElement;

    @FindBy(xpath = "//span[contains(text(),'Process Order')]")
    private WebElement processOrderButton;

    @FindBy(xpath = "//a[@title='Sort by SP Number']")
    private WebElement specimenProcurementRequestsListRefresh;

    @FindBy(partialLinkText = "SPR-0")
    private WebElement specimenProcurementRequestsListElement;

    @FindBy(id = "bprequests_sub_addImageButtonImage")
    private WebElement sprAddButton;

    private long difference = 0;

    public void goToSpecimenCreationFromOrder(WebDriver driver) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(specimensInOrderAddButton));
        clickOnFieldViaJavascript(driver, specimensInOrderAddButton);
        log.info("Clicked on 'add specimen' button in an Order entity");
    }

    public OrderPage createPharmaOrder(WebDriver driver) {
        driver.switchTo().defaultContent();
        getOrderCreationPage(driver);
        changeFormToPharma();
        fillField(fmiStudyIDField, fmiStudyIDInput, valueOf("fmiStudyId")); //fill FMI Study Id
        fillAndChooseField(patientField, patientFieldInput, patientPharmaChoice, valueOf("pharmaOrderPatient")); // Fill and Choose Pharma Order Patient
        fillField(partnerAccessionNoField, partnerAccessionNoFieldInput, "123456789"); // Fill PartnerAccessionNo
        fillLIMSStudyID(driver);
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CreateOrder");
        saveOrder(driver);
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CreateOrder", difference);
        getEntityNumber(driver, contentIFrame1);
        return this;
    }

    public OrderPage createClinicalOrder(WebDriver driver) {
        driver.switchTo().defaultContent();
        getOrderCreationPage(driver);
        changeFormToClinical();
        fillField(orderingClinicianIdField, orderingClinicianIdInput, valueOf("profile")); // Fill Ordering Clinician
        fillField(fmiProfileField, fmiProfileInput, valueOf("profile")); // Fill Profile
        fillAndChooseField(patientField, patientFieldInput, patientFieldChoice, valueOf("patient")); // Fill Clinical Order Patient
        fillAndChooseField(fmiStudyIDField, fmiStudyIDInput, fmiStudyIDChoice, valueOf("study")); // Fill Study
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CreateOrder");
        saveOrder(driver);
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CreateOrder", difference);
        getEntityNumber(driver, contentIFrame1);
        driver.switchTo().defaultContent();
        return this;
    }

    public OrderPage processOrder(WebDriver driver) {
        difference = 0;
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(processOrderButton));
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "ProcessOrder");
        processOrderButton.click();
        log.info("Clicked on 'Process Order' button");
        confirmDuplicateSave(driver);
        waitForElementToDisappear(driver, processOrderButton);
        log.info("'Process Order' successfully accomplished");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "ProcessOrder", difference);
        wait.until(ExpectedConditions.elementToBeClickable(specimenProcurementRequestsListRefresh));
        waitForSpecimenProcurementRequestAppeared(driver);
        driver.switchTo().defaultContent();
        return this;
    }

    public OrderPage prepareOrderForSubmitting(WebDriver driver) {
        addTestsOrdered(driver);
        addCoverageDeterminations(driver);
        return this;
    }

    public OrderPage openSPR(WebDriver driver) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        waitForSpecimenProcurementRequestAppeared(driver);
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "OpenSPR");
        wait.until(ExpectedConditions.elementToBeClickable(specimenProcurementRequestsListElement));
        specimenProcurementRequestsListElement.click();
        log.info("Clicked on existing Specimen Procurement Request from order");
        driver.switchTo().defaultContent();
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "OpenSPR");
        return this;
    }

    public OrderPage loadOrder(WebDriver driver) {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(homePageButton));
        homePageButton.click();
        openEntitiesPage(ordersCategoryButton);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame0));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(entityNumber)));
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "LoadOrder");
        driver.findElement(By.linkText(entityNumber)).click();
        log.info("Load Order: clicked on previously created order");
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(partnerAccessionNoField));
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "LoadOrder");
        log.info("Order successfully loaded");
        return this;
    }

    public OrderPage updatePharmaOrder(WebDriver driver) {
        difference = 0;
        partnerAccessionNoField.click();
        partnerAccessionNoFieldInput.sendKeys("Performance UI Test");
        log.info("partnerAccessionNoField was updated with value 'Performance UI Test'");
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "UpdateOrder");
        saveAfterUpdateButton.click();
        driver.switchTo().defaultContent();
        long startDifference = System.currentTimeMillis();
        confirmDuplicateSave(driver);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        difference = System.currentTimeMillis() - startDifference;
        while(statusControl.getText().contains("saving"))
            sleepWait(100);
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "UpdateOrder", difference);
        log.info("Order was updated");
        return this;
    }

    public void clickOnCreateNewSPR() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(sprAddButton));
        sprAddButton.click();
        log.info("Clicked on 'Create new Specimen Procurement Request' button from Order entity");
    }

    public void modifyOrderAfterConversion(WebDriver driver) {
        changeFormToClinical();
        fillAndChooseField(fmiStudyIDField, fmiStudyIDInput, fmiStudyIDChoice, valueOf("study")); //    Fill Study
        saveAfterUpdateButton.click();
        driver.switchTo().defaultContent();
        confirmDuplicateSave(driver);
        log.info("Order was modified and saved after a conversion");
    }

    private void addTestsOrdered(WebDriver driver) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(testsOrderedAddButton));
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CreateTestsOrdered");
        testsOrderedAddButton.click();
        log.info("Clicked on 'add test ordered' button");
        driver.switchTo().defaultContent();
        NEW_DATA_FOR_ENTITY_COMPONENT = new NewDataForEntityComponent(driver);
        NEW_DATA_FOR_ENTITY_COMPONENT.createOrderedTest(driver);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(testOrderedListElement));
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CreateTestsOrdered");
        log.info("Test ordered was created");
        driver.switchTo().defaultContent();
    }

    private void addCoverageDeterminations(WebDriver driver) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(coveregeRecordsAddButton));
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CreateCoverageDeterminations");
        coveregeRecordsAddButton.click();
        log.info("Clicked on 'add coverage determinations' button");
        driver.switchTo().defaultContent();
        NEW_DATA_FOR_ENTITY_COMPONENT = new NewDataForEntityComponent(driver);
        NEW_DATA_FOR_ENTITY_COMPONENT.createCoverageDetermination(driver);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(coverageRecordsListElement));
        log.info("'Coverage determinations' was created");
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CreateCoverageDeterminations");
        driver.switchTo().defaultContent();
    }

    private void getOrderCreationPage(WebDriver driver) {
        wait.until(ExpectedConditions.elementToBeClickable(homePageButton));
        homePageButton.click();
        openEntitiesPage(ordersCategoryButton);
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "GetOrderCreationPage");
        long difference = openCreateNewEntityForm(driver);
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "GetOrderCreationPage", difference);
    }

    private void changeFormToPharma() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(patientField));
        if (!fmiStudyIDInput.isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(formTypeDropdown));
            formTypeDropdown.click();
            log.info("Change Form: Clicked on form type dropdown");
            wait.until(ExpectedConditions.elementToBeClickable(pharmaOrderForm));
            pharmaOrderForm.click();
            log.info("Form was changed to 'Pharma'");
        }
    }

    private void changeFormToClinical() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(patientField));
        if (!orderTypeDeterminer.getText().equals("Order - LIMS 2.0")) {
            wait.until(ExpectedConditions.elementToBeClickable(formTypeDropdown));
            formTypeDropdown.click();
            log.info("Change Form: Clicked on form type dropdown");
            wait.until(ExpectedConditions.elementToBeClickable(clinicalOrderForm));
            clinicalOrderForm.click();
            log.info("Form was changed to 'Clinical'");
        }
    }

    private void waitForSpecimenProcurementRequestAppeared(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        int attempt = 1;
        boolean flag = false;
        while (!flag) {
            try {
                if (attempt >= 50) {
                    log.info("SPR wasn't created");
                    break;
                }
                specimenProcurementRequestsListElement.isDisplayed();
                flag = true;
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            } catch (NoSuchElementException ex) {
                specimenProcurementRequestsListRefresh.click();
                log.info("SPR still not available.. Attempt #" + attempt);
                attempt++;
                sleepWait(500);
            }

        }
        log.info("Specimen Procurement Request has appeared");
    }

    private void fillLIMSStudyID(WebDriver driver) {
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "LimsStudyIdSearch");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(limsStudyIDFrame));
        log.info("Switched to LIMS study ID frame");
        limsStudyIDField.click();
        log.info("Clicked on LIMS study ID field");
        limsStudyIDField.sendKeys(valueOf("limsStudyId"));
        log.info("Sent keys to LIMS study ID field with a value: '" + valueOf("limsStudyId") + "'");
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(limsStudyIdEntity));
        limsStudyIdEntity.click();
        log.info("LIMS study ID was chosen");
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "LimsStudyIdSearch");
    }

    private void saveOrder(WebDriver driver) {
        driver.switchTo().defaultContent();
        saveNewEntityButton.click();
        log.info("Clicked on save Order button");
        confirmDuplicateSave(driver);
        waitForElementToDisappear(driver, saveNewEntityButton);
        log.info("Order was saved");
    }

    private void confirmDuplicateSave(WebDriver driver) {
        long startDifference = System.currentTimeMillis();
        boolean isDuplicate = waitForAndCheckIfElementPresent(driver, inlineDialogIframe);
        if (isDuplicate) {
            log.info("'Save duplicate' window appeared");
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(inlineDialogIframe));
            wait.until(ExpectedConditions.elementToBeClickable(butBegin));
            butBegin.click();
            log.info("Clicked on 'Save duplicate' button");
            waitForElementToDisappear(driver, butBegin);
            difference = System.currentTimeMillis() - startDifference;
        }
        driver.switchTo().defaultContent();
    }

}