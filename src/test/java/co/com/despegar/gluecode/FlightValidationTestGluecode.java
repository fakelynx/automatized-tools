package co.com.despegar.gluecode;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FlightValidationTestGluecode {
	
	private ChromeDriver driver;
	WebElement driver_element;
	Actions driver_action;
	WebDriverWait driver_wait_short;
	WebDriverWait driver_wait_long;
	
	public void clickOnSomething(By xpath) throws InterruptedException {
		driver_element = driver.findElement(xpath);
		Actions driver_action = new Actions(driver);

	    driver_action.click(driver_element).perform();
	    
	    // Wait for multiple responses that are requested after the click(e.g.: fdf3936f2c or pictures?gid=)
	    TimeUnit.MILLISECONDS.sleep(100);
	}
	
	@Given("The user is at Despegar.com.co Home Page.")
	public void the_user_is_at_despegar_com_co_home_page() {
	    
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver-chrome/chromedriver_96.0.4664.45.exe");
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.get("https://www.despegar.com.co");
	    driver_action = new Actions(driver);
	    driver_wait_short = new WebDriverWait(driver, Duration.ofSeconds(2));
	    driver_wait_long = new WebDriverWait(driver, Duration.ofSeconds(30));
		
	}
	@When("They click on From city searchbox")
	public void they_click_on_from_city_searchbox() throws InterruptedException {
		By from_xpath = By.xpath("//input [@placeholder = 'Ingresa desde d�nde viajas']");
		clickOnSomething(from_xpath);
		
	}
	@When("They write {string}")
	public void they_write(String string) {
		//Despegar.com.co website removes the first character you insert, hence the need to add an initial empty space
		driver_element.sendKeys(" "+string); 
	}
	@When("They pick the highlighted city in the menu")
	public void they_pick_the_highlighted_city_in_the_menu() {
		driver_wait_short.until(ExpectedConditions.visibilityOfElementLocated(By.className("ac-group-container")));
	    driver_wait_short.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li [@class = 'item -active']")));
	    driver_action.click(driver.findElement(By.xpath("//li [@class = 'item -active']"))).perform();
	}
	@When("They click on To city searchbox")
	public void they_click_on_to_city_searchbox() throws InterruptedException {
		By to_flight_searchbox_locator = By.xpath("//input [@placeholder = 'Ingresa hacia d�nde viajas']");
		clickOnSomething(to_flight_searchbox_locator);
	}
	@When("They pick the {string} and {string} as dates for the flight")
	public void they_pick_the_and_as_dates_for_the_flight(String string, String string2) throws InterruptedException {
	    By depart_date_searchbox_locator = By.xpath("//input [@placeholder = 'Ida']");
	    By depart_date = By.xpath("//div [. ='" + string + "']");
	    By arrival_date = By.xpath("//div [. ='" + string2 + "']");
	    
	    clickOnSomething(depart_date_searchbox_locator);
	    driver_wait_short.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div [@class = 'sbox5-monthgrid']")));
	    clickOnSomething(depart_date);
	    clickOnSomething(arrival_date);
	    
	}
	@When("They click on Search button")
	public void they_click_on_search_button() throws InterruptedException {
		By search_button_locator = By.xpath("//button [@type ='button']");
	    clickOnSomething(search_button_locator);
	}
	@When("They wait for the page to load")
	public void they_wait_for_the_page_to_load() {
		driver_wait_long.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div [@class = 'main-content -show']")));
	}
	@When("They click on the first available flight")
	public void they_click_on_the_first_available_flight() throws InterruptedException {
		By select_button_locator = By.xpath("//button [@type ='buy-button']");
	    clickOnSomething(select_button_locator);
	}
	@When("They check if theres an upsell popup to click continue")
	public void they_check_if_theres_an_upsell_popup_to_click_continue() {
		//Depending on the dates for the flight, there might be an upsell for better flight equipment. 
		//User needs to click on continue to proceed
	    try {
	    	driver_wait_long.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div [@tag = 'upselling-popup']")));
	    	By continueButton = By.xpath("//em [. = 'Continuar']");
	    	clickOnSomething(continueButton);
	    }catch (Exception e) {
	    	System.out.println("Packing offer element-tag not found");
	    }
	}
	@Then("They check the next page to match the expected screen.")
	public void they_check_the_next_page_to_match_the_expected_screen() throws InterruptedException {
		String validationMessage = "�Falta poco! Completa tus datos y finaliza tu compra";
	    assertEquals(validationMessage, driver.findElement(By.xpath("//h2 [@class = 'chk-main-title -eva-3-hide-small -eva-3-hide-medium']")).getText());
	    
	    //Pause for visual validation
	    TimeUnit.SECONDS.sleep(2); 
	    driver.quit();
	}
}
