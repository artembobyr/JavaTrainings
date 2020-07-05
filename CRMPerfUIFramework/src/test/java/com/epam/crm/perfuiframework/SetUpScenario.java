package com.epam.crm.perfuiframework;

import com.epam.crm.perfuiframework.components.AttachFileComponent;
import com.epam.crm.perfuiframework.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.TestStatusListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.epam.crm.perfuiframework.navigationtiming.PerfUtil.deleteJsonFiles;
import static com.epam.crm.perfuiframework.util.Constants.*;

@Listeners({TestStatusListener.class})
public class SetUpScenario {

    protected WebDriver driver;

    @BeforeClass
    protected void setUpBrowser() {
        deleteJsonFiles();
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        driver = BROWSER_FACTORY.startBrowser(BROWSER_NAME, driver);
        driver.get(LOGIN_URL);
        LOGIN_PAGE = new LoginPage(driver);
    }

    @BeforeMethod
    protected void setUp() {
        SCENARIO_NAME = this.getClass().getSimpleName();
        LOGIN_PAGE = new LoginPage(driver);
        MAIN_PAGE = new MainPage(driver);
        ORDER_PAGE = new OrderPage(driver);
        SPECIMEN_PAGE = new SpecimenPage(driver);
        SPECIMEN_PROCUREMENT_REQUEST_PAGE = new SpecimenProcurementRequestPage(driver);
        PRE_ORDER_PAGE = new PreOrderPage(driver);
        PATIENTS_PAGE = new PatientPage(driver);
        STUDY_PAGE = new StudyPage(driver);
        ATTACH_FILE_COMPONENT = new AttachFileComponent(driver);
    }


    @AfterClass
    protected void teardown() {
        driver.quit();
    }

}
