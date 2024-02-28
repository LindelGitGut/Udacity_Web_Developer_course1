package com.udacity.jwdnd.c1.review.PageObjects;

import org.checkerframework.framework.qual.QualifierForLiterals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPageObject {


    public SignupPageObject(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "firstname")
    WebElement firstname;

    @FindBy(id = "lastname")
    WebElement lastname;

    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(css = "input[type='submit'][value = 'Registrieren']")
    WebElement registerButton;

    @FindBy(xpath = "//h1[contains(text(), 'Username bereits vergeben')]")
    WebElement errorMessage;

    @FindBy(xpath = "//h1[contains(text(), 'User wurde erfolgreich erstellt! Klicken sie')]")
    WebElement succesMessage;

    public void inputFirstName(String firstname){
        this.firstname.clear();
        this.firstname.sendKeys(firstname);
    }

    public void inputLastName(String lastname){
        this.lastname.clear();
        this.lastname.sendKeys(lastname);
    }

    public void inputUserName(String username){
        this.username.clear();
        this.username.sendKeys(username);
    }

    public void inputPassword(String password){
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void clickRegisterButton(){
        this.registerButton.click();
    }

    public String getSuccessMessage() throws NoSuchFieldError{
        return this.succesMessage.getText();
    }

    public String getErrorMessage () throws NoSuchFieldError{
        return this.errorMessage.getText();
    }


}
