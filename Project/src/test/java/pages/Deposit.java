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
import uistore.Locators2;
import uistore.Locators3;
import utils.excelReadFile;
import utils.Reporter;
public class Deposit  {

    private Map<String, String> testData; 
    private WebDriverHelper webDriverHelper; // Declare a WebDriverHelper instance


    java.util.logging.Logger log = LoggerHandler.getLogger();
    excelReadFile file = new excelReadFile();
    Reporter reporter = new Reporter();;
    public Deposit(WebDriver driver) {
        webDriverHelper = new WebDriverHelper(driver);
    }

public void Deposit_Amount(WebDriver driver)throws IOException {
            
            Map<String, String> testData = excelReadFile.readTestData("/home/coder/project/workspace/Project/testdata/Testdata.xlsx", "Sheet1");
            String depositAmount = testData.get("depositAmount");
            ExtentTest test = Reporter.generateExtentReport().createTest("Deposit Test", "Execution for Deposit  Function");

        try{
        // Click on the Deposit link
            try{
            Duration timeout = Duration.ofSeconds(10);
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            // WebElement depositLink = wait.until(ExpectedConditions.elementToBeClickable(Locators2.depositLink));
            // depositLink.click();
            webDriverHelper.clickElement(Locators2.depositLink);
            test.log(Status.PASS, "Click on the Deposit link");
            }
            catch(Exception ex){
            ex.printStackTrace();
            String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, " Error in Deposit");
            test.fail("Failed to Perform  Deposit", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
     
        }
        // Click on the Account Type

        try{
            Select accType=new Select(driver.findElement(Locators2.accType));
            accType.selectByVisibleText("Individual Checking (Standard Checking)");
            test.log(Status.PASS, "Selecte the Acc type");


        }catch(Exception ex){
            ex.printStackTrace();
            String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, " Error in Selecting_the_Acc_type");
            test.fail("Failed to Perform  Selecting_the_Acc_type", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
     
        }
    // Enter the Amount

        try{
           
            // driver.findElement(Locators2.amount).sendKeys(depositAmount);
            webDriverHelper.fillForm(Locators2.amount, depositAmount);

            log.info("Amount has been sent");
            test.log(Status.PASS, "Enter the Amonut");
        }
        catch(Exception ex){
            ex.printStackTrace();
            String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, " Error in Entering_the_Amonut");
            test.fail("Failed to Perform  Entering_the_Amonut", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
     
        }

        //click on SUbmit 
        try{
            // driver.findElement(By.xpath(Locators2.submitAcc)).click();
            By submit= By.xpath(Locators2.submitAcc);
            webDriverHelper.clickElement(submit);

            
             test.log(Status.PASS, "Click  On  Submit");
        }
     catch(Exception ex){
            ex.printStackTrace();
            String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, " Error in Clicking_Submit");
            test.fail("Failed to Perform  Clicking_Submit", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
     
        }
        
        
        }catch(Exception ex){
            ex.printStackTrace();
            String base64Screenshot = Reporter.captureScreenshotAsBase64(driver, " Deposit Test");
            test.fail("Failed to Perform Deposit Test", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
     
        }
           
        
    }
}