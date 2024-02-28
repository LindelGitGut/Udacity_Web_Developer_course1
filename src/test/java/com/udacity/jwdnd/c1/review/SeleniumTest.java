package com.udacity.jwdnd.c1.review;


import com.udacity.jwdnd.c1.review.PageObjects.ChatPageObject;
import com.udacity.jwdnd.c1.review.PageObjects.LoginPageObject;
import com.udacity.jwdnd.c1.review.PageObjects.SignupPageObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SeleniumTest {

    private static WebDriver webDriver;

    @BeforeAll
    public static void initWebDriver(){
        WebDriverManager.chromedriver().clearCache();
        WebDriverManager.chromedriver().clearPreferences();
        WebDriverManager.chromedriver().setup();
         webDriver = new ChromeDriver();
    }

    @BeforeEach
    public void openStartPage(){
        this.webDriver.get("http://localhost:8080/login");
    }

    @Test
    public void userRegistrationTest(){

        LoginPageObject loginPage = new LoginPageObject(this.webDriver);
        loginPage.clickRegisterButton();

        SignupPageObject signupPage = new SignupPageObject(this.webDriver);
        signupPage.inputFirstName("Alexandre");
        signupPage.inputLastName("Lindele");
        signupPage.inputUserName("Alex");
        signupPage.inputPassword("password");
        signupPage.clickRegisterButton();

        //TODO check how we can receive if a user is signed up successfully
        assertEquals(signupPage.getSuccessMessage().contains("User wurde erfolgreich erstellt! Klicken sie"), true);

    }

    @Test
    public void loginTest(){
        LoginPageObject loginPage = new LoginPageObject(this.webDriver);
        loginPage.inputUsername("Alex");
        loginPage.inputPassword("password");

        //TODO check wich assert we should use to check successfull login
    }

    @Test
    public void sendChatMessage(){
        LoginPageObject loginPage = new LoginPageObject(this.webDriver);
        loginPage.inputUsername("Alex");
        loginPage.inputPassword("password");
        loginPage.clickLoginButton();

        //TODO Add Chatmessage

        ChatPageObject chatPage = new ChatPageObject(this.webDriver);
        chatPage.inputMessageText("I am the almighty TestMEssage!");
        chatPage.inputMessageType("Shout");

    }

    @AfterAll
    public static void closeWebDriver(){
        webDriver.close();
    }


}
