package com.epam.crm.perfuiframework.pages;

import com.epam.crm.perfuiframework.util.CustomWaiters;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.crm.perfuiframework.util.Constants.*;
import static com.epam.crm.perfuiframework.util.TestData.valueOf;

@Log4j2
public class PreOrderPage extends AbstractPage {
    public PreOrderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Pre-Orders')]")
    private WebElement preOrdersCategoryButton;

    @FindBy(id = "fmi_patientid")
    private WebElement patientPreOrderField;

    @FindBy(id = "fmi_patientid_ledit")
    private WebElement patientPreOrderInput;

    @FindBy(xpath = "//a[@title='Beth K Bonds']")
    private WebElement patientPreOrderChoice;

    @FindBy(id = "fmi_physicianfacilityid")
    private WebElement facilityIdField;

    @FindBy(id = "fmi_physicianfacilityid_ledit")
    private WebElement facilityIdInput;

    @FindBy(xpath = "//div[@title='2211']")
    private WebElement facilityIdChoice;

    @FindBy(id = "fmi_physicianid")
    private WebElement physicianIdField;

    @FindBy(id = "fmi_physicianid_ledit")
    private WebElement physicianIdInput;

    @FindBy(id = "fmi_testtype")
    private WebElement testTypeField;

    @FindBy(xpath = "//option[@title='FoundationOne']")
    private WebElement testTypeChoice;

    @FindBy(xpath = "//span[contains(text(),'Convert Pre-Order')]")
    private WebElement convertPreOrderButton;

    public PreOrderPage createPreOrder(WebDriver driver) {
        getPreOrderCreationPage(driver);
        fillAndChooseField(patientPreOrderField, patientPreOrderInput, patientPreOrderChoice, valueOf("patient")); //   Fill and Choose Pre Order Patient
        fillAndChooseField(facilityIdField, facilityIdInput, facilityIdChoice, valueOf("facility")); // Fill and Choose Facility
        fillField(physicianIdField, physicianIdInput, valueOf("physician")); // Fill Physician
        chooseField(testTypeField, testTypeChoice); // Choose Test Type
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "CreatePreOrder");
        saveNewEntity(driver);
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "CreatePreOrder");
        return this;
    }

    public PreOrderPage convertPreOrder(WebDriver driver) {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(convertPreOrderButton));
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "ConvertPreOrder");
        convertPreOrderButton.click();
        log.info("Clicked on convert pre-order button");
        CustomWaiters.waitForElementToDisappear(driver, convertPreOrderButton);
        log.info("Pre-order was converted to order");
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "ConvertPreOrder");
        getEntityNumber(driver, contentIFrame1);
        driver.switchTo().defaultContent();
        return this;
    }


    private void getPreOrderCreationPage(WebDriver driver) {
        goToCategories();
        wait.until(ExpectedConditions.elementToBeClickable(serviceCategoryButton));
        serviceCategoryButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(preOrdersCategoryButton));
        preOrdersCategoryButton.click();
        log.info("Clicked on 'Pre-Orders' category button");
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "GetPreOrderCreationPage");
        long difference = openCreateNewEntityForm(driver);
        log.debug("Difference is: " + difference);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(patientPreOrderField));
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "GetPreOrderCreationPage", difference);
    }

}
