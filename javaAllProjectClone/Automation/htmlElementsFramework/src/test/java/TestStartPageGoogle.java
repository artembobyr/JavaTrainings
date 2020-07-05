import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class TestStartPageGoogle {

    private WebDriver driver;
    private SearchPage searchPage;
    ChromeOptions options = new ChromeOptions();


    @Before
    public void before() {
        WebDriverManager.chromedriver().setup();
        options.addArguments("--lang=en-ca");
        driver = new ChromeDriver(options);
        searchPage = new SearchPage(driver);
        driver.get("http://www.google.com");
    }

    @After
    public void after() {
        driver.quit();
    }

    @Test
    public void searchCats() {
        searchPage.search("cats");
        checkWord(By.xpath("//div[@class='FxvUNb kno-ecr-pt kno-fb-ctx']"), "Cat");
    }

    public void checkWord(By locator, String expectedValue) {
        String actual = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
        String expected = expectedValue;
        assertEquals(actual, expected);
    }
}
