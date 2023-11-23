package uistore;

import org.openqa.selenium.By;

public class Locators3 {
    public static By getWithdrawLinkLocator() {
        return By.xpath("//a[@id='withdraw-menu-item']");
    }
    public static By accType = By.id("selectedAccount");
    public static By amount = By.id("amount");
    public static String submitAcc = "//button[text()=' Submit']";

}