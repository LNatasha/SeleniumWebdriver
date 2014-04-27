package test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.*;

public class LoginInvalidAccount {
    private static WebDriver driver;
	
	
    @BeforeClass
    public void setup()
    {
    	driver = new FirefoxDriver();
    }
	
    @Test(description="Test login page for a valid google account with invalid password")
	public void testLogin()
	{
    	//variables and inputs
    	WebElement login, password, button;
		String impLogin = "validlogin@gmail.com";
    	String impPass = "badpassword";
    	driver.get("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail");
		
		
		/**
		 *  Xpath and CSS selectores for the elements
		 */
		login = driver.findElement(By.xpath("//*[@id=\"Email\"]")); //relative path
		password = driver.findElement(By.xpath("/html/body/div/div[contains(@class,'content')]/div[contains(@class,'signin-card')]/form/input[@id='Passwd']"));
		button = driver.findElement(By.xpath("/html/body/div/div[contains(@class,'content')]/div[contains(@class,'signin-card')]/form/input[@id='signIn']"));
		//login = driver.findElement(By.cssSelector("#Email")); //relative path
		//password = driver.findElement(By.cssSelector("html body div.wrapper div.main div.card form#gaia_loginform input#Passwd"))

		/**
		 * Validation for login input field
		 */
		
		Assert.assertTrue(login.isDisplayed(), "The login field is not visible");		
		
		login.sendKeys(impLogin); // write the email
		
		/**
		 * Validation for password input field
		 */
		Assert.assertTrue(password.isDisplayed(), "The password field is not visible");
		password.sendKeys(impPass);
		
		button.click();
		
		/**
		 * Explicit wait for error mesange 
		 */
		WebElement msg = waituntilbevisibel(driver,"//*[@id=\"errormsg_0_Passwd\"]");
		Assert.assertEquals(msg.getText(), "The email or password you entered is incorrect. ?");
		
	}
    
    /**
     * Expicit wait for element be visible.
     */
    private WebElement waituntilbevisibel(WebDriver driver, String xpath)
    {
    	WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return element;
    }
    
	
	@AfterClass
	public void teardown()
	{
		driver.close(); //close the page
		driver.quit(); //destroy the webdriver instance
		/**
		 *  normally I use booth because sometimes, when you are run tests in parallel, 
		 *  the end of test is not closing the browser.
		 */
	}
	

}
