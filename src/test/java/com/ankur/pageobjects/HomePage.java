package com.ankur.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ankur.utils.Initialize;

public class HomePage {

	By categoryWomen = By.xpath("//a[@class='sf-with-ul'][contains(text(),'Women')]");
	By subCategoryWomenTShirt = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[1]/a");
	By searchBox = By.id("search_query_top");
	By searchButton = By.xpath("//button[@name='submit_search']");
	By signIn = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");

	Actions action;
	WebElement element;
	
	Initialize initialize;

	public HomePage(Initialize initialize) {
		this.initialize = initialize;
		action = new Actions(initialize.webDriver);
	}


	public void hoverOverCategoryWomen() {

		initialize.wait.until(ExpectedConditions.elementToBeClickable(categoryWomen));
		element = initialize.webDriver.findElement(categoryWomen);
		action.moveToElement(element).perform();
	}

	public void clickSubCategoryWomenTShirt() {

		initialize.wait.until(ExpectedConditions.elementToBeClickable(subCategoryWomenTShirt));
		initialize.webDriver.findElement(subCategoryWomenTShirt).click();
	}

	public void searchText(String searchText) {
		initialize.wait.until(ExpectedConditions.elementToBeClickable(searchBox));
		initialize.webDriver.findElement(searchBox).sendKeys(searchText);
		initialize.webDriver.findElement(searchButton).click();
	}

	public void clickSignIn() {
		initialize.webDriver.findElement(signIn).click();
	}

}
