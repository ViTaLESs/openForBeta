package proj.selenium.lib;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import proj.selenium.utils.WebDriverWrapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static proj.selenium.lib.ConfigData.ui;


public class Util {
	static Logger log = Logger.getLogger(Util.class);
	static WebDriver driver;
    static WebElement webElement;
	Web web;
	
	public Util (WebDriverWrapper driver) throws IOException {
		this.driver = driver;
		web = new Web(driver);
		WebDriverWait waitForElement = new WebDriverWait(driver, 10);
	}
	
	
	/*
	 * Add text at the end of text file
	 */
	public static void addText(String filePath, String text) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true));
		out.write(text);
		out.close();
	}
	/*
	 * Static method that prints List's values in the form of table
	 */
/*	public static void printTable(List<ArrayList<String>> list) throws SQLException, IOException {
		int columnNumber = list.get(0).size();
		int listSize = list.size();

		// Make even length of values
		for (int k = 0; k < columnNumber; k++) {
			for (int i = 0; i < listSize; i++) {
				if ((list.get(i).get(k).toString().length() % 2) == 1) {
					String value = list.get(i).get(k).toString() + " ";
					list.get(i).set(k, value);
				}
			}
		}

		// Add additional spaces before and after the values
		for (int k = 0; k < columnNumber; k++) {
			for (int i = 0; i < listSize; i++) {
				String value = " " + list.get(i).get(k).toString() + " ";
				list.get(i).set(k, value);
			}
		}

		// Create array with every column's length and middle
		int[] columnLength = new int[columnNumber];
		int[] columnMiddle = new int[columnNumber];

		for (int k = 0; k < columnNumber; k++) {
			int maxLength = 0;
			for (int i = 0; i < listSize; i++) {
				if (list.get(i).get(k).toString().length() > maxLength) {
					maxLength = list.get(i).get(k).toString().length();
				}
			}
			columnLength[k] = maxLength;
			columnMiddle[k] = maxLength / 2;
		}

		// Print table delimiter
		for (int k = 0; k < columnNumber; k++) {
			for (int j = 0; j < columnLength[k]; j++) {
				System.out.print("-");
				addText(getCfgValue("TEST_LOG_FILE"), "-");
			}
			System.out.print("|");
			addText(getCfgValue("TEST_LOG_FILE"), "|");
		}
		System.out.print("\n");
		addText(getCfgValue("TEST_LOG_FILE"), "\n");

		// Print row by row
		for (int i = 0; i < listSize; i++) {

			// Print table header delimiter
			if (i == 1) {
				for (int k = 0; k < columnNumber; k++) {
					for (int j = 0; j < columnLength[k]; j++) {
						System.out.print("-");
						addText(getCfgValue("TEST_LOG_FILE"), "-");
					}
					System.out.print("|");
					addText(getCfgValue("TEST_LOG_FILE"), "|");
				}
				System.out.print("\n");
				addText(getCfgValue("TEST_LOG_FILE"), "\n");
			}

			// Print cells in the row
			for (int k = 0; k < columnNumber; k++) {

				int spaceNumber = columnMiddle[k] - (list.get(i).get(k).toString().length() / 2);
				for (int j = 0; j < spaceNumber; j++) {
					System.out.print(" ");
					addText(getCfgValue("TEST_LOG_FILE"), " ");
				}
				System.out.print(list.get(i).get(k));
				addText(getCfgValue("TEST_LOG_FILE"), list.get(i).get(k).toString());
				for (int j = 0; j < spaceNumber; j++) {
					System.out.print(" ");
					addText(getCfgValue("TEST_LOG_FILE"), " ");
				}
				System.out.print("|");
				addText(getCfgValue("TEST_LOG_FILE"), "|");
			}

			System.out.print("\n");
			addText(getCfgValue("TEST_LOG_FILE"), "\n");
		}

		// Print table delimiter
		for (int k = 0; k < columnNumber; k++) {
			for (int j = 0; j < columnLength[k]; j++) {
				System.out.print("-");
				addText(getCfgValue("TEST_LOG_FILE"), "-");
			}
			System.out.print("|");
			addText(getCfgValue("TEST_LOG_FILE"), "|");
		}
		System.out.print("\n");
		addText(getCfgValue("TEST_LOG_FILE"), "\n");
	}*/


    /*
     *  Select RANDOM number from range. The method returns Integer.
     */
    public static Integer selectRandNumberFromRange(int min, int max){
        Random rand=new Random();

        Integer randomNum = rand.nextInt(max - min + 1) + min;
        log.debug("Random value <"+randomNum+"> selected from range "+min+" - "+max);

        return randomNum;
    }


	/*
	 * Wait specified time
	 */
	public static void stay(long time) throws InterruptedException {
		TimeUnit.SECONDS.sleep(time);
	}
	
	
	/*
     * Wait specified time (in minutes) with displaying timer. Minutes value should be >0.
     */
    public static void expect(int min) throws InterruptedException {
        log.info("Waiting "+min+" min...");

        for(int i=0;i<min;i++){
            for(int k=0;k<60;k++){
                System.out.print("\r");
                if(k<10){
                    System.out.print(" "+i+":0" + k);
                }else{
                    System.out.print(" "+i+":" + k);
                }
                stay(1);
            }
        }
        System.out.print("\n");
    }
	
		
	/*
	 * Check if the test failed or not and complete the test.
	 */
	public static void testResult(boolean isTestPassed) {
		if (!isTestPassed) {
			log.error("TEST FAILED");
			Assert.fail();
		}
		log.info("TEST SUCCESSFULLY COMPLETED");

	}

	/**
	 * This method is used to kill all opened Firefox processes
	 */
	//Old Method for kill all processes
	static public void killAllProcesses() {
		try {
			Process process = Runtime.getRuntime().exec("cmd /c Taskkill /IM firefox.exe /F");
			process.waitFor();
			log.info("All Firefox processes closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    //New method for kill all processes
	static public void killAllBrowserProcesses(String driverName) {
		try {
			if (driverName.equals("Google Chrome")) {
				driverName = "chrome";
			}
			if (driverName.equals("Opera")) {
				driverName="opera";
			}
			Process process = Runtime.getRuntime().exec("cmd /c Taskkill /IM "+driverName+".exe /F");
			process.waitFor();
			log.info("All Firefox processes closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//
	public void selectMenuItem(String headerMenuXpath, String submenusXpath) {
	   new Actions(driver).moveToElement(driver.findElement(By.xpath(headerMenuXpath))).perform();
	   log.info("Focus on Menu to open list of submenus");
	   WebElement element = driver.findElement(By.xpath(submenusXpath));
	   element.click();
	   log.info("Click on Submenu");
	}

	//
	public void doFocusInToElementAndClick(String ElementXpath, String linkLocator) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
		new Actions(driver).moveToElement(driver.findElement(ui(ElementXpath))).perform();
        driver.switchTo();
		if (driver.findElement(ui(ElementXpath)).isDisplayed() == true) {
		    driver.findElement(ui(linkLocator)).click();
		} else {
			}
		log.info("Focus in to element");
		 }


	//
	public void clickButtonZapis() throws Exception {
		//driver.findElement(By.xpath("//a[@href='http://qalight.com.ua/zapisatsya-na-kurs/']")).click();
		Set<String> oldWindowsSet = driver.getWindowHandles();
		web.clickButton("Contacts.Button.Zapis");
		log.info("Click <Zapis> button");
		Set<String> newWindowsSet = driver.getWindowHandles();
		newWindowsSet.removeAll(oldWindowsSet);
		String newWindowHandle = newWindowsSet.iterator().next();


		String windowHandler = driver.getWindowHandle();
		driver.switchTo().window(newWindowHandle);
	}

	//
	public void clickOnSomethingAndOpenNewTab() throws Exception {
		Set<String> oldWindowsSet = driver.getWindowHandles();
		driver.findElement(By.xpath("HederRegistrationLink")).click();
		Set<String> newWindowsSet = driver.getWindowHandles();
		newWindowsSet.removeAll(oldWindowsSet);
		
		String newWindowHandle = newWindowsSet.iterator().next();
		driver.switchTo().window(newWindowHandle);		
	}
}

