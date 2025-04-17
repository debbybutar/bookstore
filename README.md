# QA Assement Solution by Debby
This project is a simple demo of login page automation testing using:
- Selenium 
- Java
- TestNG

## Prerequisites
- Java Development Kit (JDK)
- Maven: project dependencies manager
- Google Chrome: could be any version, but make sure to have a version that you can get the match 
- ChromeDriver: Must be a compatible with your Google Chrome version. Check here to get ChromeDriver: https://googlechromelabs.github.io/chrome-for-testing/

## Setup Instructions
### Step 1: Extract the .zip file
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
### Step 2: Install Dependencies
- Open terminal, cd to the project's directory where it's located on your end
```
cd Documents/Projects/selenium-test
```
- install dependencies
```
mvn clean install
```
### Step 3: Run the Test Suite
```
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

