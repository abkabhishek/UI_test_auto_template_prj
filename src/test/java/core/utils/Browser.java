package core.utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.sound.midi.Soundbank;
import java.net.MalformedURLException;
import java.net.URL;


public class Browser {

    public WebDriver getDriver(String browserName) {

        WebDriver driver= null;

        switch (browserName){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
                driver = new ChromeDriver();
//                driver.manage().window().maximize();
                break;
            case "remotechrome":
                System.out.println("Running on Remote");
                String nodeURL = "http://localhost:4444/wd/hub";
                DesiredCapabilities capability = DesiredCapabilities.chrome();
                capability.setBrowserName("chrome");
                capability.setPlatform(Platform.LINUX);
                try {
                    driver = new RemoteWebDriver(new URL(nodeURL), capability);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                }finally {
                    break;
                }
            default:
                System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
                driver = new ChromeDriver();
                driver.manage().window().maximize();

                break;


        }
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();


        }
        return driver;
    }




}



