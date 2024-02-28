package com.udacity.jwdnd.c1.review.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {

    public LoginPageObject(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(css = "input[type='submit'][value='Login']")
    WebElement loginButton;

    @FindBy(css = "input[type = 'submit'][value = 'Registrieren']")
    WebElement registerButton;

    public void inputUsername(String username){
        this.username.clear();
        this.username.sendKeys(username);
    }

    public void inputPassword(String password){
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void clickLoginButton(){
        this.loginButton.click();
    }

    public void clickRegisterButton(){
        this.registerButton.click();
    }
}
