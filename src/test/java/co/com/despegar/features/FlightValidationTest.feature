
@Search
Feature: Despegar.com.co Flight Validation BOG MDE
	I want to validate the selection screen of a flight in Despegar.com from Bogota to Medellin, with any departure/arrival date.
	
	Scenario: Validate Selection of a Flight with specified cities and dates
		Given The user is at Despegar.com.co Home Page.
		When They click on From city searchbox
		And They write "MED"
		And They pick the highlighted city in the menu
		And They click on To city searchbox
		And They write "BOG"
		And They pick the highlighted city in the menu
		And They pick the "15" and "25" as dates for the flight
		And They click on Search button
		And They go to the results page
		And They click on the first available flight
		And They check if theres an upsell popup to click continue
		Then They check the next page to match the expected screen.