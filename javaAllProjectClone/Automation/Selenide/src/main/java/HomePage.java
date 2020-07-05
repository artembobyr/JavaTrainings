import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    public void sendNotebooksInSearch(String input) {
        $(byName("search_term")).setValue(input).pressEnter();
    }

    public StartPageNotebooks openStartNotebookPage() {
        $(byText("Ноутбуки и нетбуки")).shouldBe(visible).click();
        return new StartPageNotebooks();
    }
}
