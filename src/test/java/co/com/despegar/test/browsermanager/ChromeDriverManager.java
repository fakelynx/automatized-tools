package co.com.despegar.test.browsermanager;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager{

	@Override
	protected void createDriver() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver-chrome/chromedriver_96.0.4664.45.exe");
	    driver = new ChromeDriver();
		
	}
	

}
