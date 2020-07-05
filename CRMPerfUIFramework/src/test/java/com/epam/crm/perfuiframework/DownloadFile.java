package com.epam.crm.perfuiframework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DownloadFile {


    @Test
    public void testDownloadFile() throws InterruptedException {

        DesiredCapabilities capability;
        WebDriverManager.chromedriver().setup();

        Map chromePrefs = new HashMap();
        chromePrefs.put("profile.default_content_settings.popups", 2);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        ChromeOptions options = new ChromeOptions();
//Map chromeOptionsMap = new HashMap();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("–test-type");

        capability = DesiredCapabilities.chrome();
        //capability.setCapability("chrome.switches", Arrays.asList("–start-maximized,–ignore-certificate-error"));
        capability.setCapability(ChromeOptions.CAPABILITY, options);
//capability.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);

        WebDriver driver=new ChromeDriver(capability);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://fex.net/408171194284");
        //driver.findElement(By.xpath("//button[@class='uploads_empty_state__btn js-upload-empty-btn']")).click();
        driver.findElement(By.xpath("//div[@id='upload_empty_state']//input[@type='file']")).sendKeys("/Users/dmytro_tyrtyshnyi/IdeaProjects/CRMPerfUIFramework/blacklist.json");

        driver.get("https://jmeter.apache.org/download_jmeter.cgi");
        WebElement downloadedFile = driver.findElement(By.xpath("//a[contains(text(),'apache-jmeter-5.1.1.zip')]"));
        //downloadedFile.click();

        File file = new File(downloadedFile.getText());
        System.out.println("\n\nFILE: " + file + "Path: " + file.getAbsolutePath());

        while (!file.exists()) {
            if (file.exists())
                break;
            Thread.sleep(1000);
            System.out.println("File downloading...");
        }

    }
}
