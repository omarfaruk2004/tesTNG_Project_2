package com.qa.techfios.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.qa.techfios.utilities.Browsers;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {
	
	public static WebDriver driver = null;
	public Properties properties = null;
	
	public TestBase(){
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\config.properties");
		try {
		FileInputStream fileInputStream = new FileInputStream(file);
		properties = new Properties();
		properties.load(fileInputStream);
		} catch(IOException io) {
			io.printStackTrace();
		}
	}

	public void initializeBrowser() {
		if(Browsers.chrome.name().equalsIgnoreCase(properties.getProperty("browser"))) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(Browsers.firefox.name().equalsIgnoreCase(properties.getProperty("browser"))) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(Browsers.interexplorer.name().equalsIgnoreCase(properties.getProperty("browser"))) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}else if(Browsers.edge.name().equalsIgnoreCase(properties.getProperty("browser"))) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
	}
}
