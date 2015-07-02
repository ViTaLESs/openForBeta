package proj.selenium.utils;

import net.anthavio.phanbedder.Phanbedder;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;


public class WebDriverFactory {

    /* Browsers constants */
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String PHANTOMJS = "phantomjs";

    public static WebDriverWrapper initDriver(String driverName){
        WebDriverWrapper driverWripper = null;

        if(driverName.equals(FIREFOX)){
            driverWripper = new WebDriverWrapper( new FirefoxDriver());
        }else if(driverName.equals(PHANTOMJS)){
            File phantomjs = Phanbedder.unpack();
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjs.getAbsolutePath());
            driverWripper = new WebDriverWrapper( new PhantomJSDriver(caps));

            } else if(driverName.equals(CHROME)){
                    ChromeOptions options = new ChromeOptions();
                    driverWripper = new WebDriverWrapper( new ChromeDriver(options));
                }

                else {
                    Assert.fail("invalid driver name");
                }

       driverWripper.manage().deleteAllCookies();
       driverWripper.manage().window().maximize();

       return driverWripper;
    }

}
