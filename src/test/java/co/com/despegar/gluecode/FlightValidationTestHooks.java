package co.com.despegar.gluecode;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import co.com.despegar.test.browsermanager.DriverManager;
import co.com.despegar.test.browsermanager.DriverManagerFactory;
import co.com.despegar.test.browsermanager.DriverType;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class FlightValidationTestHooks {
	
	private static WebDriver driver;
	private static WebElement driver_element;
	private static Actions driver_action;
	private static WebDriverWait driver_wait_short;
	private static WebDriverWait driver_wait_long;
	private static DriverManager driverManager;
	private static JavascriptExecutor jse;


@Before
public void setUp() {
	
	driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
	driver = driverManager.getDriver();
	
    driver.manage().window().maximize();
    driver.get("https://www.despegar.com.co");
    driver_action = new Actions(driver);
    driver_wait_short = new WebDriverWait(driver, Duration.ofSeconds(2));
    driver_wait_long = new WebDriverWait(driver, Duration.ofSeconds(10));
    jse = (JavascriptExecutor)driver;
}

public static WebDriver getDriver() {
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

public static JavascriptExecutor getJse() {
	return jse;
}

@After
public void tearDown() {
	driverManager.quitDriver();
}
}
