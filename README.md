# treeManager

Simple webapp editor of tree structure where each node stores numeric value.
Leafs of the tree displays the sum of their ancestor values but also stores their own "desiredValues" which will be displayed when they'll grow up (have their own children).

For each node you can change its value, delete (with all descendants), and add a child.

## Tests

There are two parts of tests: Unit, which test single methods from `service` part, and Integration which test if REST API works as expected by executing methods from `resource` part.

In order to run all tests, type following:
`$ mvn test`
and wait for the result

## Running the application

### Requirements
* JDK (at least 1.7)
* Apache Maven
* Internet connection

Application is powered by Springboot with embedded Tomcat and HSQL in-memory database. Application can be run by executing following line:

`$ mvn spring-boot:run` and after some time you should see on terminal message similar to:

`Tomcat started on port(s): 8080 (http)`

Go to [http://localhost:8080](http://localhost:8080) and enjoy the application!
