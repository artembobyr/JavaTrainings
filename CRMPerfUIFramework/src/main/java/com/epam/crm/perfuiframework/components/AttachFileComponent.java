package com.epam.crm.perfuiframework.components;

import com.epam.crm.perfuiframework.pages.AbstractPage;
import junit.framework.Assert;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.PrintWriter;

import static com.epam.crm.perfuiframework.util.Constants.*;

@Log4j2
public class AttachFileComponent extends AbstractPage {
    public AttachFileComponent(WebDriver driver) {
        super(driver);
    }

    // Document Downloads API Locators
    @FindBy(xpath = "//a[@title='NOTES']")
    private WebElement notesSwitcherButton;

    @FindBy(id = "createNote_notesTextBox")
    private WebElement createNoteTextBox;

    @FindBy(id = "attachButton")
    private WebElement attachButton;

    @FindBy(id = "createNote_attachFileIframe")
    private WebElement attachFileIFrame;

    @FindBy(id = "userFile")
    private WebElement attachmentInput;

    @FindBy(xpath = "//button[contains(text(),'Done')]")
    private WebElement postButton;

    @FindBy(linkText = "DDAtest.txt")
    private WebElement documentUploadedButton;

    public void uploadFile(WebDriver driver) {
//        File file = new File("DDAtest.txt");
//        System.out.println("File for upload path: " + file.getAbsolutePath());
        createFileBeforeUpload();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(notesSwitcherButton));
        notesSwitcherButton.click();
        log.info("Clicked on 'Notes Switcher' button");
        createNoteTextBox.click();
        log.info("Clicked on 'Create Note Text Box' button");
        attachButton.click();
        log.info("Clicked on 'Attach' button");
        perfNavigationTiming.writeToInflux(driver, START_POINT, SCENARIO_NAME, ENV_NAME, "UploadFile");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(attachFileIFrame));
        attachmentInput.sendKeys("/tmp/DDAtest.txt");
        log.info("File is attached");
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentIFrame1));
        wait.until(ExpectedConditions.elementToBeClickable(postButton));
        log.info("File uploading is finished");
        postButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(documentUploadedButton));
        Assert.assertEquals("DDAtest.txt", documentUploadedButton.getText());
        log.info("Asserted that the name of uploaded file equals to the created above file's name");
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "UploadFile");
    }

    @SneakyThrows
    private void createFileBeforeUpload() {
        PrintWriter writer = new PrintWriter("/tmp/DDAtest.txt", "UTF-8");
        writer.println("Performance test for DocumentDownloadAPI");
        writer.close();
        log.info("Created a file /tmp/DDAtest.txt");
    }


}
