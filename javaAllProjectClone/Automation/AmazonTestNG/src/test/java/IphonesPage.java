import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IphonesPage {

    @FindBy(className = "a-dropdown-container")
    WebElement sortByDropDown;

    @FindBy(id = "s-result-sort-select_2")
    WebElement byPriceDesc;

    @FindBy(xpath = "//*[@id=\"search\"]/div[1]/div[2]/div/span[4]/div[1]/div[1]/div/span/div/div/div[2]/div[1]/div/div")
    WebElement firstIphone;

    public IphonesPage(WebDriver driver) throws InterruptedException {
        PageFactory.initElements(driver, this);
    }

    public WebElement GetTheMostExpensiveIphone(WebDriver driver) throws InterruptedException {
        sortByDropDown.click();
        byPriceDesc.click();
        WebElement theMostExpensiveIphone = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[4]/div[1]/div[1]/div/span/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div/div/span[2]"));
        return theMostExpensiveIphone;
    }
}
