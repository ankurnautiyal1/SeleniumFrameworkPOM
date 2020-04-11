package com.ankur.tests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ankur.dataprovider.UserCredentials;
import com.ankur.pageobjects.HomePage;
import com.ankur.pageobjects.LoginPage;
import com.ankur.utils.ExtentManager;
import com.ankur.utils.Initialize;
import com.ankur.utils.ReportDirectory;
import com.ankur.utils.ScreenShots;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest {

	Initialize initialize;
	WebDriver webDriver;
	
	HomePage homePage;
	LoginPage loginPage;
	public static ExtentReports extent;
	public static ExtentTest test;
	@BeforeTest
	public void beforeTest() {
		try {
			initialize = new Initialize();
			initialize.init();
			webDriver = initialize.getDriver();
			homePage = new HomePage(initialize);
			loginPage = new LoginPage(initialize);
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

	@Test(dataProvider = "getUserData", dataProviderClass = UserCredentials.class)
	public void login(String email, String password) {
		homePage.clickSignIn();
		test.log(LogStatus.INFO, "Clicked on SignIn button");
		loginPage.setEmail(email);
		test.log(LogStatus.INFO, "Entering the email: "+email);
		loginPage.setPassword(password);
		test.log(LogStatus.INFO, "Entering the password: "+password);
		loginPage.clickOnSignIn();
		test.log(LogStatus.INFO, "Signin button clicked");
		test.addScreenCapture(ScreenShots.takeScreenShot(initialize.webDriver, "Login.png"));
		String signOutText = loginPage.getTextSignOut();
		
		Assert.assertEquals(signOutText, "Sign out", "User is not logged in");
	}
}
