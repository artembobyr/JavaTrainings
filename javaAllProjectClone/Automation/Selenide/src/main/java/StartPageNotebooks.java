import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StartPageNotebooks {
    public SortedPageNotebooks sortByPrice() {
        $(byText("Дорогие")).shouldBe(enabled).click();
        return new SortedPageNotebooks();
    }
}
