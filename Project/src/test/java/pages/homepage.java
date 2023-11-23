
package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ConfigReader;
import utils.LoggerHandler;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.*;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import utils.Screenshot;
import utils.WebDriverHelper;
import uistore.Locators1;
import utils.excelReadFile;
import utils.Reporter;
import utils.LoggerHandler;

public class homepage {

    private Map<String, String> testData;
    private WebDriverHelper webDriverHelper; // Declare a WebDriverHelper instance

    java.util.logging.Logger log = LoggerHandler.getLogger();
    excelReadFile file = new excelReadFile();
    Reporter reporter = new Reporter();
    ConfigReader configReader = new ConfigReader();
    String browserName = configReader.getBrowserName(); 
    

    public homepage(WebDriver driver) {
        webDriverHelper = new WebDriverHelper(driver);
        log.info("1234");
    }

    public void Valid_Login_TC(WebDriver driver) throws IOException {
        log.info(("qwert"));
        log.info(browserName);
        Map<String, String> testData = excelReadFile.readTestData("/home/coder/project/workspace/Project/testdata/Testdata.xlsx", "Sheet1");
        String username = testData.get("username");
        String password = testData.get("password");
        ExtentTest test = Reporter.generateExtentReport().createTest("Login Test", "Execution for Login Function");

        try {
            test.log(Status.PASS, "Browser opened");

            try {
                webDriverHelper.fillForm(Locators1.username, username);
                test.log(Status.PASS, "Enter Username");
            } catch (Exception ex) {
                ex.printStackTrace();
                String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, "UsernameEntryError");
                test.fail("Failed to Enter Username", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            }


            try {
                webDriverHelper.fillForm(Locators1.password, password);
                test.log(Status.PASS, "Enter Password");
            } catch (Exception ex) {
                ex.printStackTrace();
                String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, "PasswordEntryError");
                test.fail("Failed to Enter Password", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            }

            // Click on submit
            try {
                // driver.findElement(Locators1.submit).click();
                webDriverHelper.clickElement(Locators1.submit);
                test.log(Status.PASS, "Click on submit");
            } catch (Exception ex) {
                ex.printStackTrace();
                String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, "Error");
                test.fail("Failed to submit", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, "Valid_Login_TC");
            test.fail("Failed to Perform Valid_Login_TC", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        }
    }
}
