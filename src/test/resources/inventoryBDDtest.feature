Feature: Test CRUD Methods for inventory management REST API

  Scenario: Insert Inventory records in database
    Given API Service is started
    When User sends a POST request to insert inventory records then it receives a valid response


  Scenario: Fetch Inventory records from database
    Given API Service is started
    When User sends a GET request to fetch the inventory records by storeId "98305" then it receives a valid response

