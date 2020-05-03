package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

//    public WebDriver driver;


    public By username = new By.ById("username");
    public By roomSelect = new By.ById("room");
    public By loginButton = new By.ByCssSelector("button[type=\"submit\"]");


    public LoginPage(WebDriver driverObj) {
        super(driverObj);
//        driver = driverObj;
    }

    public void waitForPage(){

        waitForPageByName("loginpage");
    }


    public void inputUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }


    public void selectRoom(String option) {
        getDropdownSelect(roomSelect).selectByVisibleText(option);
    }

    private Select getDropdownSelect(By elem) {
        return new Select(driver.findElement(elem));

    }


    public void clickLoginButton() {

        driver.findElement(loginButton).click();

    }

    public void performLogin(String user, String room) {

        inputUsername(user);
        selectRoom(room);
        clickLoginButton();

        waitForPageByName("chatpage");



    }


}
