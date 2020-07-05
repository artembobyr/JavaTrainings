package com.epam.crm.perfuiframework;

import org.testng.annotations.Test;

import static com.epam.crm.perfuiframework.util.Constants.*;

public class CRM1_PreAccessioningFlow extends SetUpScenario {

    @Test(priority = 1)
    public void login() {
        LOGIN_PAGE.login(driver, EMAIL, PASSWORD)
                .closePendingEmailWarning(driver);
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void createNewPreOrder() {
        PRE_ORDER_PAGE.createPreOrder(driver);
        ATTACH_FILE_COMPONENT.uploadFile(driver);
    }

    @Test(priority = 3, dependsOnMethods = {"login", "createNewPreOrder"})
    public void convertPreOrderToOrder() {
        PRE_ORDER_PAGE.convertPreOrder(driver);
        ORDER_PAGE.modifyOrderAfterConversion(driver);  //For the error 'Key was not given'
    }

    @Test(priority = 4, dependsOnMethods = {"login", "createNewPreOrder", "convertPreOrderToOrder"})
    public void goToSPRPage() {
        SPECIMEN_PROCUREMENT_REQUEST_PAGE.createNewSPR(driver);
        ORDER_PAGE.openSPR(driver);
    }

    @Test(priority = 5, dependsOnMethods = {"login", "createNewPreOrder", "convertPreOrderToOrder", "goToSPRPage"})
    public void generatePathLabAndOrderReceivedLetter() {
        SPECIMEN_PROCUREMENT_REQUEST_PAGE.createPathLabAndOrderReceivedLetter(driver);
    }

    @Test(priority = 6, dependsOnMethods = {"login", "createNewPreOrder", "convertPreOrderToOrder", "goToSPRPage",
            "generatePathLabAndOrderReceivedLetter"})
    public void createSpecimenAndSubSpecimenInsideSPR() {
        SPECIMEN_PROCUREMENT_REQUEST_PAGE.openNewSpecimenCreationPage(driver);
        SPECIMEN_PAGE.createClinicalSpecimen(driver)
                .createSubSpecimen(driver);
    }

    @Test(priority = 7, dependsOnMethods = {"login", "createNewPreOrder", "convertPreOrderToOrder", "goToSPRPage",
            "generatePathLabAndOrderReceivedLetter", "createSpecimenAndSubSpecimenInsideSPR"})
    public void CloneSpecimenAndCreateSubSpecimenOnIt() {
        SPECIMEN_PAGE.cloneSpecimen(driver)
                .createSubSpecimen(driver);
    }

    @Test(priority = 8, dependsOnMethods = {"login", "createNewPreOrder", "convertPreOrderToOrder", "goToSPRPage",
            "generatePathLabAndOrderReceivedLetter", "createSpecimenAndSubSpecimenInsideSPR", "CloneSpecimenAndCreateSubSpecimenOnIt"})
    public void SubmitClonedSpecimen() {
        SPECIMEN_PAGE.submitSpecimen(driver);
    }

}
