package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Equivalence.Wrapper;

import java.time.Duration;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelDataProvider{ // Lets us read the data
        ChromeDriver driver;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */

        

        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
        }
        @Test(priority = 1,enabled = true, description = "URL assertion and about page message printing")
        public void testCase01() throws InterruptedException{
                System.out.println("Started testCase01");
                String URL = "https://www.youtube.com/";
                Assert.assertTrue(Wrappers.navigateToUrl(driver, URL),"Navigation to the URL failed.");
                Wrappers.clickElement(driver, By.xpath("//a[contains(text(),'About')]"));
                Wrappers.printMessage(driver);
                System.out.println("Ended testCase01");
        }

        @Test(priority = 2,enabled = true, description = "Assertion for the movie is marked “A” for Mature or not and whether the movie is either “Comedy” or “Animation")
        public void testCase02() throws InterruptedException{
                System.out.println("Started testCase02");
                String URL = "https://www.youtube.com/";
                Assert.assertTrue(Wrappers.navigateToUrl(driver, URL),"Navigation to the URL failed.");
                Wrappers.clickOnTab(driver,"Movies");
                Wrappers.clickOnNextButton(driver, "Top selling",3);
                Wrappers.maturityLastOfMovie(driver);
                Wrappers.genreOfLastMovie(driver);  
                System.out.println("Ended testCase02");
        }


        @Test(priority = 3, enabled = true,description = "URL assertion and about page message printing")
        public void testCase03() throws InterruptedException{
                System.out.println("Started testCase03");
                String URL = "https://www.youtube.com/";
                Assert.assertTrue(Wrappers.navigateToUrl(driver, URL),"Navigation to the URL failed.");
                Wrappers.clickOnTab(driver,"Music");
                Wrappers.jumpToTheFirstSection(driver);
                Wrappers.clickOnNextButton(driver, "Biggest Hits",3);
                Wrappers.nameOfLastPlayList(driver,"Biggest Hits");
                Wrappers.noOfTracks(driver,"Biggest Hits","Bollywood Dance");
                System.out.println("Ended testCase03");
        }

        @Test(priority = 4, enabled = true, description = "URL assertion and about page message printing")
        public void testCase04() throws InterruptedException{
                System.out.println("Started testCase04");
                String URL = "https://www.youtube.com/";
                Assert.assertTrue(Wrappers.navigateToUrl(driver, URL),"Navigation to the URL failed.");
                Wrappers.clickOnTab(driver, "News");
                Wrappers.titleOfNews(driver);
                Wrappers.sumOfTheLikes(driver);
                System.out.println("Ended testCase04");
        }

        @Test(priority = 5, enabled = true, description = "URL assertion and about page message printing")
        public void testCase05() throws InterruptedException{
                System.out.println("Started testCase05");
                String URL = "https://www.youtube.com/";
                Assert.assertTrue(Wrappers.navigateToUrl(driver, URL),"Navigation to the URL failed.");
                System.out.println("Ended testCase05");
        }



        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}