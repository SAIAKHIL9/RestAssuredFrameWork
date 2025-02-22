package steps;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utils.APIClient;
import utils.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class APISteps {
    private static int userId;
    private Response response;

    @Given("User send a POST request with the following details:")
    public void sendPOSTRequest(DataTable table) {
        Map<String, String> data = new HashMap<>(table.asMaps().get(0));
        data.put("email", "test" + System.currentTimeMillis() + "@example.com");

        response = APIClient.sendPOSTRequest(ConfigManager.getProperty("users.endpoint"), data);
        userId = response.jsonPath().getInt("id"); // Store user ID for later
    }

    @Then("User store the user ID")
    public void storeUserId() {
        System.out.println("Stored User ID: " + userId);
    }

    @When("User send a PUT request with the following details:")
    public void sendPUTRequest(DataTable table) {
        Map<String, String> data = new HashMap<>(table.asMaps().get(0));
        data.put("email", "updated" + System.currentTimeMillis() + "@example.com");
        response = APIClient.sendPUTRequest(ConfigManager.getUserEndpointWithId(userId), data);
    }

    @When("User send a GET request")
    public void sendGETRequest() {
        response = APIClient.sendGETRequest(ConfigManager.getUserEndpointWithId(userId));
    }

    @When("User send a PATCH request with the following details:")
    public void sendPATCHRequest(DataTable table) {
        Map<String, String> data = new HashMap<>(table.asMaps().get(0));

        response = APIClient.sendPATCHRequest(ConfigManager.getUserEndpointWithId(userId), data);
    }

    @Then("the response status code should be {int}")
    public void validateStatusCode(int statusCode) {
        response.then().log().all().statusCode(statusCode);
    }

    @Then("the response should contain the name {string}")
    public void validateNameInResponse(String name) {
        Response getResponse = APIClient.sendGETRequest(ConfigManager.getUserEndpointWithId(userId));
        getResponse.then().body("name", equalTo(name));
    }

    @Then("the response should contain the status {string}")
    public void validateStatusInResponse(String status) {
        Response getResponse = APIClient.sendGETRequest(ConfigManager.getUserEndpointWithId(userId));
        getResponse.then().body("status", equalTo(status));
    }

    @Given("User sends a POST request with JSON file {string}")
    public void sendPOSTRequestWithJson(String jsonFilePath) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> requestData = objectMapper.readValue(new File("src/test/resources/testdata/" + jsonFilePath), Map.class);


        requestData.put("email", "test" + System.currentTimeMillis() + "@example.com");

        response = APIClient.sendPOSTRequest(ConfigManager.getProperty("users.endpoint"), requestData);
        userId = response.jsonPath().getInt("id");


    }


}
