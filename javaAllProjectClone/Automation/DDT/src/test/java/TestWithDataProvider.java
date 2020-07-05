import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestWithDataProvider {
    private static WebDriver driver;

    @BeforeMethod
    public void before(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://trello.com");
    }

    @AfterMethod
    public void after(){
        driver.quit();
    }

    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {
        return new Object[][] { { "art@com.ua", "12345678" }, { "art1@ua.com", "87654321" }};
    }

    @Test(dataProvider = "Authentication")
    public void test(String sUsername, String sPassword) {
        driver.findElement(By.xpath("//a[@class='global-header-section-button']")).click();
        driver.findElement(By.id("user")).sendKeys(sUsername);
        driver.findElement(By.id("password")).sendKeys(sPassword);
        driver.findElement(By.id("login")).click();
    }
}
