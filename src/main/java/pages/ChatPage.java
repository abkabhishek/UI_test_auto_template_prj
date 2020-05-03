package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class ChatPage extends BasePage{
//    public WebDriver driver;

    public By headerUsername = new By.ById("header-username");
    public By headerRoomName = new By.ById("room-name");
    public By headerLogoutButton = new By.ByCssSelector("nav.navbar a[type=\"submit\"]");
    public By usersList = new By.ByCssSelector("#users-list a");
    public By messages = new By.ByCssSelector(".message");
    public By messageHeader = new By.ByTagName("dt");
    public By messageContent = new By.ByTagName("dd");
    public By messageInput = new By.ByCssSelector("input#client_msg");
    public By messageSendButton = new By.ByCssSelector("button#send_msg");

    public ChatPage(WebDriver driverObj) {
        super(driverObj);
//        driver = driverObj;

    }

    public void waitForPage(){

        waitForPageByName("chatpage");
    }


    public String getUsername(){
        return driver.findElement(headerUsername).getText();
    }

    public String getRoomName(){
        return driver.findElement(headerRoomName).getText();
    }

    public Integer getUserCount(){
        return driver.findElements(usersList).size();
    }

    public List<String> getListOfUsers(){
        List<WebElement> userElems = driver.findElements(usersList);
        return userElems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public Integer getChatMessageCount(){
        return driver.findElements(messages).size();
    }

    public Message getNthChatMessage(Integer nth){
        return new Message(driver.findElements(messages).get(nth));
    }

    public Message getLastChatMessage(){
        Integer chatCount = getChatMessageCount();
        return new Message(driver.findElements(messages).get(chatCount-1));
    }



    public List<Message> getAllChatMessage(){
        List<WebElement> msgElems = driver.findElements(messages);
        return msgElems.stream().map(Message::new).collect(Collectors.toList());
    }

    public void inputMessage(String msg){
        driver.findElement(messageInput).sendKeys(msg);
    }

    public void clickSendButton(){
        driver.findElement(messageSendButton).click();
    }

    public void sendMessage(String msg){
        inputMessage(msg);
        clickSendButton();
    }

    public void logOutUser(){
        driver.findElement(headerLogoutButton).click();
        waitForPageByName("loginpage");
    }


    public class Message{

        public String messageHeaderText;
        public String messageUser;
        public String messageTime;
        public String messageText;


        public Message(WebElement msgDiv){

            messageHeaderText = msgDiv.findElement(messageHeader).getText();
            String[] headerText = messageHeaderText.split(":");
            messageUser = headerText[0];
            messageTime = headerText[1];
            messageText = msgDiv.findElement(messageContent).getText();
        }
    }



}
