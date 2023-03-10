import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class E2E_Test {
	private static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		/*
		 * Starting the Chrome Driver
		 * 
		 */
		// Setup Chrome Driver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://primetech-store-qa.herokuapp.com/");

		/*
		 * Verify the correct Page is loaded
		 *	1. Verify the page title: Prime Tech School
		 *	2.Verify the URL: https://primetech-store-qa.herokuapp.com/
		 * 	3. Verify Logo: Sporting Goods Shop
		 */
		//Verify Page Title
		String actualTitle = driver.getTitle();
		String expectedTitle = "Prime Tech Store";
		if (!actualTitle.equals(expectedTitle)) {
			throw new RuntimeException("Title of the page does not match as expected: " + expectedTitle);
		}

		// Verify URL
		String actualURL = driver.getCurrentUrl();
		String expectedURL = "https://primetech-store-qa.herokuapp.com/";
		if (!actualURL.equals(expectedURL)) {
			throw new RuntimeException("Actual URL is not matching with expected url: " + expectedURL);
		}
		
		// Verify Logo
		WebElement logoElem = driver.findElement(By.xpath("//h1[@class='logo']"));
		String actualLogText = logoElem.getText();
		String expectedLogoText = "Sporting Goods Shop";
		if (!actualLogText.equals(expectedLogoText)) {
			throw new RuntimeException("Actual Log Text is not matching with expected logo text: " + expectedLogoText);
		}
		
		/*
		 * Navigate to Login Page
		 * 1. Click Welcome Dropdown
		 * 2. Wait for Login Button become clickable
		 * 3. Click Login Button
		 */
		// Click Welcome dropdown link
		driver.findElement(By.linkText("Welcome!")).click();

		// Waiting for the Login Button to be clickable within 10 seconds
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement loginElem = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']")));
		// Click login Element
		loginElem.click();

		/*
		 * Verify login page is displayd 1. Verify the URL is:
		 * https://primetech-store-qa.herokuapp.com/login 2. Verify the Login Page
		 * Header is displayedL :Login
		 * 
		 */

		// Verifying the Login Page URL
		String actualLoginPageURL = driver.getCurrentUrl();
		String expectedLoginPageURL = "https://primetech-store-qa.herokuapp.com/login";
		if (!actualLoginPageURL.equals(expectedLoginPageURL)) {
			throw new RuntimeException("Login Page URL is not correct");
		}
		// Verifying the Login Page Header with Text: Login is Exist
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Login']")));

		/*
		 * Login using a valid credentials: pt_test@gmail.com Test@1234 1. Enter Email
		 * Address 2. Enter Password 3. Click Login Button
		 * 
		 */
		driver.findElement(By.xpath("//label[text()='Email Address']/..//input")).sendKeys("pt_test@gmail.com");
		driver.findElement(By.name("password")).sendKeys("Test@1234");
		driver.findElement(By.xpath("//*[text()='Login']/parent::button")).click();

		/*
		 * Verify Login into the right account 1. Verify url is :
		 * https://primetech-store-qa.herokuapp.com/dashboard 2. Account Details shows
		 * as below: email: pt_test@gmail.com accountType: Member FirstName: PT
		 * LastName: Test
		 * 
		 */
		Thread.sleep(5000);
		String actualDashBoardURL = driver.getCurrentUrl();
		String expectedDashBoardURL = "https://primetech-store-qa.herokuapp.com/dashboard";
		if (!actualDashBoardURL.equals(expectedDashBoardURL)) {
			throw new RuntimeException("Dashboard Page URL is not correct");
		}
		// Verify User Email
		String actualEmail = driver.findElement(By.xpath("//*[@class='account-details']//*[@class='desc']//p"))
				.getText().trim();
		String expectedEmail = "pt_test@gmail.com";
		if (!actualEmail.equals(expectedEmail)) {
			throw new RuntimeException("Invalid Email Address: " + actualEmail);
		}
		// Verify User Account Type
		String actualAccountType = driver
				.findElement(By.xpath("//*[@class='account-details']//*[@class='desc']//*[contains(@class,'role')]"))
				.getText().trim();
		String expectedAccountType = "Member";
		if (!actualAccountType.equals(expectedAccountType)) {
			throw new RuntimeException("Invalid Account Type: " + actualAccountType);
		}

		// Verify FirstName 
		 String actualFirstName= driver.findElement(By.name("firstName")).getDomProperty("value");
		 String expectedFirstName = "PT";
		 if(!actualFirstName.equals(expectedFirstName)) {
			 throw new RuntimeException("Invalid First Name: " + actualAccountType);
		 }
		 //Verify Lastname
		 
		 String actualLastName= driver.findElement(By.name("lastName")).getDomProperty("value");
		 String expectedLastName = "Test";
		 if(!actualLastName.equals(expectedLastName)) {
			 throw new RuntimeException("Invalid Last Name: " + actualLastName);
		 }
		 

		/*
		 * 
		 * Logout from the account
		 */
		 // Locate the Account Dropdown
		 String firstName = "PT";
		 driver.findElement(By.linkText(firstName)).click();
		 
		 new WebDriverWait(driver, Duration.ofSeconds(10))
		 .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Sign Out']")))
		 .click();
		 
		 /*
		  * Verify You are back to the Login Page
		  * 1. Verify Login Page URL
		  * 2. Check the Login Page Header Displayed: Login
		  */
		 
		// Verifying the Login Page URL
		 	Thread.sleep(5000);
			String actualLoginPageURL2 = driver.getCurrentUrl();
			String expectedLoginPageURL2 = "https://primetech-store-qa.herokuapp.com/login";
			if (!actualLoginPageURL2.equals(expectedLoginPageURL2)) {
				throw new RuntimeException("Login Page URL is not correct");
			}
			// Verifying the Login Page Header with Text: Login is Exist
			new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Login']")));
		 
		/*
		 * Kill the Driver
		 * 
		 */
			
			driver.quit();

	}

}
