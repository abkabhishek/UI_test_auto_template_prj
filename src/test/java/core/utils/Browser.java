package core.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {

    public WebDriver getDriver(String browserName) {

        WebDriver driver;

        switch (browserName){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
                driver = new ChromeDriver();
                driver.manage().window().maximize();

                break;


        }

        return driver;


    }




}



