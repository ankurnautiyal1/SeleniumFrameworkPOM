package com.ankur.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ankur.utils.Initialize;

public class ProductDetailPage {

	By addToCart = By.xpath("//*[@id=\"add_to_cart\"]/button/span");
	By itemName = By.xpath("//*[@id=\"center_column\"]/div/div/div[3]/h1");
	
	Initialize initialize;

	public ProductDetailPage(Initialize initialize) {
		this.initialize = initialize;
	}

	
	public WebElement getAddToCartElement() {
		
		return initialize.webDriver.findElement(addToCart);
	}
	
	public String getItemName() {
		
		return initialize.webDriver.findElement(itemName).getText();
	}
}
