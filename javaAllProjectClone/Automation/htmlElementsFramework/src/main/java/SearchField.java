import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Search field")
@Block(@FindBy(id = "gsr"))

public class SearchField extends HtmlElement {

    @Name("Input field")
    @FindBy(name = "q")
    private WebElement requestInput;

    public void search(String request) {
        requestInput.sendKeys(request);
        requestInput.submit();
    }
}
