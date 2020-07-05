package com.epam.crm.perfuiframework.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import static com.epam.crm.perfuiframework.util.Constants.*;

public class BrowserFactory {

    public WebDriver startBrowser(String browserName, WebDriver driver) {

        if ("firefox".equalsIgnoreCase(browserName)) {
            driver = new FirefoxDriver();
        } else if ("chromeHeadless".equalsIgnoreCase(browserName)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        } else if ("chrome".equalsIgnoreCase(browserName)) {
            driver = new ChromeDriver();
        } else if ("chromeRemote".equalsIgnoreCase(browserName)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
            options.addArguments("window-size=1920x1080");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setVersion("73.0");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", ENABLE_VIDEO);
            capabilities.setCapability("videoName", "performanceTestingVideo.mp4");
            try {
                driver = new RemoteWebDriver(
                        URI.create(SELENOID_URL).toURL(),
                        capabilities);
                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        return driver;
    }
}
