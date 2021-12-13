package co.com.despegar.test;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/co/com/despegar/features",
		glue = ("co.com.despegar.gluecode")
		
		)


public class FlightValidationTest {

}