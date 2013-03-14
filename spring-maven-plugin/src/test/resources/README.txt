The pom.xml in this directory is a bootstrap testing pom

It contains executions that test the spring-maven-plugin using the Maven command line

This provides a simple (but manual) way to quickly perform integration testing of spring-maven-plugin
in a way that exactly replicates the end user experience. 

To make use of the bootstrap pom execute these 3 commands from the root of the checkout:

mvn clean install -DskipTests
cd target/test-classes
mvn test
