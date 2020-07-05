package com.epam.crm.perfuiframework;

import org.testng.annotations.Test;

import static com.epam.crm.perfuiframework.util.Constants.*;

public class CRM3_AccessioningFlow extends SetUpScenario{

    @Test(priority = 1)
    public void login() {
        LOGIN_PAGE.login(driver, EMAIL, PASSWORD).closePendingEmailWarning(driver);
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void createNewOrder() {
        ORDER_PAGE.createClinicalOrder(driver);
    }

    @Test(priority = 3, dependsOnMethods = {"login", "createNewOrder"})
    public void prepareOrderForSubmitting() {
        ORDER_PAGE.prepareOrderForSubmitting(driver).goToSpecimenCreationFromOrder(driver);
    }

    @Test(priority = 4, dependsOnMethods = {"login", "createNewOrder", "prepareOrderForSubmitting"})
    public void specimenFlow() {
        SPECIMEN_PAGE.createClinicalSpecimen(driver)
                .createSubSpecimen(driver)
                .submitSpecimen(driver);
    }
}
