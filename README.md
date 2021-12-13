# automatized-tools - Ricardo Rodriguez Suarez
This repository's purpose is to contain the automatation challenge for Accenture Colombia's technical interview.

## Acceptance Criteria
Now you'll find each of the acceptance criteria and how they were implemented in the project:

### Upload Code to Github and share link once the challenge is completed
In this repository you'll find the full Java/Maven based project based on the frameworks: JUnit, Selenium, Cucumber. Where the directory structure is as follows:
  * src/test: Folder where used packages and resources can be found. 
    * java/co/com/despegar: Folder where the source code and java packages can be found.
    * resources: Folder where the used WebDrivers can be found.
  * .gitignore: File to avoid git from tracking unwanted/IDE related files.
  * pom.xml: Maven Dependencies File.
  
### Gradle/Maven
For this project the Maven package manager was used. This can be checked in the __pom.xml__ file.

### BDD (Cucumber)
Cucumber v7.1.0 was used for the Gherkin language description and its respective implementation using Java to work under BDD (Behavioral Driven Development) methodology.

### Design Pattern to choice
Within the project the Creational Design Pattern "Abstract Factory" is used in order to handle multiple WebDriver implementations. 
This pattern was chosen because there wasn't a specified browser to perform the test. The Creational Design Pattern "Abstract Factory" provides me the flexibility to work with 
multiple WebDrivers without making the source code too complex using Abstract classes (cool Java feature, btw) and making it more readable and mantainable.

## Bugs and Problems found in the webpage.
* On the City searchbox on Despegar's homepage, the first characters entered are deleted. Usual type experience will only delete the first character entered but 
even if you paste a character array, it will delete the whole input.
  * Found in: 
    * Google Chrome v96.0.4665.45
    * Firefox (Gecko) v0.30.0-win64
  * Considerations:
    * If searchboxes are populated when the page loads, this issue doesn't show up.
    
* "Destination" City searchbox in Home page needs to be clicked and a pause must be set in order to load the list with matching cities (wait for get http method that's not always the same).
If the user types too fast before the response is retrieved, no matching cities will be displayed until a new input is entered and the response is already retrieved.
  * Found in: 
    * Google Chrome v96.0.4665.45
    * Firefox (Gecko) v0.30.0-win64
  * Considerations:
    * This doesn't happen with the "Origin" City searchbox, since the responses are retrieved at the webpage first load.
    
