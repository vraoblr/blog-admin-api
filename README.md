# blog-admin-api
**How to run the Blog Admin API application**

**Prerequisites**
Before running the application, make sure you have the following prerequisites installed on your system:
1. Java Development Kit (JDK) version 17 or higher
2. Maven build tool

**Steps to Run the Application**
1. Clone the Repository
    git clone [https://github.com/vraoblr/blog-admin-api.git](https://github.com/vraoblr/blog-admin-api.git)
    
2. Navigate to the Project Directory
   cd blog-admin-api

3. Build the Application
   mvn clean package

4. Run the Application
   java -jar target/blog-admin-api-0.0.1-SNAPSHOT.jar

5. Access the API. 
    Once the application is running, you can access the API using the following URL in browser or in postman:
    http://localhost:8080
    
6. The Blog Admin API provides documentation using Swagger UI and Springdoc OpenAPI. To access the documentation, open the following URL in your web browser:
     http://localhost:8080/swagger-ui/index.html  
   The documentation provides detailed information about the available endpoints, request and response formats, and allows you to interact with the API directly from the UI.

8. Test the API
   You can run the provided unit tests by executing the following command:
    mvn test

9. You can assess the unit test coverage by accessing the Jacoco report located at "/target/site/jacoco/index.html" in the project's directory.



