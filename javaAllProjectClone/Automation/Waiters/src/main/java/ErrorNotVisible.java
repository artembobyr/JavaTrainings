import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ErrorNotVisible implements ExpectedCondition<Boolean> {
    private String textToFind;
    private By locator;

    public ErrorNotVisible(final By elementFindBy, final String textToFind) {
        this.locator = elementFindBy;
        this.textToFind = textToFind;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {
        WebElement element = webDriver.findElement(this.locator);

        if (!(element.getText().contains(this.textToFind))) {
            System.out.println("There are not errors at the page");
            return true;
        } else {
            System.out.println("There is an error at the page");
            return false;
        }
    }
}


