package proj.selenium.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;


public class ThumbnailGenerator {
    private WebDriver driver;

    public ThumbnailGenerator(WebDriver d) {
        driver = d;
    }

    public void setSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public void setBackgroundColor(String cssValidColor) {
        ((JavascriptExecutor)driver).executeScript("document.body.style.backgroundColor = '"+ cssValidColor +"';");
    }

    public void saveThumbnail(String url, String filePath) throws IOException {
        driver.get(url);
        saveThumbnail(filePath);
    }

    public void saveThumbnail(String filePath) throws IOException {
        File memFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(memFile, new File(filePath));
    }


}
