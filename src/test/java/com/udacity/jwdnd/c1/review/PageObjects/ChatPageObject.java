package com.udacity.jwdnd.c1.review.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ChatPageObject {

    public ChatPageObject(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "messageText")
    WebElement messageText;

    @FindBy(id = "messageType")
    WebElement messageType;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement submitbutton;

    @FindBy(tagName = "h1")
    List<WebElement> messages;


    public void inputMessageText(String messagetext) {
        this.messageText.sendKeys(messagetext);
    }

    public void inputMessageType(String messageType) {
        Select dropdown = new Select(this.messageType);
        dropdown.selectByVisibleText(messageType);
    }

    public void clickSubmit() {
        this.submitbutton.click();
    }

    public List<String> getMessages() {
        List<String> allMessages = new ArrayList<>();
        for (WebElement msg : this.messages) {
            allMessages.add(msg.getText());
        }
        return allMessages;
    }

}
