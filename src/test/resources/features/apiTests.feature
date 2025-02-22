Feature: API Testing using Cucumber and Rest Assured


  @apitest @POST
  Scenario: Create a new user with POST  using data table and verify with get Request
    Given User send a POST request with the following details:
      | name     | email               | gender | status |
      | John Doe | johndoe@example.com | male   | active |
    Then the response status code should be 201
    And User store the user ID
    When User send a GET request
    Then the response status code should be 200
    And the response should contain the name "John Doe"

  @apitest @GET
  Scenario: Retrieve the user details with GET
    Given User sends a POST request with JSON file "userData.json"
    Then the response status code should be 201
    And User store the user ID
    When User send a GET request
    Then the response status code should be 200
    And the response should contain the name "John Doe"

  @apitest @PUT
  Scenario: Update an existing user with PUT and verify details
    Given User sends a POST request with JSON file "userData.json"
    Then the response status code should be 201
    And User store the user ID
    When User send a PUT request with the following details:
      | name         | email               | gender | status   |
      | Updated Name | updated@example.com | male   | inactive |
    Then the response status code should be 200
    And the response should contain the name "Updated Name"


  @apitest @PATCH
  Scenario: Partially update an existing user with PATCH and verify details
    Given User sends a POST request with JSON file "userData.json"
    Then the response status code should be 201
    And User store the user ID
    When User send a PATCH request with the following details:
      | status |
      | active |
    Then the response status code should be 200
    And the response should contain the status "active"
