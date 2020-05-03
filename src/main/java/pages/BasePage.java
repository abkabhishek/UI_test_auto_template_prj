package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public WebDriver driver;
    public static final String baseUrl = "http://localhost:3000";

    public By loginPageElemUsername = new By.ById("username");
    public By chatPageElemHeaderUsername = new By.ById("header-username");



    public BasePage(WebDriver driverObj) {
        driver = driverObj;
//
//        loginPage = new LoginPage(driver);
//        chatPage = new ChatPage(driver);

    }


    public void openBaseUrl(){
        driver.get(baseUrl);
    }


    public void quit(){
        driver.quit();
    }

    public void close(){
        driver.close();
    }

    public void waitForPageByName(String pageName){
        if(pageName.equals("loginpage")){
            waitForElemByVisibility(loginPageElemUsername,3);

        }else if(pageName.equals("chatpage")){
            waitForElemByVisibility(chatPageElemHeaderUsername,3);

        }else{
            System.out.println("Invalid page");
        }
    }


    public void waitForElemByVisibility(By elemLocator,Integer waitSeconds){
        WebDriverWait wait = new WebDriverWait(driver,waitSeconds);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(elemLocator)));
    }

}
