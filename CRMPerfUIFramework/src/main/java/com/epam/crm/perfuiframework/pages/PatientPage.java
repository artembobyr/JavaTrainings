package com.epam.crm.perfuiframework.pages;

import com.epam.crm.perfuiframework.navigationtiming.PerfNavigationTiming;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.crm.perfuiframework.util.Constants.*;
import static com.epam.crm.perfuiframework.util.CustomWaiters.sleepWait;
import static com.epam.crm.perfuiframework.util.CustomWaiters.waitForAndCheckIfElementPresent;
import static com.epam.crm.perfuiframework.util.CustomWaiters.waitForElementToDisappear;
import static com.epam.crm.perfuiframework.util.TestData.valueOf;

@Log4j2
public class PatientPage extends AbstractPage {

    public PatientPage(WebDriver driver) {
        super(driver);
    }

    private PerfNavigationTiming perfNavigationTiming = new PerfNavigationTiming();

    @FindBy(xpath = "//a[@title='Patients']")
    private WebElement patientsCategoryButton;

    @FindBy(id = "fmi_studyid")
    private WebElement fmiStudyIdField;

    @FindBy(id = "fmi_studyid_ledit")
    private WebElement fmiStudyIdInput;

    @FindBy(id = "com_externalid")
    private WebElement externalIdField;

    @FindBy(id = "com_externalid_i")
    private WebElement externalIdInput;

    @FindBy(id = "fmi_siteid")
    private WebElement fmiSiteIdField;

    @FindBy(id = "fmi_siteid_i")
    private WebElement fmiSiteIdInput;

    @FindBy(xpath = "//div[@id='com_gender']")
    private WebElement genderField;

    @FindBy(xpath = "//option[@title='Male']")
    private WebElement maleGenderButton;

    @FindBy(xpath = "//div[@id='com_dateofbirth']")
    private WebElement dateOfBirthField;

    @FindBy(xpath = "//input[@id='com_dateofbirth_iDateInput']")
    private WebElement dateOfBirthInput;

    @FindBy(xpath = "//a[contains(@title, 'Patients')][1]")
    private WebElement goToPatientsListButton;

    private long difference = 0;

    public PatientPage createPatient(WebDriver driver) {
        openEntitiesPage(patientsCategoryButton);
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "GetPatientCreationPage");
        difference = openCreateNewEntityForm(driver);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "GetPatientCreationPage", difference);
        fillNewPatientForm();
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CreatePatient");
        driver.switchTo().defaultContent();
        saveNewPatient(driver);
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CreatePatient", difference);
        return this;
    }

    public PatientPage loadPatient(WebDriver driver) {
        goToPatientsListButton.click();
        searchForRecord(driver, "Performance");
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "LoadPatient");
        searchedRecord.click();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(fmiStudyIdField));
        log.info("Existing Patient was loaded");
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "LoadPatient");
        return this;
    }

    public PatientPage updatePatient(WebDriver driver) {
        difference = 0;
        wait.until(ExpectedConditions.elementToBeClickable(fmiSiteIdField));
        fmiSiteIdField.sendKeys("Client-Side Performance Test Updated-" + System.currentTimeMillis());
        log.info("Update patient: field 'fmiSiteId' was updated");
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "UpdatePatient");
        saveAfterUpdateButton.click();
        log.info("Clicked on button 'Save after update'");
        log.info("Status control is: " + statusControl.getText());
        while(statusControl.getText().contains("saving"))
            sleepWait(100);
        driver.switchTo().defaultContent();
        log.info("Patient was updated");
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "UpdatePatient");
        return this;
    }

    private PatientPage fillNewPatientForm() {
        fillField(fmiStudyIdField, fmiStudyIdInput, valueOf("fmiStudyId")); //  Fill fmiStudyId field
        fillField(externalIdField, externalIdInput,"PerformanceTest"); //  Fill fmiStudyId field
        fillField(fmiSiteIdField, fmiSiteIdInput, "Client-Side Performance Test"); //    Fill fmiSiteId field
        chooseField(genderField, maleGenderButton); //  Choose gender
        fillField(dateOfBirthField, dateOfBirthInput, "10/10/2017"); // Fill date of birth

        return this;
    }

    private PatientPage saveNewPatient(WebDriver driver) {
        saveNewEntityButton.click();
        log.info("Clicked on 'Save new patient' button");
        long startDifference = System.currentTimeMillis();
        boolean isDuplicate = waitForAndCheckIfElementPresent(driver, inlineDialogIframe);
        if (isDuplicate) {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(inlineDialogIframe));
            wait.until(ExpectedConditions.elementToBeClickable(butBegin));
            butBegin.click();
            log.info("Clicked on 'Save duplicate patient' button");
            waitForElementToDisappear(driver, butBegin);
            difference = System.currentTimeMillis() - startDifference;
            log.debug("Difference: " + difference);
        }
        driver.switchTo().defaultContent();
        waitForElementToDisappear(driver, saveNewEntityButton);
        log.info("Patient was saved");
        return this;
    }
}
