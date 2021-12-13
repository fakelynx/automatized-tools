package co.com.despegar.gluecode;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class FlightValidationTestHooks {
	
	private static ChromeDriver driver;
	private static WebElement driver_element;
	private static Actions driver_action;
	private static WebDriverWait driver_wait_short;
	private static WebDriverWait driver_wait_long;
	
@Before
public void setUp() {
	System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver-chrome/chromedriver_96.0.4664.45.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.despegar.com.co");
    driver_action = new Actions(driver);
    driver_wait_short = new WebDriverWait(driver, Duration.ofSeconds(2));
    driver_wait_long = new WebDriverWait(driver, Duration.ofSeconds(10));
}

public static ChromeDriver getDriver() {
	return driver;
}

public static WebElement getDriver_element() {
	return driver_element;
}

public static Actions getDriver_action() {
	return driver_action;
}

public static WebDriverWait getDriver_wait_short() {
	return driver_wait_short;
}

public static WebDriverWait getDriver_wait_long() {
	return driver_wait_long;
}

@After
public void tearDown() {
	driver.quit();
}
}
