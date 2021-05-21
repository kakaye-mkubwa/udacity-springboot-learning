package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(css = "#inputFirstName")
    WebElement firstName;

    @FindBy(css = "#inputLastName")
    WebElement lastName;

    @FindBy(css = "#inputUsername")
    WebElement username;

    @FindBy(css = "#inputPassword")
    WebElement password;

    @FindBy(css = "#submit-button")
    WebElement signupButton;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void signup(String firstName,String lastName,String username,String password){
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.signupButton.click();
    }
}
