package com.ankur.tests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ankur.pageobjects.CategoryPage;
import com.ankur.pageobjects.HomePage;
import com.ankur.pageobjects.ProductDetailPage;
import com.ankur.utils.ExtentManager;
import com.ankur.utils.Initialize;
import com.ankur.utils.ReportDirectory;
import com.ankur.utils.ScreenShots;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import net.bytebuddy.implementation.bind.MethodDelegationBinder.AmbiguityResolver.Resolution;

public class ProductDetailPageTest {
	
    Initialize initialize;
	HomePage homePage;
	CategoryPage categoryPage;
	ProductDetailPage productDetailPage;
	WebElement element;
	SoftAssert softAssert;
	public static ExtentReports extent;
	public static ExtentTest test;
	String screenshotPath;

	@BeforeTest
	public void beforeTest() {
		try {
			initialize = new Initialize();
			initialize.init();
			homePage = new HomePage(initialize);
			categoryPage = new CategoryPage(initialize);
			productDetailPage = new ProductDetailPage(initialize);
			softAssert = new SoftAssert();
			extent = ExtentManager.getInstance(ReportDirectory.getReportPath()+"\\ExtentReport.html");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterTest
	public void afterTest() {
		initialize.webDriver.quit();
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		test = extent.startTest(method.getName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS)
			test.log(LogStatus.PASS, "Test case got passed");
		else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getThrowable());
		}
			
		else if (result.getStatus() == ITestResult.SKIP)
			test.log(LogStatus.SKIP, result.getThrowable());

		extent.flush();

	}

	@Test(priority = 1)
	public void verifyProductName() {
		homePage.hoverOverCategoryWomen();
		test.log(LogStatus.INFO, "Hovering over the Women category option");
		homePage.clickSubCategoryWomenTShirt();
		test.log(LogStatus.INFO, "Sub category T-shirt is clicked");
		categoryPage.clickOnItem();
		test.log(LogStatus.INFO, "Item clicked");

		String itemName = productDetailPage.getItemName();
		test.log(LogStatus.INFO, "Item name from the product detail page: "+itemName);

		softAssert.assertEquals(itemName, "Intentional incorrect name");
		softAssert.assertAll();

	}

	@Test(priority = 2)
	public void verifyAddToCart() {
		element = productDetailPage.getAddToCartElement();
		screenshotPath = ScreenShots.takeScreenShot(initialize.webDriver, "ProductDetailPage.png");
		test.addScreenCapture(screenshotPath);
		softAssert.assertNull(element, "Intentional soft assertion failure with this message");
		softAssert.assertAll();
	}

}
