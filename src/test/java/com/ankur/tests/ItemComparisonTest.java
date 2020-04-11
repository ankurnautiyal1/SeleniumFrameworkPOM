package com.ankur.tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ankur.pageobjects.CategoryPage;
import com.ankur.pageobjects.HomePage;
import com.ankur.pageobjects.SearchResultPage;
import com.ankur.utils.Initialize;
import com.ankur.utils.ReportDirectory;
import com.ankur.utils.ScreenShots;
import com.ankur.utils.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ItemComparisonTest {

	Initialize initialize;

	HomePage homePage;
	CategoryPage categoryPage;
	SearchResultPage searchResultPage;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void beforeSuite() throws IOException {
		Initialize.initReportDir();
	}

	@BeforeTest
	public void beforeTest() {
		try {
			initialize = new Initialize();
			initialize.init();
			homePage = new HomePage(initialize);
			categoryPage = new CategoryPage(initialize);
			searchResultPage = new SearchResultPage(initialize);
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
		else if (result.getStatus() == ITestResult.FAILURE)
			test.log(LogStatus.FAIL, result.getThrowable());
		else if (result.getStatus() == ITestResult.SKIP)
			test.log(LogStatus.SKIP, result.getThrowable());

		extent.flush();

	}

	@Test
	public void compareItemName() {
		String nameFromCategory, nameFromSearchResult;

		homePage.hoverOverCategoryWomen();
		test.log(LogStatus.INFO, "Women category clicked");
		homePage.clickSubCategoryWomenTShirt();
		test.log(LogStatus.INFO, "Sub category named T-Shirt under Women category clicked");

		nameFromCategory = categoryPage.getItemName();
		test.log(LogStatus.INFO, "Get name of the item: "+nameFromCategory);

		homePage.searchText(nameFromCategory);
		test.log(LogStatus.INFO, "Searching for: "+nameFromCategory);

		nameFromSearchResult = searchResultPage.getItemName();
		test.log(LogStatus.INFO, "Item name from search results: "+nameFromSearchResult);

		Assert.assertEquals(nameFromCategory, nameFromSearchResult);
		test.addScreenCapture(ScreenShots.takeScreenShot(initialize.webDriver, "compareItemName.png"));
	}
}
