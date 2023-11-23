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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import utils.Screenshot;
import utils.WebDriverHelper;
import uistore.Locators1;
import uistore.Locators3;
import utils.excelReadFile;
import utils.Reporter;
public class Withdraw {

    private Map<String, String> testData; 
    private WebDriverHelper webDriverHelper; // Declare a WebDriverHelper instance


    java.util.logging.Logger log = LoggerHandler.getLogger();
    excelReadFile file = new excelReadFile();
    Reporter reporter = new Reporter();
    
    public Withdraw(WebDriver driver) {
        // Initialize the WebDriverHelper with the WebDriver instance
        webDriverHelper = new WebDriverHelper(driver);
    }



  public void WithdrawTest(WebDriver driver)throws IOException {
            Map<String, String> testData = excelReadFile.readTestData("/home/coder/project/workspace/Project/testdata/Testdata.xlsx", "Sheet1");
            String withdrawAmount = testData.get("Wamt");
            ExtentTest test = Reporter.generateExtentReport().createTest("Deposit Test", "Execution for Deposit  Function");
try{
        try{
            Duration timeout = Duration.ofSeconds(10);
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            driver.findElement(Locators3.getWithdrawLinkLocator()).click();
            // webDriverHelper.clickElement(Locators3.getWithdrawLinkLocator);

            test.log(Status.PASS, "Click on the Withdrea link");
        }
        catch(Exception ex){
            ex.printStackTrace();
            String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, " Error in Withdrea link");
            test.fail("Failed to Perform  Withdrea link", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
     
        }try{
            Select accType=new Select(driver.findElement(Locators3.accType));
            accType.selectByVisibleText("Individual Checking (Standard Checking)");

            test.log(Status.PASS, "Select the Account type");

        }catch(Exception ex){
            ex.printStackTrace();
            String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, " Error in Account type");
            test.fail("Failed to Perform  Account type", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
     
        }try{
            webDriverHelper.fillForm(Locators3.amount, withdrawAmount);
             test.log(Status.PASS, "Enter the withdrawAmount");
        }catch(Exception ex){
            ex.printStackTrace();
            String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, " Error in withdrawAmount");
            test.fail("Failed to Perform  withdrawAmount", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
     
        }

        driver.findElement(By.xpath(Locators3.submitAcc)).click();

    }
    catch(Exception ex){
        ex.printStackTrace();
        String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, " Error in withdrawAmount");
        test.fail("Failed to Perform  withdrawAmount", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
 
    }
    }
}