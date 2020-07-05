import org.junit.After;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractForTests {
    static RemoteWebDriver driver;
    static String nodeUrl = "http://localhost:5555/wd/hub";

    public void runTest(MutableCapabilities options){
        try {
            this.driver = new RemoteWebDriver(new URL(nodeUrl), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
    }

    @After
    public void after() {
        driver.close();
    }
}
