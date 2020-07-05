import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class LinkIsClickable implements ExpectedCondition<WebElement> {
    private String linkText;

    public LinkIsClickable(final String linkText) {
        this.linkText = linkText;
    }

    @Override
    public WebElement apply(WebDriver webDriver) {
        By locator = By.linkText(this.linkText);
        WebElement element = webDriver.findElement(locator);

        if (element != null && element.isEnabled()) {
            System.out.println("Link is clickable");
            return element;
        } else {
            System.out.println("No such element");
            return null;
        }
    }
}
