package com.sddevops.selenium_maven.eclipse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class RecipeTest {
	private WebDriver webDriver;
  @Test
  public void checkTitle() {
		//check title for Recipe.jsp
		webDriver.navigate().to("http://localhost:8090/DevOpsProject/RecipeManagement/recipe");
		 Assert.assertEquals(webDriver.getTitle(), "Recipe");
		//check title for Home.jsp
		 webDriver.navigate().to("http://localhost:8090/DevOpsProject/Home.jsp");
		 Assert.assertEquals(webDriver.getTitle(), "Home");
		//check title for Blankpage1.jsp
		 webDriver.navigate().to("http://localhost:8090/DevOpsProject/Blankpage1.jsp");
		 Assert.assertEquals(webDriver.getTitle(), "Register"); 	
	}
  
  @Test
  public void CheckNavBar() {
	  webDriver.navigate().to("http://localhost:8090/DevOpsProject/Home.jsp");
	  List<WebElement> NumNavLink = webDriver.findElements(By.className("nav-link"));
	  
	  int size = NumNavLink.size();
	  
	  Assert.assertEquals(size, 3);
  }
  @Test
  public void checkInput() {
	  webDriver.navigate().to("http://localhost:8090/DevOpsProject/CreateRecipe.jsp");
	  webDriver.findElement(By.name("recipeName"));
  }
  

  @BeforeTest
  public void beforeTest() {
	  //Setting system properties of ChromeDriver
	  //to amend directory path base on your local file path
	  String chromeDriverDir = "C:\\Program Files\\Google\\Chrome\\chromedriver.exe";

	  System.setProperty("webdriver.chrome.driver", chromeDriverDir);

	  //initialize FirefoxDriver at the start of test
	  webDriver = new ChromeDriver();  
  }

  @AfterTest
  public void afterTest() {
  }

}
