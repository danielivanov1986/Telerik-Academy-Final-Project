# WEare Social Network Project 

### **Overview**
Welcome to the **WEare** Social Network project repository, created as part of our final project at Telerik Academy. This repository provides comprehensive details and reports on the testing of the WEare Social Network project. The social network is designed to connect individuals who can mutually benefit by exchanging skills and services. Users can create posts, comments, and show appreciation through likes.

### **Running WEare Application Locally with Docker**
The WEare application is designed to be easily started on your local machine using Docker. All necessary configurations for starting the application and its associated database are described in the PDF file found in the weare folder. This document will guide you through the process of setting up and running the application with Docker.

### **Testing Strategy**
We have adopted the **Agile Scrum methodology**, organizing our work into sprints. Testing activities are structured to cover **UI testing** with **Selenium WebDriver** and **API testing** with **REST Assured** and **Postman**. Performance testing are also a part of our strategy. For more detailed information on our testing approach and strategies, refer to the **TestPlan** file in the **Documents** folder.

### **Repository Structure**
- **Documents**:
  - Contains the detailed test plan for the project, bug template and test case template.
**weare** foler:
**database** and **app** folders:
- Contains the Docker configuration and setup for running the WEare application locally, along with:
**DockerDesktopSetup_WEarepp.pdf**:
- Contains detailed instructions for building the Docker container and how to start the application
  **setup docker**: Defines the services for the application.

- **WEare_Postman_API**
  Includes **Weare_Postman_API** for API testing and **REPORT** folder.

- **WEare_REST_Assured**  
  Includes **REST_Assured** for API testing and **REPORT** folder.

-  **WEareProject_Selenium**  
   Includes **WEareProject_Selenium** for UI testing, **REPORT** and **ALLURE_REPORT** folders.

- **Jira Report**  
  Includes .png reports.

- **Performance Testing**  
  This folder contains statistics of performance testing conducted with **JMeter**.

### **Running the Tests**
After the WEare application is running locally (on localhost:8081), you can proceed with running the tests:
1. Running Selenium WebDriver Tests:
- Open your project in IntelliJ IDEA.
- Navigate to src/test/java in the project structure.
- Right-click on the java folder and select "Run All Tests".

2. Running REST Assured Tests:
- Open your project in IntelliJ IDEA.
- Navigate to src/test/java in the project structure.
- Right-click on the java folder and select "Run All Tests".

3. Running Postman Tests:
- Open Postman.
- Import the Postman collection and environment files from the Postman folder in your project.
- Click Import in the top-left corner of Postman.
- Choose Import File and select the Postman collection and environment files from the **WEare_Postman_API** folder in your project.
- After importing, select the imported Postman collection.
- right-click on the collection and select "Run" to execute the entire collection.

4. Running Performance Tests with JMeter:
- Open Jmeter.
- Navigate to the Performance Testing folder and open the JMX file in JMeter.
- Click the Start button (green play icon) in JMeter to run the test.
- View the results in JMeter's listeners (e.g., View Results Tree, Summary Report).

### **Viewing Jacoco and Newman Reports**
- To view Jacoco reports open .html file in the **REPORT** folders in **WEareProject_Selenium** and - **WEare_REST_Assured**.
- To view Newman report open .html file in the **REPORT** folder in **WEare_Postman_API**.

### **Viewing Allure Reports**
- To view the generated Allure report, follow these steps:
1. Open your terminal;
2. Navigate to the project directory;
3. Run the following command:
**allure serve allure-results**

This command will start a local server and automatically open the Allure report in your browser, displaying details such as passed/failed tests, test history, and more.

Feel free to explore each section to gain a comprehensive understanding of our project's testing and development process.
