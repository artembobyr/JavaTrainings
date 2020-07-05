import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    @FindBy(id = "sc-subtotal-label-activecart")
    WebElement cartCountInfoField;

    @FindBy(className = "a-list-item")
    WebElement cartProductsList;

    public CartPage(WebDriver driver) throws InterruptedException {
        PageFactory.initElements(driver, this);
    }
}
