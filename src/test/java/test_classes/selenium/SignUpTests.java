package test_classes.selenium;

import org.testng.annotations.*;
import proj.selenium.pages.MainPage;
import proj.selenium.pages.SignUpPage;
import proj.selenium.utils.Log4Test;
import proj.selenium.utils.PropertyLoader;
import proj.selenium.utils.ThumbnailGenerator;

import java.io.IOException;


@Test
public class SignUpTests extends Fixture {

    /*
     *  Test1: - enter to the system;
     *         - check main page;
     *         - switch to sing up page;
     *         - test sign up without fields fill
     */
    @Test
    public void test1() throws Exception {

        Log4Test.start("test1 => sign up without fields fill");

        mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.checkCorrectSwitchToMainPage();

        //mainPage.makeScreenshotForPhantom("login");

        mainPage.clickOnSignUpLink();
        mainPage.checkCorrectSwitchToSignUpPage();
        signUpPage = new SignUpPage(driver);
        signUpPage.signUpWithoutFieldsFill();

        Log4Test.end("test1");
    }


    /*
     *  Test2: - fill Username field - name is already taken
     *         - pass length not equals conf pass
     *         - incorrect email
     *         - check CAPTCHA
     */
    @Test
    public void test2() throws Exception {
        Log4Test.start("test2 => check sing up fields");

        //signUpPage.switchToSignUp();

        signUpPage.isCheckUsernameField();
        signUpPage.isCheckPassField();
        signUpPage.isCheckEmailField();
        signUpPage.isChangeGenderAndCheckCAPTCHA();
        signUpPage.isSignUpSuccess();

        Log4Test.end("test2");
    }


    /*
     *  Test3: - refresh page
     *          - check that fields are empty
     *          - click signUp link
     *          - check correct switch to MainPage
     */
    @Test
    public void test3() throws Exception {
        Log4Test.start("test3 => check sing up fields");

        signUpPage.refreshSignUpPage();
        signUpPage.isFieldsEmpty();
        signUpPage.switchToMainPage();
        signUpPage.checkCorrectSwitchToMainPage();

        Log4Test.end("test3");
    }

}
