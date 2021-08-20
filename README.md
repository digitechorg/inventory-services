# inventory-services
This API will do Inventory Management services. This RestFulAPI has 2 seperate endpoints to handle single and bulk json payloads.
Please refer endpoint details to test CRUD operations on this API using PostMan or other Rest Clients.

# technology stack
Java 11

SpringBoot

Gradle (as build tool)

BDD Cucumber (for functional testing)

# Junit

Junit has been added for controller, service and repository class
Package : com.interviewrepl.inventory

# BDD Cucumber

For functional testing,inventoryBDDtest.feature file has been added 
and stepdefinitions and runner has been added inside package bdd.cucumber.test 

# Database
in-memory Database (H2)

Please configure this path in application.properties before starting application
_**replace C:/Users/44745/test with your user path in windows.**_

spring.datasource.url = jdbc:h2:file:C:/Users/44745/test

database console:
http://localhost:8080/h2


# API End Points

GET ALL:
http://localhost:8080/api/allinventory

GET BY ID:
http://localhost:8080/api/inventory/{storeId}

POST (single Json payload):
http://localhost:8080/api/inventory

POST (Multi Json Payload):
http://localhost:8080/api/inventory/bulk

PUT (single Json payload):
http://localhost:8080/api/inventory

POST (Multi Json Payload):
http://localhost:8080/api/inventory/bulk

DELETE BY ID:
http://localhost:8080/api/inventory/{storeId}


# Database Tables

This Database has 4 tables:

SELECT * FROM INVENTORY;  // Parent Table storeId as primary key

SELECT * FROM DELIVERY; // child Table storeId as foreign key

SELECT * FROM REFUND; // child Table storeId as foreign key

SELECT * FROM SALE; // child Table storeId as foreign key



# Test Data

Single Payload Json : 
https://github.com/digitechorg/inventory-services/blob/main/testData/SinglePayload.json

Multi Payload Json :
https://github.com/digitechorg/inventory-services/blob/main/testData/MultiPayload.json
