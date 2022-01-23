
# E-Drone Delivery App

E-Drone is an task given to me by a company named Velsta Company. An application that allows you register a drone and 
make delivery with drones and allows communication between drone clients and API endpoints E-drone application is built 
with the following: Spring Framework, Java, MySQL, Maven tool and TDD.

#Feature
- Drone registration
- Querying of all idle drones
- Exception Validations
- public and private key pairs
- Checking loaded medications items on given drones
- Query drone battery percentage

Spring RESTFUL APIs while using postman to test the APIs

#Installation
- STEP 1: Clone repository
- STEP 2: Install dependencies: cd into root folder run the following command `mvn install` then `mvn compile` or you can 
  easily use `mvn verify` to run it all with just a command.
- STEP 3: Setup database: change directory into the db folder
- STEP 4: Run the project `mvn spring-boot-run`