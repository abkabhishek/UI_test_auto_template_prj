package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import core.utils.ConfigMan;

public class LoginTests extends BaseTests {

    @Test
    public void testLoadPage(){
        app.openBaseUrl();
        Assert.assertEquals(app.driver.getTitle(),"INDIchat","Page title is not matched");
    }

    @Test
    public void testLoginChat(){

        app.loginPage.performLogin("abk","Game");
        Assert.assertEquals(app.driver.getTitle(),"INDIchat-chat room","Page title is not matched");


    }


}
