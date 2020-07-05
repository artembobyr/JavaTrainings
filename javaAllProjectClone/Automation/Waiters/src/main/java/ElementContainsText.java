import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ElementContainsText implements ExpectedCondition<Boolean> {
    private String textToFind;
    private By locator;

    public ElementContainsText(final By elementFindBy, final String textToFind) {
        this.locator = elementFindBy;
        this.textToFind = textToFind;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {
        WebElement element = webDriver.findElement(this.locator);

        if (element.getText().contains(this.textToFind)) {
            return true;
        } else {
            return false;
        }
    }
}
