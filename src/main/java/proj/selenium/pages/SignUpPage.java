package proj.selenium.pages;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import proj.selenium.lib.Web;
import proj.selenium.utils.Log4Test;
import proj.selenium.utils.PropertyLoader;
import proj.selenium.utils.WebDriverWrapper;

import java.io.IOException;

import static proj.selenium.lib.ConfigData.ui;

/**
 * Created by ViTaLES on 30.06.2015.
 */
public class SignUpPage extends Page{
    Web web;

    boolean isSignUp = false;

    public SignUpPage(WebDriverWrapper driver) throws Exception {
        super(driver);
        web = new Web(driver);
    }

    public void checkCorrectSwitchToMainPage(){
        if (isOpenPage("Logo") && driver.getTitle().equals("Open for Beta")) {
            Log4Test.info("Correct enter to the system");
        } else {
            Log4Test.error("INCORRECT enter to the system");
            Assert.fail();
        }
    }


    public void signUpWithoutFieldsFill() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.clickButton("CreateAccountButton");
        web.waitForElementPresent("ErrorMassage");

        if (driver.findElement(ui("ErrorMassage")).getText().equals("Error: Register before submission.")) {
            Log4Test.info("Error massage present and correct");
        } else {
            Log4Test.error("INCORRECT Error massage == " + driver.findElement(ui("ErrorMassage")).getText());
            Assert.fail("Incorrect signUp Without Fields Fill");
        }
    }


    public boolean isCheckUsernameField() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.input("SingUpUsernameField", "12345");
        web.clickButton("CreateAccountButton");

        if (web.waitForElementPresent("ErrorMassage") &&
                driver.findElement(ui("ErrorMassage")).getText()
                        .equals("Error: The username you entered is already taken.")) {
            Log4Test.info("Username is already taken");
            isSignUp = true;
            return true;
        } else {
            Log4Test.error("Error with the same username value.");
            isSignUp = false;
            return isSignUp;
        }

    }


    public boolean isCheckPassField() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.input("SingUpUsernameField", "test100500");
        web.input("SingUpPasswordField", "12345");
        web.input("SingUpConfirmPasswordField", "1234");
        web.clickButton("CreateAccountButton");

        if (web.waitForElementPresent("ErrorMassage") &&
                driver.findElement(ui("ErrorMassage")).getText()
                        .equals("Error: The password you entered does not match the confirm password.")) {
            Log4Test.info("Password Field work correct");
            isSignUp = true;
            return true;
        } else {
            Log4Test.error("INCORRECT Password Field work");
            isSignUp = false;
            return isSignUp;
        }
    }


    public boolean isCheckEmailField() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

        // check empty email field
        web.input("SingUpUsernameField", "test100500");
        web.input("SingUpPasswordField", "12345");
        web.input("SingUpConfirmPasswordField", "12345");
        web.clickButton("CreateAccountButton");

        web.waitForElementPresent("ErrorMassage");

        boolean emptyEmailField = driver.findElement(ui("ErrorMassage")).getText()
                .equals("Error: Please enter your e-mail address.");

        web.waitForInvisibilityOfElement("ErrorMassage"); //TODO
        Log4Test.info("Correct work with empty email field");


        // check incorrect email format
        web.input("EmailField", "12345");
        web.clickButton("CreateAccountButton");
        web.waitForElementPresent("ErrorMassage");

        boolean incorrectEmailField = driver.findElement(ui("ErrorMassage")).getText()
                .equals("Error: Your e-mail address is invalid.");

        web.waitForInvisibilityOfElement("ErrorMassage"); //TODO
        Log4Test.info("Correct work with incorrect email format");



        // check correct email format
        web.input("EmailField", "12345@test.com");
        web.clickButton("CreateAccountButton");
        web.waitForElementPresent("ErrorMassage");

        boolean correctEmail = driver.findElement(ui("ErrorMassage")).getText()
                .equals("Error: Image code wrong, please try again.");

        web.waitForInvisibilityOfElement("ErrorMassage"); //TODO
        Log4Test.info("Correct work with test email format  =>  12345@test.com");


        if (emptyEmailField && incorrectEmailField && correctEmail) {
            Log4Test.info("Correct work of email field");
            isSignUp = true;
            return true;
        } else {
            Log4Test.error("INCORRECT work of email field");
            isSignUp = false;
            return isSignUp;
        }
    }


    public boolean isChangeGenderAndCheckCAPTCHA() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

        // check dropdown
        web.clickButton("GenderDropDown");
        web.clickButton("Female_DropDownValue");
        Log4Test.info("Female_DropDown isSelected -> " + driver.findElement(ui("Female_DropDownValue")).isSelected());

        web.clickButton("GenderDropDown");
        web.clickButton("Male_DropDownValue");
        Log4Test.info("Male_DropDown isSelected -> " + driver.findElement(ui("Male_DropDownValue")).isSelected());

        // check Captcha as much as possible!
        web.input("Captcha", "12345");
        web.clickButton("CreateAccountButton");
        web.waitForElementPresent("ErrorMassage");

        if (driver.findElement(ui("ErrorMassage")).getText().equals("Error: Image code wrong, please try again.")) {
            Log4Test.info("Captcha work correct");
            isSignUp = true;
            return isSignUp;
        } else {
            Log4Test.info("Captcha work INCORRECR");
            isSignUp = false;
            return isSignUp;
        }
    }


    public void isSignUpSuccess(){
        if (isSignUp) {
            Log4Test.info("SignUp work Success");
        } else {
            Log4Test.error("SignUp work INCORRECT!");
            Assert.fail("SignUp work INCORRECT!");
        }
    }


    public void refreshSignUpPage() {
        web.refreshPage();
    }


    public boolean isFieldsEmpty() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        web.waitForElementPresent("SingUpUsernameField");

        if (driver.findElement(ui("SingUpUsernameField")).getText().equals("")
                && driver.findElement(ui("SingUpPasswordField")).getText().equals("")
                && driver.findElement(ui("SingUpConfirmPasswordField")).getText().equals("")
                && driver.findElement(ui("EmailField")).getText().equals("")) {
            Log4Test.info("SingUp Page refreshed correct");
            return true;
        } else {
            Log4Test.error("SingUp Page refreshed INCORRECT");
            return false;
        }
    }


    public void switchToMainPage() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.clickButton("Logo");
    }

}
