import org.junit.Test;
import org.openqa.selenium.edge.EdgeOptions;

import java.net.MalformedURLException;

public class TestEdge extends AbstractForTests {
    EdgeOptions edgeOptions = new EdgeOptions();

    @Test
    public void runEdge(){
        runTest(edgeOptions);
    }
}
