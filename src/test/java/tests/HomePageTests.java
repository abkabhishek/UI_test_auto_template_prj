package tests;

import base.BaseTests;
import org.testng.annotations.Test;
import utils.ConfigMan;

public class HomePageTests extends BaseTests {

    @Test
    public void testOne(){
        driver.get(ConfigMan.getProp("TestBaseURL"));

        System.out.println(driver.getTitle());

    }


}
