package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ConfigMan;

public class BaseTests {

    public static WebDriver driver;
    public static ConfigMan config = new ConfigMan();

    @BeforeClass
    public void setUpClass(){

//        Initializing Chrome driver
        System.setProperty("webdriver.chrome.driver", config.getProp("ChromeDriverPath"));
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDownClass(){
        driver.quit();
    }


//    @Test
//    public void testOne(){
//        driver.get(ConfigMan.getProp("TestBaseURL"));
//
//        System.out.println(driver.getTitle());
//
//    }


}
