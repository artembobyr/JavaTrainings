package com.epam.crm.perfuiframework.pages;

import com.epam.crm.perfuiframework.components.NewDataForEntityComponent;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static com.epam.crm.perfuiframework.util.Constants.*;
import static com.epam.crm.perfuiframework.util.CustomWaiters.*;
import static com.epam.crm.perfuiframework.util.TestData.*;

@Log4j2
public class SpecimenPage extends AbstractPage {

    public SpecimenPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@id='Accessioning_Specimen']")
    private WebElement specimensCategoryButton;

    @FindBy(xpath = "//span[@title='Pharma']")
    private WebElement pharmaOrderForm;

    @FindBy(xpath = "//span[@title='LIMS 2 Compliant Specimen Form']")
    private WebElement lims2Form;

    @FindBy(xpath = "//div[@id='com_orderid']")
    private WebElement orderField;

    @FindBy(xpath = "//input[@id='com_orderid_ledit']")
    private WebElement orderFieldInput;

    @FindBy(xpath = "//div[@id='com_receivedon_date']")
    private WebElement specimenReceivedDateField;

    @FindBy(xpath = "//input[@id='com_receivedon_date_iDateInput']")
    private WebElement specimenReceivedDateInput;

    @FindBy(xpath = "//div[@id='fmi_matrixtype']")
    private WebElement matrixTypeField;

    @FindBy(xpath = "//input[@id='fmi_matrixtype_ledit']")
    private WebElement matrixTypeInput;

    @FindBy(xpath = "//span[text()='Block']")
    private WebElement matrixTypeChoice;

    @FindBy(xpath = "//div[@id='fmi_collectiontubetype']")
    private WebElement factField;

    @FindBy(xpath = "//option[@title='Cell-free DNA BCT (Streck)']")
    private WebElement factChoice;

    @FindBy(xpath = "//div[@id='fmi_shippingvendor']")
    private WebElement shippingVendorField;

    @FindBy(xpath = "//option[@title='DHL']")
    private WebElement shippingVendorChoice;

    @FindBy(xpath = "//div[@id='com_specimenid_text']")
    private WebElement externalSpecimenIdField;

    @FindBy(xpath = "//input[@id='com_specimenid_text_i']")
    private WebElement externalSpecimenIdInput;

    @FindBy(xpath = "//div[@id='com_dateofcollection_date']")
    private WebElement dateOfCollectionField;

    @FindBy(xpath = "//input[@id='com_dateofcollection_date_iDateInput']")
    private WebElement dateOfCollectionInput;

    @FindBy(xpath = "//div[@id='fmi_specimensitecode']")
    private WebElement specimenSiteField;

    @FindBy(xpath = "//select[@id='fmi_specimensitecode_i']//option[@title='Anus']")
    private WebElement specimenSiteChoice;

    @FindBy(xpath = "//div[@id='com_trvdx_text']")
    private WebElement trfField;

    @FindBy(xpath = "//input[@id='com_trvdx_text_i']")
    private WebElement trfInput;

    @FindBy(xpath = "//div[@id='com_numbertubes_numb']")
    private WebElement noOfTubesField;

    @FindBy(xpath = "//input[@id='com_numbertubes_numb_i']")
    private WebElement noOfTubesInput;

    @FindBy(xpath = "//div[@id='com_numberunstainedslides_numb']")
    private WebElement noOfUSField;

    @FindBy(xpath = "//input[@id='com_numberunstainedslides_numb_i']")
    private WebElement noOfUSInput;

    @FindBy(id = "fmi_he_code")
    private WebElement heCodeField;

    @FindBy(xpath = "//select[@id='fmi_he_code_i']//option[@title='Yes'][contains(text(),'Yes')]")
    private WebElement heCodeChoice;

    @FindBy(id = "fmi_pathologist_id")
    private WebElement pathologistField;

    @FindBy(id = "fmi_pathologist_id_ledit")
    private WebElement pathologistFieldInput;

    @FindBy(xpath = "//div[@title='PathLab Test']")
    private WebElement pathologistChoice;

    @FindBy(id = "fmi_fmilaboratorylocation_code")
    private WebElement accessionedSiteField;

    @FindBy(xpath = "//option[@title='Cambridge']")
    private WebElement accessionedSiteChoice;

    @FindBy(id = "fmi_containertype")
    private WebElement containerTypeField;

    @FindBy(xpath = "//option[@title='FFPE Block']")
    private WebElement containerTypeChoice;

    @FindBy(xpath = "//label[contains(text(),'PerformanceUITest')]")
    private WebElement subSpecimenListCheck;

    @FindBy(xpath = "//span[contains(text(),'Clone Specimen')]")
    private WebElement cloneSpecimenButton;

    @FindBy(id = "fmi_specimenprocurementrequest_id")
    private WebElement sprField;

    @FindBy(id = "fmi_specimenprocurementrequest_id_ledit")
    private WebElement sprInput;

    @FindBy(xpath = "//li[contains(@id,'item')]//a[contains(@title, 'SPR-')][1]")
    private WebElement sprChoice;

    @FindBy(xpath = "//h1[@class='ms-crm-TextAutoEllipsis']")
    private WebElement nameOfSpecimen;

    /* DCP Locators*/

    @FindBy(xpath = "//a[contains(@aria-label,'Create Document')]")
    private WebElement createDocumentButton;

    @FindBy(xpath = "//div[@class = 'header' and contains(text(),'Specimen Received Letter')]")
    private WebElement specimenReceivedLetterButton;

    @FindBy(id = "lblRunWorkflow")
    private WebElement labelRunWorkflow;

    @FindBy(xpath = "//button[@id='btnNext']")
    private WebElement buttonNextDCP;

    @FindBy(xpath = "//a[contains(@title,'SPE-')]//span[@class='navTabButtonImageContainer']")
    private WebElement entityDataButton;

    @FindBy(xpath = "//span[contains(text(), 'Background Processes')]")
    private WebElement backgroundProcessesButton;

    @FindBy(xpath = "//iframe[@id='areaAsyncOperationsFrame']")
    private WebElement areaAsyncOperationsFrame;

    @FindBy(id = "refreshButtonLink")
    private WebElement refreshBackgroundProcessesButton;

    @FindBy(xpath = "//tr[@class='ms-crm-List-Row-Lite'][1]/td/div[@class='ms-crm-data-format-string'][1]")
    private WebElement documentSpecimenStatus;

    @FindBy(xpath = "//span[contains(text(),'SPE-')]")
    private WebElement backToSpecimenFormButton;

    @FindBy(css = "#actionGroupControl_scrollableContainer")
    private WebElement actionGroupControl;


    public SpecimenPage createClinicalSpecimen(WebDriver driver) {
        for (String winHandle: driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "GetSpecimenCreationPage");
            log.info("Successfully switched to the new window");
            driver.manage().window().maximize();
        }
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame0));
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "GetSpecimenCreationPage");

        changeFormToLIMS2(driver);
        fillSpecimenOrder();
        if (sprField.getText().isEmpty())
            fillAndChooseField(sprField, sprInput, sprChoice, valueOf("spr")); //  Fill and choose SPR
        fillAndChooseField(matrixTypeField, matrixTypeInput, matrixTypeChoice, valueOf("matrixType")); //   Fill Matrix Type
        containerTypeChoice.click();
        //chooseField(containerTypeField, containerTypeChoice); //    Choose Container Type
        fillAndChooseField(pathologistField, pathologistFieldInput, pathologistChoice, valueOf("pathologist")); //  Fill and choose Pathologist
        fillField(externalSpecimenIdField, externalSpecimenIdInput, "123456789"); //   Fill External Specimen Id
        chooseField(specimenSiteField, specimenSiteChoice); //  Choose Specimen Site
        chooseField(shippingVendorField, shippingVendorChoice); //  Choose Shipping Vendor
        chooseField(accessionedSiteField, accessionedSiteChoice); //    Choose Accessioned Site
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CreateSpecimen");
        saveNewEntity(driver);
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CreateSpecimen");
        getEntityNumber(driver, contentIFrame0);
        driver.switchTo().defaultContent();

        return this;
    }

    public SpecimenPage createPharmaSpecimen(WebDriver driver) {
        driver.switchTo().defaultContent();
        openSpecimensCategory();
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "GetSpecimenCreationPage");
        long difference = openCreateNewEntityForm(driver);
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "GetSpecimenCreationPage", difference);
        changeFormToPharma(driver);

        fillSpecimenOrder();
        //
        fillField(specimenReceivedDateField, specimenReceivedDateInput, "10/19/2018"); //   Fill Specimen Received Date
        fillField(matrixTypeField, matrixTypeInput, valueOf("matrixType")); //   Fill Matrix Type
        chooseField(factField, factChoice); //  Choose Collection Tube Type
        chooseField(shippingVendorField, shippingVendorChoice); //  Choose Shipping Vendor
        fillField(externalSpecimenIdField, externalSpecimenIdInput, "123456789"); //   Fill External Specimen Id
        fillField(dateOfCollectionField, dateOfCollectionInput, "10/10/2018"); //   Fill Date Of Collection
        chooseField(specimenSiteField, specimenSiteChoice); //  Choose Specimen Site
        fillField(noOfTubesField, noOfTubesInput, "2"); //    Fill No. Of Tubes
        fillField(noOfUSField, noOfUSInput, "9"); //    Fill No. Of US
        chooseField(accessionedSiteField, accessionedSiteChoice); //    Choose Accessioned Site
        fillField(trfField, trfInput, "123456789"); //    Fill TRFDx
        //fillAndChooseField(pathologistField, pathologistFieldInput, pathologistChoice, valueOf("pathologist")); //  Fill and choose Pathologist
        //

        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CreateSpecimen");
        saveNewEntity(driver);
        getEntityNumber(driver, contentIFrame1);
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CreateSpecimen");
        return this;
    }

    public SpecimenPage loadSpecimen(WebDriver driver) {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(homePageButton));
        homePageButton.click();
        openSpecimensCategory();
        searchSpecimen(driver);
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "LoadSpecimen");
        driver.switchTo().defaultContent();
        if (waitForAndCheckIfElementPresent(driver, contentIFrame1)) {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        }
        else {wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame0));}
        wait.until(ExpectedConditions.visibilityOf(orderField));
        log.info("Specimen was loaded");
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "LoadSpecimen");
        return this;
    }

    public SpecimenPage updateSpecimen(WebDriver driver, String typeOfSpecimen) {
        if (typeOfSpecimen.equals("Pharma"))
            fillField(noOfUSField, noOfUSInput, "9"); //    Fill No. Of US
        else
            fillField(trfField, trfInput, "123456789"); //    Fill TRFDx
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "UpdateSpecimen");
        saveAfterUpdateButton.click();
        log.info("Status control is: " + statusControl.getText());
        while(statusControl.getText().contains("saving"))
            sleepWait(100);
        log.info("Specimen was updated");
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "UpdateSpecimen");
        return this;
    }

    public SpecimenPage createSubSpecimen(WebDriver driver) {
        NEW_DATA_FOR_ENTITY_COMPONENT = new NewDataForEntityComponent(driver);
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CreateSubSpecimen");
        NEW_DATA_FOR_ENTITY_COMPONENT.createSubSpecimen(driver);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame0));
        wait.until(ExpectedConditions.elementToBeClickable(subSpecimenListCheck));
        log.info("Sub-Specimen was created and appeared in the list");
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CreateSubSpecimen");
        return this;
    }

    public SpecimenPage cloneSpecimen(WebDriver driver) {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(cloneSpecimenButton));
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CloneSpecimen");
        cloneSpecimenButton.click();
        log.info("Clicked on 'Clone specimen' button");
        wait.until(ExpectedConditions.elementToBeClickable(saveNewEntityButton));
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CloneSpecimen");
        saveNewEntityButton.click();
        log.info("Clicked on 'Save cloned specimen' button");
        waitForElementToDisappear(driver, saveNewEntityButton);
        log.info("Cloned specimen was saved");
        return this;
    }

    public SpecimenPage submitSpecimen(WebDriver driver) {
        driver.switchTo().defaultContent();
        waitForJavascript(20000, 1000, driver);
        wait.until(ExpectedConditions.elementToBeClickable(submitEntityButton));
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "SubmitSpecimen");
        submitEntityButton.click();
        log.info("Clicked on 'Submit specimen' button");
        waitForElementToDisappear(driver, submitEntityButton);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame0));
        log.info("Name of specimen: " + nameOfSpecimen.getText());
        Assert.assertTrue(nameOfSpecimen.getText().contains("US"));
        log.info("Specimen was submitted");
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "SubmitSpecimen");
        driver.switchTo().defaultContent();
        getEntityNumber(driver, contentIFrame0);
        return this;
    }

    public SpecimenPage createDocument(WebDriver driver) {
        driver.switchTo().defaultContent();
        createDocumentButton.click();
        log.info("Clicked on 'Create document' button");
        wait.until(ExpectedConditions.elementToBeClickable(specimenReceivedLetterButton));
        specimenReceivedLetterButton.click();
        log.info("Clicked on 'SPR received letter' button");
        wait.until(ExpectedConditions.visibilityOf(labelRunWorkflow));
        buttonNextDCP.click();
        log.info("Clicked on 'Next' in DCP button");
        waitForElementToDisappear(driver, buttonNextDCP);
        checkIfDocumentIsCreated(driver);
        backToSpecimenFormButton.click();
        return this;
    }

    private void checkIfDocumentIsCreated(WebDriver driver) {
        wait.until(ExpectedConditions.elementToBeClickable(entityDataButton));
        entityDataButton.click();
        log.info("Clicked on 'Entity data' button");
        wait.until(ExpectedConditions.elementToBeClickable(backgroundProcessesButton));
        backgroundProcessesButton.click();
        log.info("Clicked on 'Background Processes' button");
        driver.switchTo().frame(contentIFrame1);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(areaAsyncOperationsFrame));
        while(!documentSpecimenStatus.getText().equals("Succeeded")) {
            clickOnFieldViaJavascript(driver, refreshBackgroundProcessesButton);
        }
        log.info("Status of the document is 'Succeeded'");
        driver.switchTo().defaultContent();
    }

    private void openSpecimensCategory() {
        goToCategories();
        wait.until(ExpectedConditions.elementToBeClickable(serviceCategoryButton));
        serviceCategoryButton.click();
        log.info("Clicked on 'Service Category' button");
        wait.until(ExpectedConditions.elementToBeClickable(specimensCategoryButton));
        specimensCategoryButton.click();
        log.info("Clicked on 'Specimens Category' button");
    }

    private void changeFormToPharma(WebDriver driver) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(formTypeDropdown));
        } catch (StaleElementReferenceException ex) {
            sleepWait(1000);
            wait.until(ExpectedConditions.elementToBeClickable(formTypeDropdown));
        }
        log.info("Clicked on 'form type dropdown'");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        if (driver.findElements(By.xpath("//div[@id='com_receivedon_date']")).size() == 0) {
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            formTypeDropdown.click();
            wait.until(ExpectedConditions.elementToBeClickable(pharmaOrderForm));
            pharmaOrderForm.click();
            log.info("Clicked on 'Pharma order' form");
        } else {
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            log.info("It already was a 'Pharma order' form, no changes needed");
        }
    }

    private void changeFormToLIMS2(WebDriver driver) {
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame0));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(formTypeDropdown));
        } catch (StaleElementReferenceException ex) {
            sleepWait(1000);
            wait.until(ExpectedConditions.elementToBeClickable(formTypeDropdown));
        }
        log.info("Clicked on 'form type dropdown'");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        if (driver.findElements(By.xpath("//h2[@id='Accessioning_header_h2' and contains (text(), 'Accessioning- LIMS 2.0')]")).size() == 0) {
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            formTypeDropdown.click();
            wait.until(ExpectedConditions.elementToBeClickable(lims2Form));
            lims2Form.click();
            log.info("Clicked on 'LIMS 2.0' form");
        } else {
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            log.info("It already was a 'LIMS 2.0' form, no changes needed");
        }
    }

    private void fillSpecimenOrder() {
        if (!orderField.getText().contains(entityNumber)) {
            wait.until(ExpectedConditions.elementToBeClickable(orderField));
            orderField.click();
            log.info("Clicked on 'Order Field' button");
            wait.until(ExpectedConditions.elementToBeClickable(orderFieldInput));
            orderFieldInput.sendKeys(entityNumber);
            log.info("Sent value '" + entityNumber + "' to 'Specimen's Order Field' button");
        }
    }

    private void searchSpecimen(WebDriver driver) {
        if (contentIframes.size() <= 2)
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIframes.get(1)));
        else
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIframes.get(2)));
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        waitForJavascript(20000, 500, driver);
        searchField.sendKeys(entityNumber);
        log.info("Search Specimen: Sent value'" + entityNumber + "' to the 'search field'");
        searchField.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(entityNumber)));
        log.info("Search Specimen: Specimen was found");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.linkText(entityNumber)));
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.linkText(entityNumber)));
    }
}