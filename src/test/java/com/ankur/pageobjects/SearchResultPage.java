package com.ankur.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ankur.utils.Initialize;

public class SearchResultPage {
	By itemName = By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/h5/a");

	WebElement element;
	Initialize initialize;

	public SearchResultPage(Initialize initialize) {
		this.initialize = initialize;
	}

	public String getItemName() {
		element = initialize.webDriver.findElement(itemName);
		initialize.wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

}
