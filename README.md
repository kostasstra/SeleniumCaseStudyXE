# xeQAChallenge 
------
The project uses Java 8, TestNG, Selenium and builds with maven.

Your machine must have the corresponding JDK installed, maven will deal with the rest dependencies, which can be seen in the pom file.

In the folder resources you may find the web driver executables, chromedriver and chromedriver.exe. chromedriver is for linux,
while chromedriver.exe can be used for windows.

If you want to use another version of chrome you must replace the executable in resources with the corresponding one. You may find all the
available chromedriver versions here https://chromedriver.chromium.org/downloads

If you want to run the project in linux, you must go to TestExample.java and change chromeDriverPath from
path + "/src/main/resources/chromedriver.exe" to path + "/src/main/resources/chromedriver". This also applies for
the other test included in the project.

If you want to use another browser, add the web driver executable in the folder resources and make the necessary adjustments in TestExample.
E.g. to use firefox you need to place GeckoDriver.exe in resources, declare WebDriver driver as FirefoxDriver() and use
System.setProperty("webdriver.gecko.driver", path + "/src/main/resources/GeckoDriver.exe");

The default configuration of this project is to run in Windows, with **chrome 108.0.5359.71**

A maven project is created with test based on sellenium and testNG.
Three different tests are completed in order to improve the function of site "xe.gr"

# Run smoke test
- Create file testng.xml for the smoke test 
- In order to run the smoke test insert this command **mvn clean test -D suiteXmlFile=testng.xml** in the terminal.

# Versions
---------
Version chrome driver: 108.0.5359.71
Version Java SE Development Kit : 19.0.1

# Dependencies
-------------
- org.seleniumhq.selenium - version: 4.7.1
- org.testng - version: 7.7.0

# Plugin
-------
- org.apache.maven.plugins - version : 3.0.0-M7

### Part1
------
Folder name : **"returnedResultsForRentHouses"**
- On the context of this part 1 check if in page "House for rent",the results that returned related with "house for rent"

### Part2
------
Folder name: **"filterGreaterThanFiveHundredThousand"**
- In this part 2 check if the results that returned back in page "House for sales" are greater than 500.000

### Part3
------
Folder name: **"selectLandForSaleApplyPlotFilterSaveSearch"**
- In part 3 which based on "land for sale" checked if when user is login can save his search.If search is already saved, then the search is deleted

# Second project
I created a second project and I added the Cucumber
- https://github.com/kostasstra/caseStudyXE

