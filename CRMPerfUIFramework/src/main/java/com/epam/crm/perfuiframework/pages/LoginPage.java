package com.epam.crm.perfuiframework.pages;

import com.epam.crm.perfuiframework.navigationtiming.PerfNavigationTiming;
import com.epam.crm.perfuiframework.util.CustomWaiters;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.crm.perfuiframework.util.Constants.ENV_NAME;
import static com.epam.crm.perfuiframework.util.Constants.SCENARIO_NAME;
import static com.epam.crm.perfuiframework.util.Constants.WRITE_POINT;
import static com.epam.crm.perfuiframework.util.CustomWaiters.*;

@Log4j2
public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private PerfNavigationTiming perfNavigationTiming = new PerfNavigationTiming();

    String loginPageUrl = "https://login.microsoftonline.com/";

    @FindBy(xpath = "//input[@id='i0116']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='idSIButton9']")
    private WebElement nextButton;

    @FindBy(xpath = "//input[@id='okta-signin-username']")
    private WebElement oktaEmail;

    @FindBy(xpath = "//input[@id='okta-signin-password']")
    private WebElement oktaPassword;

    @FindBy(xpath = "//input[@id='okta-signin-submit']")
    private WebElement oktaLoginButton;

    @FindBy(xpath = "//input[@id='idBtn_Back']")
    private WebElement dismissRemember;


    public MainPage login(WebDriver driver, String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "Login");
        emailField.sendKeys(email);
        log.info("Login: sent keys " + email + " to Microsoft emailField");
        nextButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(oktaEmail));
        perfNavigationTiming.writeToInflux(driver, WRITE_POINT, SCENARIO_NAME, ENV_NAME, "LoginRedirect");
        oktaEmail.sendKeys(email);
        log.info("Login: sent keys " + email + " to Okta emailField");
        oktaPassword.sendKeys(password);
        log.info("Login: sent secure password to Okta passwordField");
        oktaLoginButton.click();
        log.info("Login: clicked on Okta login button");
        wait.until(ExpectedConditions.elementToBeClickable(dismissRemember));
        dismissRemember.click();
        log.info("Login: clicked on 'Stay signed' button");
        return new MainPage(driver);
    }
}
