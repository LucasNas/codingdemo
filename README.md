# codingdemo
Application developed using spring boot and Java 11
## Running the app
After cloning the app run the following command while in the main app folder:

> ./mvnw spring-boot:run  -Dspring-boot.run.arguments="'\<input file path\>' '\<optional output file path\>'"

*The input file path has to be the absolute path to a file to be processed 

*If not output file path is provided a txt file with the results will be created inside the project folder
## Running unit tests with maven
to run only the unit tests use the followinf command while in the main app folder:

> mvn test
