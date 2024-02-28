package com.udacity.jwdnd.c1.review;


import com.udacity.jwdnd.c1.review.PageObjects.ChatPageObject;
import com.udacity.jwdnd.c1.review.PageObjects.LoginPageObject;
import com.udacity.jwdnd.c1.review.PageObjects.SignupPageObject;
import com.udacity.jwdnd.c1.review.services.UserService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SeleniumTest {

    private static WebDriver webDriver;

    @LocalServerPort
    private int port;

    @BeforeAll
    public static void initWebDriver(){
        WebDriverManager.chromedriver().clearDriverCache();
        WebDriverManager.chromedriver().clearResolutionCache();
        WebDriverManager.chromedriver().setup();
         webDriver = new ChromeDriver();
    }

    @BeforeEach
    public void openStartPage(){
        this.webDriver.get("http://localhost:"+port+"/login");
    }

    @Test
    public void userRegistrationTest() throws InterruptedException {

        LoginPageObject loginPage = new LoginPageObject(this.webDriver);
        loginPage.clickRegisterButton();

        Thread.sleep(200);

        SignupPageObject signupPage = new SignupPageObject(this.webDriver);
        signupPage.inputFirstName("Alexandre");
        signupPage.inputLastName("Lindele");
        signupPage.inputUserName("Alex");
        signupPage.inputPassword("password");
        Thread.sleep(2000);
        signupPage.clickRegisterButton();
        Thread.sleep(1000);
        assertEquals(signupPage.getSuccessMessage().contains("User wurde erfolgreich erstellt! Klicken sie"), true);

    }

    @Test
    public void loginTest() throws InterruptedException {
        LoginPageObject loginPage = new LoginPageObject(this.webDriver);
        loginPage.inputUsername("Alex");
        loginPage.inputPassword("password");

        Thread.sleep(200);

        ChatPageObject chatPageObject = new ChatPageObject(webDriver);
        WebElement loggedInElement = chatPageObject.getSubmitbutton();
        assertNotNull(loggedInElement);

    }

    @Test
    public void sendChatMessage() throws InterruptedException {
        LoginPageObject loginPage = new LoginPageObject(this.webDriver);
        loginPage.inputUsername("Alex");
        loginPage.inputPassword("password");
        loginPage.clickLoginButton();



        Thread.sleep(1000);

        ChatPageObject chatPage = new ChatPageObject(this.webDriver);
        Thread.sleep(1000);
        chatPage.inputMessageText("I am the allmighty TestMEssage!");
        chatPage.inputMessageType("Shout");
        chatPage.clickSubmit();

        Thread.sleep(20000);

        assertEquals(true, chatPage.getMessages().stream().anyMatch(s -> s.contains("User: Alex | Nachricht: I AM THE ALLMIGHTY TESTMESSAGE!")));

    }

    @AfterAll
    public static void closeWebDriver(){
        webDriver.close();
    }


}
