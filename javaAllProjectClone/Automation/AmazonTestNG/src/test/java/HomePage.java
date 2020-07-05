import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HomePage {
    WebDriver driver;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchField;

    @FindBy(className = "nav-input")
    WebElement searchButton;

    @FindBy(id = "nav-link-accountList")
    WebElement accountsAndLists;

    @FindBy(className = "nav-action-inner")
    WebElement signInButton;

    @FindBy(className = "nav-line-1")
    WebElement nameField;

    @FindBy(id = "nav-cart")
    WebElement cartButton;

    public HomePage() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        PageFactory.initElements(driver, this);
    }

    @BeforeTest
    public void beforeTest() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com/");
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    //todo Failed, need to verify with email code
    @Test
    public void login() throws InterruptedException {

        Actions action = new Actions(driver);
        action.moveToElement(accountsAndLists).moveToElement(signInButton).click().build().perform();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.PerformRegistration();
        Assert.assertTrue(nameField.getText().contains("Alex"));
    }

    @Test
    public void filterIphonesByPriceDesc() throws InterruptedException {
        searchField.sendKeys("Iphone");
        searchButton.click();
        IphonesPage iphonesPage = new IphonesPage(driver);
        Assert.assertTrue(iphonesPage.GetTheMostExpensiveIphone(driver).getText().contains("1,790"));
    }

    @Test
    public void addIphoneToCart() throws InterruptedException {
        searchField.sendKeys("Iphone");
        searchButton.click();
        new IphonesPage(driver).firstIphone.click();
        new IphonePage(driver).addToCartButton.click();
        cartButton.click();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.cartCountInfoField.getText().contains("1 item"));
        Assert.assertTrue(cartPage.cartProductsList.getText().contains("iPhone"));
    }
}