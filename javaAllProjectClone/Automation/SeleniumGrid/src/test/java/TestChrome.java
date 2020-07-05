import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;

public class TestChrome extends AbstractForTests {
    ChromeOptions chromeOptions = new ChromeOptions();

    @Test
    public void runTest(){
        runTest(chromeOptions);
    }
}
