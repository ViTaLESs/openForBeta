package proj.selenium.pages;


import proj.selenium.utils.*;

import java.io.IOException;

import static proj.selenium.lib.ConfigData.ui;


public abstract class Page {
    private String PAGE ;
    public WebDriverWrapper driver;


    public Page(WebDriverWrapper dr, String page){
        driver = dr;
        PAGE = page;
    }

    public Page(WebDriverWrapper dr) throws Exception {
        driver = dr;
    }

    public boolean openPage(){
        try{
            Log4Test.info("Start open page.");
            driver.get(PAGE);
        } catch (Exception e){
            Log4Test.error("Error in open page.");
            return false;
        }
        Log4Test.info("Page open successful.");
        return true;
    }

    public boolean isOpenPage(String checkLocator){
        try{
            Log4Test.info("PAGE: Check is page open. " + checkLocator + " is present!");
            //sleep(5);
            driver.findElement(ui(checkLocator)).isEnabled();
        } catch (Exception e){
            Log4Test.error("PAGE: Error with check page.");
            return false;
        }
        Log4Test.info("PAGE: Page is open.");
        return true;
    }

    public void makeScreenshotForPhantom(String picName) {
        ThumbnailGenerator tg = new ThumbnailGenerator(driver);
        tg.setSize(1200, 800);
    //    tg.setBackgroundColor("#fff");

        try {
            tg.saveThumbnail("log.jpg");
        } catch (IOException ioe) {
            Log4Test.info("It was not possible make a login screenshot");
        }
    }

    public void sleep(int sec){
        try {
            Log4Test.info("Sleap "+sec+" sec.");
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}