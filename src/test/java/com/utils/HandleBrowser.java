package com.utils;

import java.io.*;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class HandleBrowser {

	static WebDriver driver = null;

	public static WebDriver launchBrowser() throws IOException {

		String propPath = System.getProperty("user.dir") + "/config.properties";
		FileInputStream file = new FileInputStream(new File(propPath));
		Properties prop = new Properties();
		prop.load(file);
		file.close();

		String browser = prop.getProperty("testBrowser");

		if (browser.equalsIgnoreCase("firefox")) {

			FirefoxOptions ops = new FirefoxOptions();
			ops.addArguments("--disable-notifications");

			System.setProperty("webdriver.gecko.driver",
					prop.getProperty("driverLocationFirefox"));

			driver = new FirefoxDriver();
		}

		else if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--disable-notifications");

			System.setProperty("webdriver.chrome.driver",
					prop.getProperty("driverLocationChrome"));

			driver = new ChromeDriver(ops);
		}

		return driver;
	}

	public static void closeBrowser(WebDriver driver) {
		driver.quit();
	}
}
