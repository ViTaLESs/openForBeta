package test_classes.selenium;

import org.testng.annotations.*;
import proj.selenium.pages.LogginPage;
import proj.selenium.pages.MainPage;
import proj.selenium.utils.Log4Test;

/**
 * Created by ViTaLES on 30.06.2015.
 */
public class LoginTests extends Fixture{

    /*
         *  Test4: - switch to login page;
         *         - check correct switch to loginPage;
         *         - test login without fields fill;
         *         - test rememberMeCheckBox;
         *         - test Close Button;
         */
    @Test
    public void test4() throws Exception {
        Log4Test.start("test4 => sign up without fields fill");

        logginForm = new LogginPage(driver);

        logginForm.loginWithoutFieldsFill();
        logginForm.checkIncorrectLoginFieldFill();
        logginForm.checkIncorrectPasswordFieldFill();
        logginForm.checkRememberMeCheckBox();
        logginForm.checkCloseButton();


        Log4Test.end("test4");
    }


    /*
     *  Test5: - test forgot on login form;
     *         - test singUpLink on login form;
     *         - test incorrect username in RESET your Password;
     *         - test correct username in RESET your Password;
     */
    @Test
    public void test5() throws Exception {
        Log4Test.start("test5 => sign up without fields fill");

        logginForm.checkForgotLink();
        logginForm.checkIncorrectLoginUsername();
        logginForm.checkCorrectLoginUsername();
        logginForm.checkSingUpLink();
        mainPage = new MainPage(driver);
        mainPage.checkCorrectSwitchToSignUpPage();

        Log4Test.end("test5");
    }


    /*
     *  Test6: - Correct login to the system;
     */
    @Test
    public void test6() throws Exception {
        Log4Test.start("test6 => login to the system");

        logginForm.correctLoginToSystem();


        Log4Test.end("test6");
    }


    /*
    *  Test7: - Correct logOut fo the system;
    */
    @Test
    public void test7() throws Exception {
        Log4Test.start("test7 => ");

        logginForm.LogOutOfTheSystem();

        Log4Test.end("test7");
    }

}
