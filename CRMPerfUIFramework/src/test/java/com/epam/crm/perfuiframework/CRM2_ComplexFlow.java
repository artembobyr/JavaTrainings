package com.epam.crm.perfuiframework;

import org.testng.annotations.Test;

import static com.epam.crm.perfuiframework.util.Constants.*;

public class CRM2_ComplexFlow extends SetUpScenario{

    @Test(priority = 1)
    public void login() {
        LOGIN_PAGE.login(driver, EMAIL, PASSWORD).closePendingEmailWarning(driver);
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void createNewOrder() {
        ORDER_PAGE.createClinicalOrder(driver);
    }

    @Test(priority = 3, dependsOnMethods = {"login", "createNewOrder"})
    public void uploadDownloadFile() {
        ATTACH_FILE_COMPONENT.uploadFile(driver);
    }

    @Test(priority = 4, dependsOnMethods = {"login", "createNewOrder", "uploadDownloadFile"})
    public void processOrder() {
        ORDER_PAGE.processOrder(driver).prepareOrderForSubmitting(driver);
    }

    @Test(priority = 5, dependsOnMethods = {"login", "createNewOrder", "uploadDownloadFile", "processOrder"})
    public void goToSPRPage() {
        ORDER_PAGE.openSPR(driver);
    }

    @Test(priority = 6, dependsOnMethods = {"login", "createNewOrder", "uploadDownloadFile", "processOrder", "goToSPRPage"})
    public void createSpecimenInsideSPR() {
        SPECIMEN_PROCUREMENT_REQUEST_PAGE.openNewSpecimenCreationPage(driver);
        SPECIMEN_PAGE.createClinicalSpecimen(driver)
                .createSubSpecimen(driver)
                .submitSpecimen(driver);
    }

    @Test(priority = 7, dependsOnMethods = {"login", "createNewOrder", "uploadDownloadFile", "processOrder", "goToSPRPage", "createSpecimenInsideSPR"})
    public void openAndUpdatePreviouslyCreatedSpecimen() {
        SPECIMEN_PAGE.loadSpecimen(driver)
                .updateSpecimen(driver, "LIMS-2.0");
    }

}
