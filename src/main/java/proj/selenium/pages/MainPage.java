package proj.selenium.pages;


import proj.selenium.lib.Web;
import proj.selenium.utils.Log4Test;
import proj.selenium.utils.PropertyLoader;
import proj.selenium.utils.WebDriverWrapper;

import java.io.IOException;

import static proj.selenium.lib.ConfigData.ui;


public class MainPage extends Page{

    private static final String MAIN_PAGE = PropertyLoader.loadProperty("site.url");
    Web web;

    public MainPage(WebDriverWrapper driver) throws IOException {
        super(driver, MAIN_PAGE);
        web = new Web(driver);
    }

    public void checkCorrectSwitchToMainPage() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        if (driver.findElement(ui("Logo")).isEnabled()) {
            Log4Test.info("Correct enter to the system");
        } else {
            Log4Test.error("Incorrect enter to the system");
        }
    }


    public void clickOnSignUpLink() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.clickLink("SingUpLink");
        Log4Test.info("Press SignUpLink");
    }


    public void checkCorrectSwitchToSignUpPage() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        if (driver.getTitle().equals("Open for Beta - Sign up") && driver.findElement(ui("SingUpUsernameField")).isEnabled()) {
            Log4Test.info("SwitchToSignUpPage was correct");
        } else {
            Log4Test.error("SwitchToSignUpPage was INCORRECT");
        }
    }


    public void switchToLoginForm() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        web.clickButton("LoginLink");
        Log4Test.info("Press SignUpLink");

        web.waitForElementPresent("Loginform");
        if (driver.findElement(ui("LogginField")).isDisplayed()) {
            Log4Test.info("Switch to LoginForm was correct");
        } else {
            Log4Test.error("Switch to LoginForm was INCORRECT");
        }

    }
}
