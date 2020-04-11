package com.ankur.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Initialize {

	public WebDriver webDriver;
	public static String cwd, browser, application_url;
	private static long globalWait;
	public WebDriverWait wait;
	private static Properties properties;

	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd HH_mm");
	static Date date = new Date();;
	final String dateTime = formatter.format(date);
	
	public String getDateTime() {
		return dateTime;
	}
	
	public static void initReportDir() throws IOException {
		ReportDirectory.setReportDirectory();
	}
	
	public void init() throws FileNotFoundException, IOException {
		initBrowser();
		webDriver.get(application_url); 
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().pageLoadTimeout(globalWait, TimeUnit.SECONDS);
		wait = new WebDriverWait(webDriver, globalWait);

	}

	public WebDriverWait getWait(WebDriver webDriver) {

		return wait;
	}

	public WebDriver getDriver() {
		return webDriver;
	}

	private static void readProperties() throws FileNotFoundException, IOException {
		cwd = System.getProperty("user.dir");
		properties = new Properties();
		properties.load(new FileInputStream(cwd + "\\src\\main\\resources\\config.properties"));

		browser = properties.getProperty("browser").toLowerCase();
		globalWait = Long.parseLong(properties.getProperty("global_wait_time"));
		application_url = properties.getProperty("application_url").toLowerCase();
	}

	private void initBrowser() throws FileNotFoundException, IOException {
		readProperties();
		switch (browser) {
		case "chrome": {
			WebDriverManager.chromedriver().setup();
			webDriver = new ChromeDriver();
			break;
		}
		case "firefox": {
			WebDriverManager.firefoxdriver().setup();
			webDriver = new FirefoxDriver();
			break;
		}
		case "edge": {
			WebDriverManager.edgedriver().setup();
			webDriver = new EdgeDriver();
			break;
		}
		case "internetexplorer": {
			WebDriverManager.iedriver().setup();
			webDriver = new InternetExplorerDriver();
			break;
		}
		case "ie": {
			WebDriverManager.iedriver().setup();
			webDriver = new InternetExplorerDriver();
			break;
		}

		default: {
			WebDriverManager.chromedriver().setup();
			webDriver = new ChromeDriver();
		}

		}
	}
}
