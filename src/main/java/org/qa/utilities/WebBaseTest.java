package org.qa.utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.qa.QACore;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;

public class WebBaseTest extends QACore {
    protected static Properties serverProperties = new Properties();
    private static final String URI = "homePageURL";

    public static ExtentReports extentReports;
    public static ExtentTest logger;

    protected static WebDriver driver;

    protected void loadOnSuiteStart() throws Exception {
        serverProperties = getConfigsValue("Config-Web.properties");
    }

    private void reportHandler() {
        System.out.println(LocalDateTime.now());
        extentReports = new ExtentReports("reports/index/report " + LocalDateTime.now() + ".html");
        extentReports.addSystemInfo("Automation", "BIG Assignment");
        extentReports.addSystemInfo("Author", "Saeed Sokar");
        extentReports.addSystemInfo("version", "1");
    }

    @BeforeSuite
    public void beforeSuite() throws Exception {
        loadOnSuiteStart();
        reportHandler();
    }

    @AfterSuite
    public void afterSuite() throws Exception {
        extentReports.flush();
//        driver.quit();
    }

    @BeforeMethod
    @Parameters("browser")
    public void beforeMethod(Method method, String browser) {
        logger = extentReports.startTest(method.getName());
        if (browser.equalsIgnoreCase("chrome")) {
            openChromeBrowser();
        } else if (browser.equalsIgnoreCase("firefox")) {
            openFireFoxBrowser();
        }
    }

    public void openChromeBrowser() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions opt = new ChromeOptions();
            driver = new ChromeDriver(opt);
            driver.manage().window().maximize();
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.navigate().to(serverProperties.getProperty(URI));
        }
    }

    public void openFireFoxBrowser() {
        if (driver == null) {
            System.setProperty("webdriver.gecko.driver", "./src/main/resources/drivers/geckodriver");
            driver = new FirefoxDriver();
            driver.navigate().to(serverProperties.getProperty(URI));
        }
    }

    @AfterMethod
    public void afterMethod(Method method, ITestResult result) throws InterruptedException {

        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, "Test Failed due to: " + result.getThrowable());
            logger.log(LogStatus.FAIL, logger.getDescription());
        } else {
            logger.log(LogStatus.SKIP, "Test escape as there is no error/exception ");
        }
        captureScreenshot(driver, result.getName());
    }
}
