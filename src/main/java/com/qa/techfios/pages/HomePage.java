package com.qa.techfios.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.techfios.customactions.CustomWebDriverMethods;

public class HomePage extends CustomWebDriverMethods {

	WebDriver driver;
	
	@FindBy(css = "input[name='categorydata']")
	private WebElement categoryTextField;

	public WebElement getCategoryTextField() {
		return this.categoryTextField;
	}

	@FindBy(css = "input[value='Add category']")
	private WebElement addCategoryButton;
	
	public WebElement getAddCategoryButton() {
		return this.addCategoryButton;
	}
	
	@FindBy(css = "body")
	private WebElement categoryErrorMessage;
	
	public WebElement getCategoryErrorMessage() {
		return this.categoryErrorMessage;
	}
	
	@FindBy(css="select[name='due_month']")
	private WebElement monthsDropDown;
	
	public WebElement getMonthsDropDown() {
		return this.monthsDropDown;
	}
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void addCategory(String categoryName) {
		setText(categoryTextField, categoryName);
		customClick(addCategoryButton);
	}
	
	public boolean verifyCategoryIsDisplayed(String categoryName) {
		WebElement category = driver.findElement(By.xpath("//span[text()='"+categoryName+"']"));
		return isElementDisplayed(category);
	}
	
	public String getErrorMessageForDuplicateCategory() {
		return getElementText(getCategoryErrorMessage());
	}
	
	public List<String> getListOfMonthsFromDropDown() {
		List<String> monthsList = new ArrayList<String>();
		for(WebElement monthOption :  getAllElementsFromDropDown(getMonthsDropDown())) {
			if(!getElementText(monthOption).equals("None")) {
				monthsList.add(getElementText(monthOption));
			}
		}		
		return monthsList;
	}
}
