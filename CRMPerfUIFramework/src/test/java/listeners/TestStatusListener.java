package listeners;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileWriter;

@Log4j2
public class TestStatusListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String failedMethod = result.getMethod().getMethodName();
            appendTestResultStatusAndFailedMethodToPropertiesFile(failedMethod);
            log.error(result.getThrowable());
            log.error("\n ERROR CAUSE: " + result.getThrowable().getCause() + parseStackTraceToString(result));
        }
    }

    @SneakyThrows
    private void appendTestResultStatusAndFailedMethodToPropertiesFile(String failedMethod) {
        File propFile = new File("src/main/resources/application.properties");
        FileWriter fr = new FileWriter(propFile, true);
        fr.write("\ntestStatus=failed");
        fr.write("\nfailedStep=" + failedMethod);
        fr.close();
    }

    private String parseStackTraceToString(ITestResult result) {
        StringBuilder string = new StringBuilder();
        for (StackTraceElement s : result.getThrowable().getStackTrace()) {
            string.append("\n\tat " + s.getClassName() + "." + s.getMethodName()
                    + "(" + s.getFileName() + ":" + s.getLineNumber() + ")");
        }
        return string.toString();
    }

}
