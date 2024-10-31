package test;

import base.Base;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class LoginTest extends Base {

    LoginPage loginPage;
    String timeStamp;

    @BeforeClass
    public void beforeClassSetUp() {
        setup(browser);
        loginPage = new LoginPage(driver);

        //Create a new folder in each run to save screenshots
        timeStamp = new SimpleDateFormat("(dd-MM-yyyy HH-mm-ss)").format(Calendar.getInstance().getTime());
        new File(".\\Screenshots\\Login\\" + timeStamp).mkdir();
    }

    @Test
    @Description("Check that the user can login with valid credentials")
    public void loginWithValidCredentials() {

        // Test steps
        loginPage.setCredentials(validUserName, validPassword);

        // assert that the profile image is displayed after logging by valid credentials
        Assert.assertTrue(loginPage.isProfileImageDisplayed(), "Invalid username or password");
        Assert.assertEquals(driver.getCurrentUrl(), homePageURL, "The user should be redirected to the homepage" + homePageURL);
    }

    @Test
    @Description("Check the error message when user login by invalid username and password")
    public void loginWithInvalidCredentials() {

        // Test steps
        loginPage.setCredentials(invalidUsername, invalidPassword);

        // assert on invalid credentials error message
        loginPage.invalidCredentialsMsgAssertion();

    }

    @Test
    @Description("Check the error message when user login by invalid username and valid password")
    public void loginWithInvalidUsernameAndValidPassword() {

        // Test steps
        loginPage.setCredentials(invalidUsername, validPassword);

        // assert on invalid credentials error message
        loginPage.invalidCredentialsMsgAssertion();

    }

    @Test
    @Description("Check the error message when user login by valid username and invalid password")
    public void loginWithValidUsernameAndInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);

        // Test steps
        loginPage.setCredentials(validUserName, invalidPassword);


        // assert on invalid credentials error message
        loginPage.invalidCredentialsMsgAssertion();

    }

    @Test
    @Description("Check that the user cannot login with empty username and password")
    public void loginWitheEmptyUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver);

        // Test steps
        loginPage.setCredentials("", "");

        // assert that the username and password fields highlight by red when login by empty credentials
        loginPage.emptyUsernameAndPasswordAssertion();

    }
}

