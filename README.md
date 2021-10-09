
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

**Navigate to following path API-Automation/ExcelFiles/TestSuite.xlsx and define the TSID, Test Scenario Name, API, Author, Active flag and Test case count.**(Note: Test case count will be based on number of rows present in TestData sheet with which the same Scenario will be executed with different input/output parameters) 

**Navigate to following path API-Automation/ExcelFiles/TestData.xlsx and create a sheet name with the TSID, and define your Test case ID, Test case description, actual input, expected output and comments if any.**

**Navigate to following path API-Automation/src/test/java/{respective API package}**

**Write the first automation script with testNG annotations which would look like following.**

_@Before Class_
This is where test name and respective service class object initialization happens.

_@Test_
This is where the actual test script along with the extent report logs and assertion happens.

_@DataProvider_
This is where the data is supplied to the above @Test, based on the number of rows defined in the respective test scenario sheet in TestData.xlsx file.

**Note: Every test script java file should extend the properties from BaseTest.java for report initialization.**


#Base URL: http://httpbin.org

CRUD METHODS implemented: GET, POST, PUT, DELETE

#GET Retrieves the request header parameters in the response body and verifies the status code and certain attributes in the response payload.

**URL: http://httpbin.org/headers**

**Response Body:**
{
    "headers": {
        "Accept": "application/json",
        "Z-Forwarded-For": "AAAAAAAAAAAA",
        "User-Agent": "Apache-HttpClient/4.5.3 (Java/1.8.0_171)",
        "Host": "httpbin.org",
        "Xroxy-Connection": "Keep-Alive",
        "Accept-Encoding": "gzip,deflate",
        "X-Amzn-Trace-Id": "Root=1-6161af38-7c6126b4658a291a778a7446"
    }
}

#POST: Posts the request with the below request payload and verifies if the provided request payload is coming in the response payload along with the response status code.

**URL: http://httpbin.org/anything**

**Request Body:**
{
  "firstName": "Richard",
  "lastName": "Samson"
}

**Response Body:**
 "data": "{\"firstName\":\"Richard\",\"lastName\":\"Samson\"}",


#PUT: A put request is sent as multipart form data and verifies if the provided input is coming up in the response attribute along with response status code.

**URL: http://httpbin.org/anything**

**Request form data:**
Country:Netherlands
City:Amsterdam

**Response Body:**

"form": {
        "country": "Netherlands",
        "city": "Amsterdam"
    },


#DELETE: A delete request is sent as a query parameter and verifies if the provided input is coming up in the response attribute along with response status code.

**URL: http://httpbin.org/anything**

**Request Query Param:** 
Skip:Anything
Limit:10

**Response Body:**
 "args": {
        "limit": "10",
        "skip": "Anything"
    },


# Test Reports Path:
Test Reports will always be generated in the path API-Automation/ExtentReports in format of {timestamp}.html
The report contains 4 Pane ( "Test Results" , "Category", "Author" , "Dashboard")- Refer to the sample test report

In the top right corner of the report there will be a indication of  type of testing(customizable), BaseURI(customizable), date and time of execution.

**Test Results** : Displays test results statuses and logs along with duration of execution and tags such as scripted by Author, API name etc for each test case.

**Category** : Categorized/grouped the test results based on API Services(Customizable according to project need)

**Capabilities** : Categorized/grouped the test results based on Author(Customizable according to project need)

**Dashboard** : Gives an overview of all the tests executed along with the duration and pie chart representation.


