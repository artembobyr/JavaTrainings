import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tests {

    private WebDriver driver;
    private String baseDir = "https://football.ua";
    private WebDriverWait wait;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "\\webDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(baseDir);
        getElemByLocator(driver, By.xpath("//nav[@class='header-nav']//a[contains(text(),'Новости')]"), 5).click();
    }

    @After
    public void after() {
        driver.close();
    }

    @Test
    public void goToNews() {
        wait.until(new ElementContainsText(By.className("feed-header"), "АРХИВ"));
    }

    @Test
    public void findPartOfURL() {
        wait.until(new UrlContainsText("new"));
    }

    @Test
    public void noErrorsOnThePage() {
        wait.until(new ErrorNotVisible(By.className("page"), "error"));
    }

    @Test
    public void checkLinkIsClickable() {
        wait.until(new LinkIsClickable("Украина")).click();
    }

    @Test
    public void ElementContainsPhoto() {
        wait.until(new ElementContainsPhoto(By.className("photo"), By.xpath("//img[@alt='ФОТО: ФФУ']")));
    }

    public WebElement getElemByLocator(WebDriver driver, By locator, int waitTime) {
        return new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}