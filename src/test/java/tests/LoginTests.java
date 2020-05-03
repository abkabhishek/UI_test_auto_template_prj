package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests {

    @Test
    public void testLoadPage(){
        app.basePage.openBaseUrl();
        Assert.assertEquals(app.basePage.driver.getTitle(),"INDIchat","Page title is not matched");
    }

    @Test
    public void testLoginChat(){

        app.loginPage.performLogin("abk","Game");
        Assert.assertEquals(app.basePage.driver.getTitle(),"INDIchat-chat room","Page title is not matched");


    }


}
