package com.qa.techfios.tests;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.techfios.base.TestBase;
import com.qa.techfios.pages.HomePage;
import com.qa.techfios.utilities.TestUtilities;

public class HomePageTest extends TestBase{
	
	TestUtilities testUtils = new TestUtilities();
	
	@BeforeMethod
	public void setUp() {
		initializeBrowser();
	}
	
	@Test(priority=0)
	public void validateToggleAllFunctionality() {
		driver.get(properties.getProperty("url"));
		HomePage homePage = new HomePage(driver);
		String categoryName = "TESTNG84";
		homePage.addCategory(categoryName);
		boolean isCategoryPresent = homePage.verifyCategoryIsDisplayed(categoryName);
		Assert.assertTrue(isCategoryPresent, "The Category Name is not Present");
	}
	
	@Test(priority=1)
	public void validateDuplicateCategoryMessage() {
		driver.get(properties.getProperty("url"));
		HomePage homePage = new HomePage(driver);
		String categoryName = "TESTNG84";
		homePage.addCategory(categoryName);
		String expectedMessage = "The category you want to add already exists: "+categoryName+".";
		String actualMessage = homePage.getErrorMessageForDuplicateCategory().split("\\n")[0];
		Assert.assertEquals(actualMessage, expectedMessage, "Not as expected, the acutal error message '"+actualMessage+"' is not matching with the expected '"+expectedMessage+"'");
	}
	
	@Test(priority=2)
	public void validateMonthsInDueDateDropDown() {
		driver.get(properties.getProperty("url"));
		HomePage homePage = new HomePage(driver);
		List<String> monthNamesFromUI = homePage.getListOfMonthsFromDropDown();
		List<String> monthNamesFromUtils = testUtils.getMonthsList();
		int listSizeFromUI = monthNamesFromUI.size();
		int listSizeFromUtils = monthNamesFromUtils.size();
		Assert.assertEquals(listSizeFromUI, listSizeFromUtils, "The number of months from ui is not matching with the expected months");
		for(int i=0; i< listSizeFromUI; i++) {
			Assert.assertEquals(monthNamesFromUI.get(i), monthNamesFromUtils.get(i), 
					"month name form UI: '"+monthNamesFromUI.get(i)+"' is not matching with the expected month name '"+monthNamesFromUtils.get(i)+"'");
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
