package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {

    private final WebDriver driver;

    // Locators
    private final By usernameField = By.cssSelector("input[class='oxd-input oxd-input--focus']");
    private final By passwordField = By.cssSelector("input[class='oxd-input oxd-input--focus']");
    private final By loginButton = By.cssSelector("button[class$='orangehrm-login-button']");

    private final By profileImageLocator = By.cssSelector("li[class^='--active']");
    private final By invalidCredentialErrorMsg = By.cssSelector("div[class='oxd-alert-content oxd-alert-content--error']");
    private final By emptyUsername = By.cssSelector("input[class='oxd-input oxd-input--focus oxd-input--error']");
    private final By emptyPassword = By.cssSelector("input[class='oxd-input oxd-input--focus oxd-input--error']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Method to enter all the possible username and password values
    public void setCredentials(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }


    // Method to Check the profile image is displayed
    public boolean isProfileImageDisplayed() {
        WebElement profileImage = driver.findElement(profileImageLocator);
        return profileImage.isDisplayed();
    }

    // Method to Check the error message content when using invalid username or/and password
    public void invalidCredentialsMsgAssertion() {
        WebElement errorMsg = driver.findElement(invalidCredentialErrorMsg);
        String msgTxt = errorMsg.getText();

        Assert.assertTrue(errorMsg.isDisplayed(), "The invalid credentials message should be displayed");
        Assert.assertTrue(msgTxt.contains("Invalid credentials"), "The error message should be 'Invalid credentials'");

    }

    //Method to chek that the 2 fields of username and password highlighted by red in the empty case
    public void emptyUsernameAndPasswordAssertion() {
        WebElement emptyUsernameElement = driver.findElement(emptyUsername);
        WebElement emptyPasswordElement = driver.findElement(emptyPassword);


        Assert.assertTrue(emptyUsernameElement.isDisplayed(), "the username field should be highlighted by red");
        Assert.assertTrue(emptyPasswordElement.isDisplayed(), "the password field should be highlighted by red");

    }


}
