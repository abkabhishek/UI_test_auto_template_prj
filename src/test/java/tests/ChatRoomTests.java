package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasePage;

public class ChatRoomTests extends BaseTests {


    /*

    ChatRoom Tests:

    1. Validate Joining Room - Welcome message, room name, list of room users.
    2. Validate chat room when someone else join - welcome message, users list.
    3. Validate sending message
    4. Validate leaving chat room by Logout
    5. Validate leaving chat room by closing browser tab.
    5. Validate all messages are specific to  one room only.

     */
    public App app;
    public App app2;

    @BeforeClass
    public void setUpClass() {
        // Initialize app.
        app = getApp();
        app2 = getApp();
    }

    @BeforeMethod
    public void beforeMethod() {
        app.basePage.openBaseUrl();
        app2.basePage.openBaseUrl();
    }

    @AfterMethod
    public void afterMethod() {

    }

    @AfterClass
    public void tearDownClass() {
        app2.basePage.quit();
        app.basePage.quit();
    }


    @Test
    public void testValidateJoiningRoom(){

        app.loginPage.performLogin("Abk","Game");
        Assert.assertEquals(app.chatPage.getUsername(),"Abk","Username is not matching");
        Assert.assertTrue(app.chatPage.getRoomName().contains("Game"),"Room name is not matching");
        var welcomeMessage = app.chatPage.getLastChatMessage();
        Assert.assertTrue(welcomeMessage.messageUser.contains("INDI"),"Failed to match");
        Assert.assertEquals(welcomeMessage.messageText,String.format("Hello %s, Welcome to %s-INDIchat","Abk","Game"),"Failed to match");
        Assert.assertTrue(app.chatPage.getListOfUsers().contains("Abk"));
    }

    @Test
    public void testValidateJoiningRoomByOtherUser(){
        String[] users = new String[]{"Abk","Roop"};
        String[] rooms = new String[]{"Work"};


        app.loginPage.performLogin(users[0],rooms[0]);
        app2.loginPage.performLogin(users[1],rooms[0]);
        var welcomeMessage = app.chatPage.getLastChatMessage();
        Assert.assertTrue(welcomeMessage.messageUser.contains("INDI"),"Failed to match");
        Assert.assertEquals(welcomeMessage.messageText,String.format("%s is joined %s-INDIchat",users[1],rooms[0]),"Failed to match");
        Assert.assertTrue(app.chatPage.getListOfUsers().contains(users[0]));
        Assert.assertTrue(app.chatPage.getListOfUsers().contains(users[1]));

    }

    @Test
    public void testValidateSendingMessage(){
        String[] users = new String[]{"Abk","Roop"};
        String[] rooms = new String[]{"Work"};
        String[] msgs = new String[]{"Hello there!","Wow"};


        app.loginPage.performLogin(users[0],rooms[0]);
        app2.loginPage.performLogin(users[1],rooms[0]);

        app.chatPage.sendMessage(msgs[0]);

        var welcomeMessage = app.chatPage.getLastChatMessage();
        Assert.assertTrue(welcomeMessage.messageUser.contains(users[0]),"Failed to match");
        Assert.assertEquals(welcomeMessage.messageText,msgs[0],"Failed to match");

        var welcomeMessage2 = app2.chatPage.getLastChatMessage();
        Assert.assertTrue(welcomeMessage2.messageUser.contains(users[0]),"Failed to match");
        Assert.assertEquals(welcomeMessage2.messageText,msgs[0],"Failed to match");


    }



    @Test
    public void testValidateLogoutMessageOtherUser(){
        String[] users = new String[]{"Abk","Roop","INDI"};
        String[] rooms = new String[]{"Work"};
        String[] msgs = new String[]{"Hello there!","Wow"};


        app.loginPage.performLogin(users[0],rooms[0]);
        app2.loginPage.performLogin(users[1],rooms[0]);

        app2.chatPage.logOutUser();

        var lastMessage = app.chatPage.getLastChatMessage();
        Assert.assertTrue(lastMessage.messageUser.contains(users[2]),"Failed to match");
        Assert.assertEquals(lastMessage.messageText,String.format("%s has left the chat",users[1]),"Failed to match");


    }


    @Test
    public void testValidateLogoutMessageOtherUserByClosingBrowser(){
        String[] users = new String[]{"Abk","Roop","INDI"};
        String[] rooms = new String[]{"Work"};
        String[] msgs = new String[]{"Hello there!","Wow"};


        app.loginPage.performLogin(users[0],rooms[0]);
        app2.loginPage.performLogin(users[1],rooms[0]);

        app2.basePage.openBaseUrl();

        var lastMessage = app.chatPage.getLastChatMessage();
        Assert.assertTrue(lastMessage.messageUser.contains(users[2]),"Failed to match");
        Assert.assertEquals(lastMessage.messageText,String.format("%s has left the chat",users[1]),"Failed to match");


    }


    @Test
    public void testValidateMessageNotSentToOtherGroup(){
        String[] users = new String[]{"Abk","Roop"};
        String[] rooms = new String[]{"Work","Game"};
        String[] msgs = new String[]{"Hello there!","Wow"};


        app.loginPage.performLogin(users[0],rooms[0]);
        app2.loginPage.performLogin(users[1],rooms[1]);

        app.chatPage.sendMessage(msgs[0]);
        app.chatPage.sendMessage(msgs[1]);

        var welcomeMessage = app.chatPage.getLastChatMessage();
        Assert.assertTrue(welcomeMessage.messageUser.contains(users[0]),"Failed to match");
        Assert.assertEquals(welcomeMessage.messageText,msgs[1],"Failed to match");

        Assert.assertEquals(app2.chatPage.getChatMessageCount().intValue(),1,"Failed to match");
        Assert.assertFalse(app2.chatPage.getLastChatMessage().messageText.contains(msgs[1]),"Failted to match");

    }
}
