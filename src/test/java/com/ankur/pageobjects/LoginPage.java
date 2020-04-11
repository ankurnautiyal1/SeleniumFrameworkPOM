package com.ankur.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ankur.utils.Initialize;

public class LoginPage {
	
	By email = By.xpath("//*[@id=\"email\"]");
	By password = By.xpath("//*[@id=\"passwd\"]");
	By signInButton = By.xpath("//*[@id=\"SubmitLogin\"]/span");
	By signOut = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a");
	
	Initialize initialize;

	public LoginPage(Initialize initialize) {
		this.initialize = initialize;
	}

	
	public void setEmail(String emailId) {
		initialize.wait.until(ExpectedConditions.elementToBeClickable(email));
		initialize.webDriver.findElement(email).sendKeys(emailId);
	}
	
	public void setPassword(String pass) {
		initialize.webDriver.findElement(password).sendKeys(pass);
	}
	
	public void clickOnSignIn() {
		initialize.webDriver.findElement(signInButton).click();
	}
	
	public String getTextSignOut() {
		initialize.wait.until(ExpectedConditions.elementToBeClickable(signOut));
		return initialize.webDriver.findElement(signOut).getText();
	}
	

}
