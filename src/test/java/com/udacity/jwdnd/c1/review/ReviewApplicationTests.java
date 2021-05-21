package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.ChatForm;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewApplicationTests {

	@LocalServerPort
	public int port;

	public static WebDriver driver;

	public String baseUrl;

	@BeforeAll
	static void setup(){
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	@AfterAll
	static void afterAll(){
		driver.quit();
		driver = null;
	}

	@BeforeEach
	void beforeEach(){
		baseUrl = "http://localhost:"+port;
	}


	@Test
	public void testUserSignupLoginAndSubmitMessage(){
		String firstName = "Werner";
		String lastName = "Cucu";
		String username = "kuku";
		String password = "121212";
		String messageText = "This is really awesome";

		driver.get(baseUrl + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName,lastName,username,password);

		driver.get(baseUrl + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username,password);

		ChatPage chatPage = new ChatPage(driver);
		chatPage.sendChatMessage(messageText);

		ChatForm chatMessage = chatPage.getFirstMessage();
		assertEquals(username, chatMessage.getUsername());
		assertEquals(messageText, chatMessage.getMessagetext());
	}

}
