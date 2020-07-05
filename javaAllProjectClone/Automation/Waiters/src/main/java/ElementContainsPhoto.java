import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ElementContainsPhoto implements ExpectedCondition<Boolean> {
    private By locatorClass;
    private By locatorPhoto;

    public ElementContainsPhoto(final By locatorClass, final By locatorPhoto) {
        this.locatorClass = locatorClass;
        this.locatorPhoto = locatorPhoto;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {
        WebElement elementClass = webDriver.findElement(this.locatorClass);
        WebElement elementPhoto = webDriver.findElement(this.locatorPhoto);

        if (elementClass.getText().contains(elementPhoto.getText())) {
            return true;
        } else {
            return false;
        }
    }
}
