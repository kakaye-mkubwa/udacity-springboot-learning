package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.ChatForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {
    @FindBy(id = "messageText")
    WebElement messageTextField;

    @FindBy(id = "submit-message")
    WebElement submitMessageButton;

    @FindBy(className = "message-text-and-username")
    WebElement messageAndUsernamePTag;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void sendChatMessage(String text){
        this.messageTextField.sendKeys(text);
        this.submitMessageButton.click();
    }

    public ChatForm getFirstMessage(){
        ChatForm result = new ChatForm();
        result.setMessagetext(messageTextField.getText());
        String usernameAndMessage = messageAndUsernamePTag.getText();
        String[] values = usernameAndMessage.split(" -> ");
        result.setUsername(values[0]);
        result.setMessagetext(values[1]);
        return result;
    }
}
