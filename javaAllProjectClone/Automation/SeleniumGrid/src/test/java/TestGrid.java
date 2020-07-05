import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

public class TestGrid {

    @Test
    public void runAllTests() {
        Class<?>[] classes = {TestChrome.class,TestEdge.class};
        JUnitCore.runClasses(new ParallelComputer(true, true), classes);
    }
}
