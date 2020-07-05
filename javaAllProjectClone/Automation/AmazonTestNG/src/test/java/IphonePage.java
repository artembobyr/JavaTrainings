import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IphonePage {

    @FindBy(id = "add-to-cart-button")
    WebElement addToCartButton;

    public IphonePage(WebDriver driver) throws InterruptedException {
        PageFactory.initElements(driver, this);
    }
}
