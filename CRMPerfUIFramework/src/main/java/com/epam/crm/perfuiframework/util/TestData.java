package com.epam.crm.perfuiframework.util;

import org.openqa.selenium.WebElement;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.crm.perfuiframework.util.Constants.*;

public class TestData {
    private static Map<String, String> testEnvDataMap = Stream.of(new Object[][] {
            { "physician", "Account Test"},
            { "facility", "Account Test"},
            { "patient", "Beth K Bonds"},
            { "pathologist", "Path Test"},
            { "fmiStudyId", "ACT-AMC-PRO-16-239"},
            { "pharmaOrderPatient", "ACT-AMC-PRO-16-239 BackendTestUpdated 67507e63-b4ad-4d19-821c-170166cbda78"},
            { "study", "CLINICAL-T7"},
            { "limsStudyId", "IT Test FACT"},
            { "profile", "James Test"},
            { "matrixType", "Block"},
            { "sow", "Pharma 1"},
            { "spr", "Test"},
            { "product", "AV Samples"},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));

    private static Map<String, String> stageEnvDataMap = Stream.of(new Object[][] {
            { "physician", "Account TestStage"},
            { "facility", "Account Test"},
            { "patient", "Beth K Bonds"},
            { "pathologist", "Path Test"},
            { "fmiStudyId", "ACT-AMC-PRO-16-239"},
            { "pharmaOrderPatient", "ACT-AMC-PRO-16-239"},
            { "study", "CLINICAL-T7"},
            { "limsStudyId", "IT Test FACT"},
            { "profile", "James Test"},
            { "matrixType", "Blood"},
            { "sow", "Test"},
            { "spr", "Test"},
            { "product", "AV Samples"},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));

    public static String valueOf(String key) {
        if (ENV_NAME.equalsIgnoreCase("Test")) {
            return testEnvDataMap.get(key);
        }
        else if(ENV_NAME.equalsIgnoreCase("Stage")) {
            return stageEnvDataMap.get(key);
        }
        return "Unknown Key";
    }

    public static String getLocatorFromWebElement(WebElement element) {

        return element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
    }

}
