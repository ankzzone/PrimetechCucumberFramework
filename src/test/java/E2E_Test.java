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
	

	public static void main(String[] args) throws InterruptedException {
		
		
		/*
		 * Navigate to Login Page
		 * 1. Click Welcome Dropdown
		 * 2. Wait for Login Button become clickable
		 * 3. Click Login Button
		 */
	

		/*
		 * Verify login page is displayd 1. Verify the URL is:
		 * https://primetech-store-qa.herokuapp.com/login 2. Verify the Login Page
		 * Header is displayedL :Login
		 * 
		 */

		
		
		
		
		
		

		
		 
		 

		/*
		 * 
		 * Logout from the account
		 */
		
		 
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
			
			

	}

}
