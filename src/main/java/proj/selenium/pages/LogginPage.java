package proj.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import proj.selenium.lib.Web;
import proj.selenium.utils.Log4Test;
import proj.selenium.utils.WebDriverWrapper;

import java.io.IOException;

import static proj.selenium.lib.ConfigData.ui;

/**
 * Created by ViTaLES on 01.07.2015.
 */
public class LogginPage extends Page{

    Web web;

    public LogginPage(WebDriverWrapper dr) throws Exception {
        super(dr);
        web = new Web(driver);
    }


    public void loginWithoutFieldsFill() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.clickButton("LoginLink");
        web.clickButton("LoginButton");
        web.waitForElementPresent("ErrorMassage");

        if (driver.findElement(ui("ErrorMassage")).getText().equals("Error: Register before submission.")) {
            Log4Test.info("Error massage present and correct. Correct work with empty fields");
        } else {
            Log4Test.error("INCORRECT Error massage == " + driver.findElement(ui("ErrorMassage")).getText());
            Assert.fail("Incorrect login Without Fields Fill");
        }
    }


    public void checkIncorrectLoginFieldFill() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.clickLink("LoginLink");
        web.waitForElementPresent("Loginform");
        web.input("LogginField", "test*test!111");
        web.clickButton("LoginButton");

        if (driver.findElement(ui("ErrorMassage")).getText().equals("Error: Password not entered.")) {
            Log4Test.info("Correct work with IncorrectLogin");
        } else {
            Log4Test.error("Incorrect work with IncorrectLogin");
            Assert.fail("Incorrect switch from login form");
        }

    }


    public void checkIncorrectPasswordFieldFill() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.clickLink("LoginLink");
        web.waitForElementPresent("Loginform");
        web.input("PasswordField", "test*test!111");
        web.clickButton("LoginButton");

        if (driver.findElement(ui("ErrorMassage")).getText().equals("Error: Register before submission.")) {
            Log4Test.info("Correct work with IncorrectPassword");
        } else {
            Log4Test.error("Incorrect work with IncorrectPassword");
            Assert.fail("Incorrect switch from login form");
        }



    }


    public void checkRememberMeCheckBox() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.clickLink("LoginLink");
        web.waitForElementPresent("Loginform");
        web.input("LogginField", "test*test!111");

        web.clickLink("RememberMeCheckButton");

        web.clickButton("LoginButton");
        web.waitForElementPresent("LogginUsername");


        if (driver.findElement(ui("ErrorMassage")).getText().equals("Error: Password not entered.") &&
                driver.findElement(ui("LogginUsername")).getAttribute("value").equals("test*test!111")) {
            Log4Test.info("RememberMeCheckBox works correct");
        } else {
            Log4Test.error("RememberMeCheckBox works INCORRECT. ErrorMassage - " + driver.findElement(ui("ErrorMassage")).getText());
            Assert.fail("CheckBox works INCORRECT. checkRememberMeCheckBox phase");
        }
    }


    public void checkCloseButton() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        web.clickLink("LoginLink");
        web.waitForElementPresent("Loginform");
        web.clickButton("CloseButton");

        if (driver.findElement(ui("ErrorMassage")).isDisplayed()) {
            Log4Test.info("CloseButton works correct");
        } else {
            Log4Test.error("CloseButton works INCORRECT");
            Assert.fail("CloseButton works INCORRECT. checkCloseButton phase");
        }
    }


    public void checkForgotLink() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.clickLink("LoginLink");
        web.waitForElementPresent("Loginform");
        web.clickButton("ForgotLink");


        if (web.waitForElementPresent("ResetPasswordUsername")) {
            Log4Test.info("ForgotLink work correct");
        } else {
            Log4Test.error("ForgotLink work INcorrect");
            Assert.fail("ForgotLink work INcorrect. checkForgotLink phase");
        }
    }


    public void checkIncorrectLoginUsername() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        web.input("ResetPasswordUsername", "hh");
        web.clickButton("ResetPasswordSubmit");

        if (web.waitForElementPresent("ErrorMassage") &&
                driver.findElement(ui("ErrorMassage")).getText().equals("Error: No account found with that username.")) {
            Log4Test.info("Correct error on IncorrectLoginUsername");
        } else {
            Log4Test.error("IncorrectLoginUsername error");
            Assert.fail("IncorrectLoginUsername error. CheckIncorrectLoginUsername phase");
        }

    }


    public void checkCorrectLoginUsername() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        web.input("ResetPasswordUsername", "12345");
        web.clickButton("ResetPasswordSubmit");
        web.waitForElementPresent("GoodMassage");

        if (driver.findElement(ui("GoodMassage")).getText().equals("A reset password link has been sent to your e-mail address.")) {
            Log4Test.info("Correct work of reset password functional");
        } else {
            Log4Test.error("Correct work of reset password functional");
            Assert.fail("Correct work of reset password functional. checkCorrectLoginUsername phase");
        }
    }


    public void checkSingUpLink() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.clickLink("LoginLink");
        web.waitForElementPresent("Loginform");
        web.clickButton("SignUpLink");

    }


    public void correctLoginToSystem() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.clickLink("LoginLink");
        web.input("LogginField", "vitales");
        web.input("PasswordField", "12345");
        web.clickButton("LoginButton");

        if (web.waitForElementPresent("BeTheFirstTitle") &&
                driver.findElement(ui("BeTheFirstTitle")).getText().equals("Be the First to Discover the Next Great Website!")) {
            Log4Test.info("Login was correct");
        } else {
            Log4Test.error("Login was INCORRECT");
            Assert.fail("Login was INCORRECT. correctLoginToSystem phase");
        }
    }


    public void LogOutOfTheSystem() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.clickLink("ProfileDropDown");
        web.waitForElementPresent("LogUotDropDownValue");
        web.clickLink("LogUotDropDownValue");
        web.waitForElementPresent("Logo");

/*

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(ui("ProfileDropDown")))
                .moveToElement(driver.findElement(ui("LogUotDropDownValue")))
                .click()
                .perform();
*/


        //new Select(driver.findElement(ui("ProfileDropDown"))).selectByVisibleText("Logout");


    }


}
