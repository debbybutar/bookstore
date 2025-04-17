# QA Assement Solution by Debby
This project is a simple demo of login page automation testing using:
- Selenium (UI Testing)
- RestAssured (API Testing) 
- Java
- TestNG

## Prerequisites
- Java Development Kit (JDK)
- Maven: project dependencies manager
- Google Chrome: could be any version, but make sure to have a version that you can get the match 
- ChromeDriver: Must be a compatible with your Google Chrome version. Check here to get ChromeDriver: https://googlechromelabs.github.io/chrome-for-testing/

## Setup Instructions
### Step 1: Clone repository
```
https://github.com/debbybutar/bookstore
```
### Step 2: Install a Compatible Version of ChromeDriver
- Check your Google Chrome version, example:
Version 131.0.6778.70
![alt text](image-1.png)
- Add the ChromeDriver to this specific path "/usr/local/bin/".
to verify, go to your "/usr/local/bin" directory, and it must look like this
![alt text](image.png)
```
/usr/local/bin/
- LICENSE.chromedriver
- THIRD_PARTY_NOTICES.chromedriver
- chromedriver
```
### Step 3: Install Dependencies
- Open terminal, cd to the project's directory where it's located on your end
```
cd Documents/Projects/selenium-test
```
- install dependencies
```
mvn clean install
```
### Step 4: Run the Test Suite
```
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```
Note on testng.xml
This file defines which test classes to run. Update it based on what you want to test:
✅ To run UI tests: point "testng.xml" to classes inside "com.UI".
✅ To run API tests: point "testng.xml" to classes inside "com.HTTP".

