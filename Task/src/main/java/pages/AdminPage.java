package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static constans.AdminTestData.*;
import static org.openqa.selenium.By.cssSelector;

public class AdminPage {

    private WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By adminTab = cssSelector("a[class*='active']");
    private final By rowsCountMsg = cssSelector("span[class='oxd-text oxd-text--span']");

    private final By userRole = cssSelector("div[class='oxd-select-text oxd-select-text--focus']");
    private final By employeeName = cssSelector("input[placeholder='Type for hints...']");
    private final By Status = cssSelector("a[class*='active']div[class='oxd-select-text oxd-select-text--focus']");
    private final By username = cssSelector("input[class='oxd-input oxd-input--focus']");
    private final By password = cssSelector("input[class='oxd-input oxd-input--focus']");
    private final By confirmPassword = cssSelector("input[class='oxd-input oxd-input--focus']");
    public final By userNameSearchingField = cssSelector("input[class='oxd-input oxd-input--focus']");

    public final By addButton = cssSelector("button[class='oxd-button oxd-button--medium oxd-button--secondary']");
    public final By saveButton = cssSelector("button[class$='orangehrm-left-space']");
    public final By searchButton = cssSelector("button[class$='orangehrm-left-space']");
    public final By deleteIcon = cssSelector("html > body > div > div:nth-of-type(1) > div:nth-of-type(2) > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type(3) > div > div > div:nth-of-type(1) > div > div > div:nth-of-type(1) > div:nth-of-type(2) > div > div > button:nth-of-type(1) > i");
    public final By deleteConfirmation = cssSelector("button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']");

    // Locate the table body with rows
    List<WebElement> rows = driver.findElements(By.cssSelector(".oxd-table .oxd-table-body .oxd-table-row"));


    //Method to click on the admin tab from the side menu
    public void clickOnAdminTab() {
        driver.findElement(adminTab).click();

    }
    //Method to return the real count of rows

    public int getListRowsCount() {
        return rows.size();
    }

    //Method to check that the message of the rows count display the real count
    public String assertOnRowNumbersInToaster() {
        WebElement rowsCountMsgLocator = driver.findElement(rowsCountMsg);
        return rowsCountMsgLocator.getText();

    }

    //Method to click on add button
    public void clickOnAddBtn() {
        driver.findElement(addButton).click();

    }

    //Method to click on save button
    public void clickOnSaveBtn() {
        driver.findElement(saveButton).click();

    }

    //Method to search by username and click on search button
    public void searchByUsername() {
        driver.findElement(userNameSearchingField).sendKeys("Admin");
        driver.findElement(searchButton).click();

    }

    //Method to delete user and confirm the deletion
    public void deleteUser() {
        driver.findElement(deleteIcon).click();
        driver.findElement(deleteConfirmation).click();

    }

    //Method to add all the mandatory fields
    public void setDataInAddForm() {
        WebElement selectRole = driver.findElement(userRole);
        Select select = new Select(selectRole);
        select.selectByVisibleText("Admin");

        driver.findElement(employeeName).sendKeys(validEmployeeName);

        WebElement selectStatus = driver.findElement(Status);
        Select select2 = new Select(selectStatus);
        select2.selectByVisibleText("Enabled");

        driver.findElement(username).sendKeys(validUserName);

        driver.findElement(password).sendKeys(validPassword);

        driver.findElement(confirmPassword).sendKeys(validPassword);

    }


}
