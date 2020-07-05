import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class TestSelenide {
    private String baseURL = "https://prom.ua/";
    private HomePage homePage = new HomePage();

    @Before
    public void before() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        open(baseURL);
    }

    @Test
    public void findTheMostExpensiveNotebook() {
        homePage.sendNotebooksInSearch("ноутбук");
        StartPageNotebooks startPageNotebooks = homePage.openStartNotebookPage();
        SortedPageNotebooks sortedPageNotebooks = startPageNotebooks.sortByPrice();
        sortedPageNotebooks.checkNotebook("655 400");
    }
}
