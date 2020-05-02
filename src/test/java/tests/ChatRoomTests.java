package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasePage;

public class ChatRoomTests extends BaseTests {

    public BasePage app2;

    /*

    ChatRoom Tests:

    1. Validate Joining Room - Welcome message, room name, list of room users.
    2. Validate chat room when someone else join - welcome message, users list.
    3. Validate sending message
    4. Validate leaving chat room by Logout
    5. Validate leaving chat room by closing browser tab.
    5. Validate all messages are specific to  one room only.

     */


    @Test
    public void testValidateJoiningRoom(){

        app.loginPage.performLogin("Abk","Game");
        Assert.assertEquals(app.chatPage.getUsername(),"Abk","Username is not matching");
        Assert.assertTrue(app.chatPage.getRoomName().contains("Game"),"Room name is not matching");
        Integer msgCount = app.chatPage.getChatMessageCount();
        var welcomeMessage = app.chatPage.getNthChatMessage(msgCount-1);
        Assert.assertTrue(welcomeMessage.messageUser.contains("INDI"),"Failed to match");
        Assert.assertEquals(welcomeMessage.messageText,String.format("Hello %s, Welcome to %s-INDIchat","Abk","Game"),"Failed to match");
        Assert.assertTrue(app.chatPage.getListOfUsers().contains("Abk"));
    }

    @Test
    public void testValidateJoiningRoomByOtherUser(){
        var app2 = getApp();
        app2.openBaseUrl();
        app.loginPage.performLogin("Abk","Game");
        app2.loginPage.performLogin("Roop","Game");
        Assert.assertEquals(app.chatPage.getUsername(),"Abk","Username is not matching");
        Assert.assertTrue(app.chatPage.getRoomName().contains("Game"),"Room name is not matching");
        app2.close();
    }

}
