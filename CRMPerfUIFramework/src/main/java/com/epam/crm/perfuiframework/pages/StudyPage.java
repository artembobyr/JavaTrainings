package com.epam.crm.perfuiframework.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.crm.perfuiframework.util.Constants.*;
import static com.epam.crm.perfuiframework.util.CustomWaiters.sleepWait;
import static com.epam.crm.perfuiframework.util.TestData.valueOf;

@Log4j2
public class StudyPage extends AbstractPage {

    public StudyPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@title='Studies'][1]")
    private WebElement studiesCategoryButton;

    @FindBy(xpath = "//div[@id='pha_sowid']")
    private WebElement sowField;

    @FindBy(xpath = "//input[@id='pha_sowid_ledit']")
    private WebElement sowInputField;

    @FindBy(xpath = "//span[@title='Test']")
    private WebElement sowChoice;

    @FindBy(xpath = "//div[@id='pha_priority']")
    private WebElement priorityField;

    @FindBy(xpath = "//option[contains(@title, 'PRO (Prospective)')]")
    private WebElement priorityChoice;

    @FindBy(xpath = "//div[@id='fmi_routing']")
    private WebElement routingField;

    @FindBy(xpath = "//option[@title = 'Retrospective']")
    private WebElement routingChoice;

    @FindBy(xpath = "//div[@id='fmi_ordersubtype']")
    private WebElement orderSubTypeField;

    @FindBy(xpath = "//select[@id='fmi_ordersubtype_i']//option[@title='Pharma'][contains(text(),'Pharma')]")
    private WebElement orderSubTypeChoice;

    @FindBy(xpath = "//div[@id='pha_estimatedsamplequantity']")
    private WebElement estimatedSampleQuantityField;

    @FindBy(xpath = "//div[@id='fmi_product_lookup']")
    private WebElement productField;

    @FindBy(xpath = "//div[@id='fmi_dnabait']")
    private WebElement dnaBaitField;

    @FindBy(xpath = "//option[@title = 'CF3+T7']")
    private WebElement dnaBaitChoice;

    @FindBy(xpath = "//div[@id='fmi_rnabait']")
    private WebElement rnaBaitField;

    @FindBy(xpath = "//option[@title = 'R2']")
    private WebElement rnaBaitChoice;

    @FindBy(xpath = "//div[@id='fmi_analysistype']")
    private WebElement analysisTypeField;

    @FindBy(xpath = "//option[@title = 'DNA and RNA']")
    private WebElement analysisTypeChoice;

    @FindBy(xpath = "//div[@id='fmi_turnaroundtime']")
    private WebElement tatField;

    @FindBy(xpath = "//option[@title = '7']")
    private WebElement tatChoice;

    @FindBy(xpath = "//div[@id='fmi_specialinstructions']")
    private WebElement asiField;

    @FindBy(xpath = "//textarea[@id='fmi_specialinstructions_i']")
    private WebElement asiFieldClear;

    @FindBy(xpath = "//div[@id='pha_comments']")
    private WebElement commentsField;

    @FindBy(xpath = "//textarea[@id='pha_comments_i']")
    private WebElement commentsFieldClear;

    @FindBy(id = "fmi_reimbursementtypecode")
    private WebElement reimbursementTypeField;

    @FindBy(xpath = "//option[@title='FMI-Sponsored']")
    private WebElement reimbursementTypeChoice;

    @FindBy(xpath = "//a[contains(@title, 'Studies')][1]")
    private WebElement goToStudiesListButton;

    private long studyCreationTime;

    public StudyPage createStudy(WebDriver driver) {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(homePageButton));
        homePageButton.click();
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "GetStudyCreationPage");
        openEntitiesPage(studiesCategoryButton);
        long difference = openCreateNewEntityForm(driver);
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "GetStudyCreationPage", difference);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        fillNewStudyForm();
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CreateStudy");
        saveNewEntity(driver);
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CreateStudy");
        return this;
    }

    public StudyPage loadStudy(WebDriver driver) {
        goToStudiesListButton.click();
        log.info("Clicked on 'Go to Studies List' button");
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "LoadStudy");
        searchForRecord(driver, "Performance-" + studyCreationTime);
        searchedRecord.click();
        log.info("Searched record is successfully found");
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(sowField));
        log.info("Study is loaded");
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "LoadStudy");
        return this;
    }

    public StudyPage updateStudy(WebDriver driver) {
        wait.until(ExpectedConditions.elementToBeClickable(asiField));
        asiField.click();
        asiFieldClear.clear();
        asiField.sendKeys("PerformanceTestASIUpdated");
        log.info("ASI field is updated with a new value");
        commentsField.click();
        commentsFieldClear.clear();
        commentsField.sendKeys("PerformanceTestCommentsUpdated");
        log.info("Comments field is updated with a new value");
        chooseField(reimbursementTypeField, reimbursementTypeChoice); //    Choose reimbursement type
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "UpdateStudy");
        saveAfterUpdateButton.click();
        log.info("Status control is: " + statusControl.getText());
        while(statusControl.getText().contains("saving"))
            sleepWait(100);
        log.info("Update finished");
        driver.switchTo().defaultContent();
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "UpdateStudy");
        return this;
    }

    private StudyPage fillNewStudyForm() {
        fillAndChooseField(sowField, sowInputField, sowChoice, valueOf("sow")); // Fill and Choose Sow field
        chooseField(priorityField, priorityChoice); //  Choose priority
        chooseField(routingField, routingChoice); //    Choose routing
        chooseField(orderSubTypeField, orderSubTypeChoice); //  Choose order sub type
        chooseField(reimbursementTypeField, reimbursementTypeChoice); //    Choose reimbursement type
        estimatedSampleQuantityField.sendKeys("1");
        productField.sendKeys(valueOf("product"));
        chooseField(dnaBaitField, dnaBaitChoice); // Choose DNA Bait
        chooseField(rnaBaitField, rnaBaitChoice); // Choose RNA Bait
        chooseField(analysisTypeField, analysisTypeChoice); // Choose analysis type
        chooseField(tatField, tatChoice); // Choose tat

        studyCreationTime = System.currentTimeMillis();

        asiField.sendKeys("PerformanceTestASI");
        log.info("Sent keys to 'ASI field' with value 'PerformanceTestASI'");
        commentsField.sendKeys("Performance-" + studyCreationTime + "-TestComments");
        log.info("Sent keys to 'Comments field' with value 'PerformanceTestComments'");
        return this;
    }
}
