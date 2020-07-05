package com.epam.crm.perfuiframework;

import org.testng.annotations.Test;

import static com.epam.crm.perfuiframework.util.Constants.*;

public class PharmaDataMigration extends SetUpScenario{

    @Test(priority = 1, groups = {"user05","user11"})
    public void login() {
        LOGIN_PAGE.login(driver, EMAIL, PASSWORD).closePendingEmailWarning(driver);
    }

    @Test(priority = 2, dependsOnMethods = "login", groups = "user05")
    public void patientEntityFlow() {
        PATIENTS_PAGE.createPatient(driver)
                .loadPatient(driver)
                .updatePatient(driver);
    }

    @Test(priority = 3, dependsOnMethods = {"login", "patientEntityFlow"}, groups = "user05")
    public void studyEntityFlow() {
        STUDY_PAGE.createStudy(driver)
                .loadStudy(driver)
                .updateStudy(driver);
    }

    @Test(priority = 4, dependsOnMethods = "login", groups = "user11")
    public void OrderEntityFlow() {
        ORDER_PAGE.createPharmaOrder(driver)
                .loadOrder(driver)
                .updatePharmaOrder(driver);
    }

    @Test(priority = 5, dependsOnMethods = {"login", "OrderEntityFlow"}, groups = "user11")
    public void SpecimenEntityFlow() {
        SPECIMEN_PAGE.createPharmaSpecimen(driver)
                .loadSpecimen(driver)
                .updateSpecimen(driver,"Pharma");
    }
}
