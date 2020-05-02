package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    public WebDriver driver;
    public final String baseUrl;

    //    all page objects
    public LoginPage loginPage;
    public ChatPage chatPage;


    public BasePage(WebDriver driverObj,String bUrl) {
        driver = driverObj;

        baseUrl = bUrl;

        loginPage = new LoginPage(driver);
        chatPage = new ChatPage(driver);

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




}
