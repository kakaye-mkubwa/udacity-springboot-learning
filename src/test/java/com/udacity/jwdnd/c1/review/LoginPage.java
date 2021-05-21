package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.yaml.snakeyaml.events.Event;

public class LoginPage {
    @FindBy(id = "submit-button")
    WebElement loginButton;

    @FindBy(id = "inputUsername")
    WebElement loginUsername;

    @FindBy(id = "inputPassword")
    WebElement loginPassword;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void login(String username,String password){
        this.loginUsername.sendKeys(username);
        this.loginPassword.sendKeys(password);
        this.loginButton.click();
    }
}
