package co.com.despegar.test.browsermanager;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {

	@Override
	protected void createDriver() {
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/driver-gecko/geckodriver_0.30.0-win64.exe");
	    driver = new FirefoxDriver();
		
	}

}
