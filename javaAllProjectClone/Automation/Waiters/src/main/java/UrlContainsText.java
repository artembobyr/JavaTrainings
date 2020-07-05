import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class UrlContainsText implements ExpectedCondition<Boolean> {
    private String textToFind;

    public UrlContainsText(final String textToFind) {
        this.textToFind = textToFind;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {

        if (webDriver.getCurrentUrl().contains(this.textToFind)) {
            return true;
        } else {
            return false;
        }
    }
}
