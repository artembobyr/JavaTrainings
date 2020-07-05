import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActionKeywords {
    static WebDriver driver;

    public static void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public static void openURL() {
        driver.get("https://trello.com");
    }

    public static void clickLoginButton() {
        driver.findElement(By.xpath("//a[@class='global-header-section-button']")).click();
    }

    public static void inputValidLogin() {
        driver.findElement(By.id("user")).sendKeys("art@com.ua");
    }

    public static void inputValidPassword() {

        driver.findElement(By.id("password")).sendKeys("12345678");
    }

    public static void clickEnterButton() {
        driver.findElement(By.id("login")).click();
    }

    public static void closeBrowser() {
        driver.close();
    }
}
