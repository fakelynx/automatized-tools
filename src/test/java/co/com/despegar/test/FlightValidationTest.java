package co.com.despegar.test;


import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// here's missing the Javadoc.
public class FlightValidationTest {
	
	private WebDriver driver;
	By from_flight_searchbox_locator = By.xpath("//input [@placeholder = 'Ingresa desde dónde viajas']");
	By to_flight_searchbox_locator = By.xpath("//input [@placeholder = 'Ingresa hacia dónde viajas']");
	By depart_date_searchbox_locator = By.xpath("//input [@placeholder = 'Ida']");
	//By return_date_searchbox_locator = By.xpath("//input [@placeholder = 'Vuelta']");
	
	@Before
	public void setup() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","./src/test/resources/driver-chrome/chromedriver_96.0.4664.45.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.despegar.com.co");
		
	}
	
	/**
	 * @author      Firstname Lastname <address @ example.com>
	 * @version     1.6                 (current version number of program)
	 * @since       1.2          (the version of the package this class was first added to)
	 * - Que hace el metodo
	 * - parametro (linea por cada uno)
	 * - retorna 
	 * - que hace + consideraciones importantes 
	 */
	public void pickOption(WebDriver driver, By searchbox_path, String searchbox_content) throws InterruptedException {
		WebDriverWait driver_wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement driver_searchbox = driver.findElement(searchbox_path);
		Actions driver_action = new Actions(driver);
		
		driver_searchbox.clear();
		driver_action.click(driver_searchbox).perform();
		
		/*Sometimes when you click on a search box, the page will need to fetch
		some HTTP requests. To make sure the page receives the response for the request, 
		I've created this wait time that covers all of the responses' wait time 
		(e.g.: fdf3936f2c or pictures?gid=)*/
		
		// Wait for multiple requests' responses (e.g.: fdf3936f2c or pictures?gid=)
		TimeUnit.MILLISECONDS.sleep(100);
		
		driver_searchbox.sendKeys(searchbox_content);
		driver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ac-group-container")));
		driver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li [@class = 'item -active']")));
		driver_action.click(driver.findElement(By.xpath("//li [@class = 'item -active']"))).perform();
	}
	
	public void pickDate(WebDriver driver, By calendar_path) {
		WebDriverWait driver_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement driver_calendar = driver.findElement(calendar_path);
		Actions driver_action = new Actions(driver);
		
		driver_action.click(driver_calendar).perform();
		driver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div [@class = 'sbox5-monthgrid']")));
		driver_action.click(driver.findElement(By.xpath("//div [@class ='sbox5-monthgrid-datenumber -weekday -today']"))).perform();
		driver_action.click(driver.findElement(By.xpath("//div [. ='22']"))).perform();
		
	}
	
	public void clickOnButtonNamed(WebDriver driver, String name) {
		WebDriverWait driver_wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement driver_button = driver.findElement(By.xpath("//em [. = '"+name+"']"));
		Actions driver_action = new Actions(driver);
		
		driver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//em [. = '"+name+"']")));
		driver_action.click(driver_button).perform();
	}
	
	public void clickOnFirstElementbyTag(WebDriver driver, String tagname) {
			
		WebElement driver_button = driver.findElement(By.tagName(tagname));
	
		Actions driver_action = new Actions(driver);
	
		driver_action.click(driver_button).perform();
	}
	/*public void waitForVisibility(WebDriver driver, String classname) {
		WebDriverWait driver_wait
	}*/
	
	public boolean checkTagExistence(WebDriver driver, String tagname) {
		try {
			
			WebElement driver_item = driver.findElement(By.tagName(tagname));
			ExpectedConditions.visibilityOf(driver_item);
			
			return true;
			
		}catch (Exception e) {
			return false;
		}
	}
	
	@Test
	public void testFlightScreen() throws Exception{
		
		pickOption(driver, from_flight_searchbox_locator, " Medellin");
		pickOption(driver, to_flight_searchbox_locator, " Amsterdam");
		pickDate(driver, depart_date_searchbox_locator);
		
		//clickOnButtonNamed(driver, "Buscar");
		clickOnFirstElementbyTag(driver, "button");
		TimeUnit.SECONDS.sleep(10);	
		clickOnFirstElementbyTag(driver, "buy-button");
		TimeUnit.SECONDS.sleep(5);
		if(checkTagExistence(driver, "upselling-popup") == true){
			clickOnButtonNamed(driver, "Continuar");
		}
		TimeUnit.SECONDS.sleep(10);
	
	}
	
	@After
	public void cleanup() throws Exception {
		driver.close();
	}
}
