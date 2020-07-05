import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

public class BasicSeleniumTest {
    private WebDriver driver;

    @Before
    public void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void loginToGmail() {
        getBaseDir(driver, "https://www.google.com/intl/ru/gmail/about/#");
        getElemByLocator(driver, By.xpath("//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']"), 5).click();
        sendKeysByLocator(driver, By.id("identifierId"), 10, "artembobir@gmail.com");
        getElemByLocator(driver, By.className("CwaK9"), 5).click();
        sendKeysByLocator(driver, By.name("password"), 10, "artbob888");
        getElemByLocator(driver, By.id("passwordNext"), 10).click();
        checkEquals(getElemByLocator(driver, By.linkText("Входящие"), 10).getText(), "Входящие");
    }

    @Test
    public void loginToTwitter() {
        driver.manage().window().maximize();
        driver.get("https://twitter.com/");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("artembobir@gmail.com");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Art123");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

    }
    public void getBaseDir(WebDriver driver, String baseDir){
        driver.get(baseDir);
    }

    public void checkEquals(String actualValue, String expectedValue){
        String actual = actualValue;
        String expected = expectedValue;
        Assert.assertEquals(actual, expected);
    }

    public WebElement getElemByLocator(WebDriver driver, By locator, int waitTime) {
        return new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void sendKeysByLocator(WebDriver driver, By locator, int waitTime, String value) {
        new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.visibilityOfElementLocated(locator))
                .sendKeys(value);
    }
}
