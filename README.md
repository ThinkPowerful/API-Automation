# HTTPBin(http://httpbin.org/) API's testing with Data driven framework with Rest-Assured and TestNG libraries.

# Prerequisites to run this Project:

Java and Maven

# Commands to run the Project:

# Clone the repository:
$ git clone https://github.com/ThinkPowerful/API-Automation.git

# Set the directory path:
$ cd API-Automation

# Run test scenarios:
$ mvn exec:java -Dexec.mainClass="com.rest.main.utility.XmlGenerator" test

# To write a new test scenario, follow the steps below:

1.Navigate to following path API-Automation/ExcelFiles/TestSuite.xlsx and define the TSID, Test Scenario Name, API, Author, Active flag and Test case count.(Note: Test case count will be based on number of rows present in TestData sheet with which the same Scenario will be executed with different input/output parameters) 

2.Navigate to following path API-Automation/ExcelFiles/TestData.xlsx and create a sheet name with the TSID, and define your Test case ID, Test case description, actual input, expected output and comments if any.

3.Navigate to following path API-Automation/src/test/java/{respective API package}

4.Write the first automation script with testNG annotations which would look like following.

@Before Class
This is where test name and respective service class object initialization happens.

@Test
This is where the actual test script along with the extent report logs and assertion happens.

@DataProvider
This is where the data is supplied to the above @Test, based on the number of rows defined in the respective test scenario sheet in TestData.xlsx file.

Note: Every test script java file should extend the properties from BaseTest.java for report initialization.


CRUD METHODS implemented: GET, POST, PUT, DELETE

GET

POST

PUT

DELETE



# Test Reports Path:
Test Reports will always be generated in the path API-Automation/ExtentReports in format of {timestamp}.html
The report contains 4 Pane ( "Test Results" , "Category", "Author" , "Dashboard")- Refer to the sample test report

In the top right corner of the report there will be a indication of  type of testing(customizable), BaseURI(customizable), date and time of execution.

Test Results : Displays test results statuses and logs along with duration of execution and tags such as scripted by Author, API name etc for each test case.

Category : Categorized/grouped the test results based on API Services(Customizable according to project need)

Capabilities : Categorized/grouped the test results based on Author(Customizable according to project need)

Dashboard : Gives an overview of all the tests executed along with the duration and pie chart representation.