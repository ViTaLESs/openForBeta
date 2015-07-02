package test_classes.selenium;

import org.testng.annotations.*;
import proj.selenium.pages.LogginPage;
import proj.selenium.pages.MainPage;
import proj.selenium.pages.SignUpPage;
import proj.selenium.utils.Log4Test;
import proj.selenium.utils.PropertyLoader;
import proj.selenium.utils.WebDriverFactory;
import proj.selenium.utils.WebDriverWrapper;


public class Fixture {
    public static WebDriverWrapper driver;
    public MainPage mainPage;
    public SignUpPage signUpPage;
    public LogginPage logginForm;

    @BeforeSuite
    public void setUp() throws Exception {
        driver = WebDriverFactory.initDriver(PropertyLoader.loadProperty("browser.name"));
        Log4Test.info("Start Test Suite execution");
    }

    @AfterSuite
    public void tearDown() throws Exception {
        if (driver != null){
            driver.quit();
        }
        Log4Test.info("Tests Suite execution completed.");
    }




}
