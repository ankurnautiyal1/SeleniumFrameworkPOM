package com.ankur.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.io.Files;

public class ReportDirectory {

	private static String reportDir, archiveDir, currentReportDir, screenshotDir;
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd HH_mm");
	static Date date;
	static String dateTime;

	static File reportDirectory;
	static File archiveDirectory;
	static File currentReportDirectory;
	static File screenshotDirectory;
	
	public static String getReportPath() {
		return currentReportDir;
	}

	public static void setReportDirectory() throws IOException {

		date = new Date();
		dateTime = formatter.format(date);

		reportDir = System.getProperty("user.dir") + "\\Reports\\";
		currentReportDir = System.getProperty("user.dir") + "\\Reports\\" + dateTime;
		archiveDir = System.getProperty("user.dir") + "\\Reports\\Archive\\";
		screenshotDir = currentReportDir + "\\Screenshot";

		reportDirectory = new File(reportDir);
		archiveDirectory = new File(archiveDir);
		currentReportDirectory = new File(currentReportDir);
		screenshotDirectory = new File(screenshotDir);

		if (!reportDirectory.exists()) {
			reportDirectory.mkdir();
		}
		if (!archiveDirectory.exists()) {
			archiveDirectory.mkdir();
		}
		if (!currentReportDirectory.exists()) {
			currentReportDirectory.mkdir();
		}
		if (!screenshotDirectory.exists()) {
			screenshotDirectory.mkdir();
		}
		archivePreviousReports();
	}

	private static void archivePreviousReports() throws IOException {

		String[] reportList = reportDirectory.list();

		for (int counter = 0; counter < reportList.length; ++counter) {
			if (reportList[counter].compareToIgnoreCase(dateTime) != 0
					&& reportList[counter].compareToIgnoreCase("Archive") != 0) {
				Files.move(new File(reportDir + reportList[counter]), new File(archiveDir + reportList[counter]));
			}
		}

	}
}
