import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SortedPageNotebooks {
    public void checkNotebook(String price) {
        $(byXpath("//span[contains(text(),'655 400')]")).shouldHave(text(price));
    }
}
