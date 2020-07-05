import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class SearchPage {
    private SearchField searchField;

    public SearchPage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public void search(String request) {
        searchField.search(request);
    }

}
