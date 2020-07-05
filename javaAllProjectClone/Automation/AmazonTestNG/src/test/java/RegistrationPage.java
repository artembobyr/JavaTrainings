import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private final String EMAIL = "niwiwe1725@mailimail.com";
    private final String PASSWORD = "123456";

    @FindBy(id = "ap_email")
    WebElement emailField;

    @FindBy(id = "continue")
    WebElement continueButtonEmail;

    @FindBy(id = "ap_password")
    WebElement passwordField;

    @FindBy(id = "signInSubmit")
    WebElement continueButtonPassword;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void PerformRegistration() throws InterruptedException {
        emailField.sendKeys("niwiwe1725@mailimail.com");
        continueButtonEmail.click();
        passwordField.sendKeys("123456");
        continueButtonPassword.click();
    }
}
