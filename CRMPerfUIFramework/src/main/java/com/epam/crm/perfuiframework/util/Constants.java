package com.epam.crm.perfuiframework.util;

import com.epam.crm.perfuiframework.components.AttachFileComponent;
import com.epam.crm.perfuiframework.components.NewDataForEntityComponent;
import com.epam.crm.perfuiframework.pages.*;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Constants {

    public final static String BROWSER_NAME = System.getProperty("browser");

    public final static String DATABASE_URL = "http://" + getProperties().get("dbUrl") + ":80";
    public final static String DATABASE_NAME = "jmeter";

    public final static String SELENOID_URL = "http://" + getProperties().get("selenoidUrl") + ":4444/wd/hub";
    public final static Boolean ENABLE_VIDEO = Boolean.valueOf(getProperties().get("enableVideo"));

    public static String SCENARIO_NAME;
    public final static String LOGIN_URL = getProperties().get("envUrl");
    public final static String ENV_NAME = determineEnvName();
    public final static String INITIATOR_SCRIPT = "script";
    public final static String INITIATOR_AJAX = "xmlhttprequest";

    public final static boolean START_POINT = true;
    public final static boolean WRITE_POINT = false;

    public final static BrowserFactory BROWSER_FACTORY = new BrowserFactory();

    public static LoginPage LOGIN_PAGE;
    public static MainPage MAIN_PAGE;
    public static PatientPage PATIENTS_PAGE;
    public static StudyPage STUDY_PAGE;
    public static OrderPage ORDER_PAGE;
    public static SpecimenPage SPECIMEN_PAGE;
    public static PreOrderPage PRE_ORDER_PAGE;
    public static SpecimenProcurementRequestPage SPECIMEN_PROCUREMENT_REQUEST_PAGE;

    public static AttachFileComponent ATTACH_FILE_COMPONENT;
    public static NewDataForEntityComponent NEW_DATA_FOR_ENTITY_COMPONENT;

    public final static String EMAIL = "email" + getProperties().get("testUser") + "@foundationmedicine.com";
    public final static String PASSWORD = valueOfPassMap(getProperties().get("testUser"));


    private static String valueOfPassMap(String key) {
         Map<String, String> passMap = Stream.of(new Object[][] {
                { "01", "password"},
                { "02", "password"},
                { "03", "password"},
                { "04", "password"},
                { "05", "password"},
                { "06", "y=CF^password"},
                { "07", "%KXZ=Jzrr!=password"},
                { "08", "3_A!k^Hpassword"},
                { "09", "-5Wffw%#password"},
                { "10", "@@Khd!^password"},
                { "11", "password!C6gw"},
                { "12", "p+9uDL$+V#password"},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));
        return passMap.get(key);
    }

    private static String determineEnvName() {
        if (LOGIN_URL.contains("test"))
            return "Test";
        else if (LOGIN_URL.contains("stage"))
            return "Stage";
        return "Unknown";
    }

    @SneakyThrows
    private static Map<String, String> getProperties() {
        Properties properties = new Properties();
        File propFile = new File("src/main/resources/application.properties");
        String propertiesFileName = propFile.getAbsolutePath();
        Map<String, String> propertiesMap = new HashMap<>();
        properties.load(new FileInputStream(propertiesFileName));

        propertiesMap.put("envUrl", properties.getProperty("envUrl"));
        propertiesMap.put("dbUrl", properties.getProperty("dbUrl"));
        propertiesMap.put("testUser", properties.getProperty("testUser"));
        propertiesMap.put("selenoidUrl", properties.getProperty("selenoidUrl"));
        propertiesMap.put("enableVideo", properties.getProperty("enableVideo"));

        return propertiesMap;
    }

}
