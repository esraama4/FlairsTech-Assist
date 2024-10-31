package test;

import base.Base;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AdminTest extends Base {

    AdminPage adminPage;
    LoginPage loginPage;
    String timeStamp;
    String adminPageURL = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";

    @BeforeClass
    public void beforeClassSetUp() {
        setup(browser);
        adminPage = new AdminPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.setCredentials(validUserName, validPassword);

        //Create a new folder in each run to save screenshots
        timeStamp = new SimpleDateFormat("(dd-MM-yyyy HH-mm-ss)").format(Calendar.getInstance().getTime());
        new File(".\\Screenshots\\Admin\\" + timeStamp).mkdir();
    }

    @BeforeMethod

    public void beforeMethodSetUp() {
        //Click on adminTab
        adminPage.clickOnAdminTab();
        //Assert That the user redirected to the correct url
        Assert.assertEquals(driver.getCurrentUrl(), adminPageURL, "the url should be redirected to the admin page " + adminPageURL);

    }

    @Test
    @Description("Validate that the table rows counter in the toaster is a correct number")
    public void checkTableRowsCount() {

        //Return the correct rows from the table
        String expectedRowsCount = String.valueOf(adminPage.getListRowsCount());
        System.out.println("The num of rows count=" + expectedRowsCount);

        String writtenRowsCount = adminPage.assertOnRowNumbersInToaster();

        //assert that the toaster message display the correct rows count
        Assert.assertTrue(writtenRowsCount.contains(expectedRowsCount), "the rows count should be " + expectedRowsCount);

    }

    @Test
    @Description("Add user with all the valid mandatory fields and check that the rows count increased by 1 ")
    public void addUserWithValidMandatoryFields() {

        //Get the records number before adding new user
        int recordsCountBeforeAddingUser = adminPage.getListRowsCount();

        adminPage.clickOnAddBtn();
        adminPage.setDataInAddForm();
        adminPage.clickOnSaveBtn();

        //Get the records number after adding new user
        int recordsCountAfterAddingUser = adminPage.getListRowsCount();

        // Assert that the rows count increased by 1
        Assert.assertEquals(recordsCountBeforeAddingUser + 1, recordsCountAfterAddingUser, "the rows count should be " + recordsCountBeforeAddingUser + 1);


    }

    @Test
    @Description("Check that the user can search by username ")
    public void searchByUserName() {


        adminPage.searchByUsername();

    }

    @Test
    @Description("Check that the user can delete user and the list decreased by 1 ")
    public void deleteUser() {
        //Get the records number before deleting user
        int recordsCountBeforeDeletingUser = adminPage.getListRowsCount();

        adminPage.deleteUser();

        //Get the records number after deleting user
        int recordsCountAfterDeletingUser = adminPage.getListRowsCount();

        // Assert that the rows count decreased by 1
        Assert.assertEquals(recordsCountBeforeDeletingUser - 1, recordsCountAfterDeletingUser, "The records should be =" + recordsCountAfterDeletingUser);

    }


}
