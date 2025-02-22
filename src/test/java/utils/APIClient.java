package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;

public class APIClient extends BaseTest {

    private static RequestSpecification getRequestSpec() {
        return given().baseUri(BASE_URL)
                .header("Authorization", "Bearer " + TOKEN)
                .contentType(ContentType.JSON);
    }

    public static Response sendGETRequest(String endpoint) {
        ReportLogger.startTest("GET Request: " + endpoint);


        Response response = getRequestSpec().when().get(endpoint);
        ReportLogger.logInfo("Response: " + response.asPrettyString());

        return response;
    }

    public static Response sendPOSTRequest(String endpoint, Map<String, ?> data) {

        ReportLogger.startTest("POST Request: " + endpoint);

        Response response = getRequestSpec().body(data).when().post(endpoint);
        ReportLogger.logInfo("Response: " + response.asPrettyString());

        return response;
    }

    public static Response sendPUTRequest(String endpoint, Map<String, String> data) {
        ReportLogger.startTest("PUT Request: " + endpoint);


        Response response = getRequestSpec().body(data).when().put(endpoint);
        ReportLogger.logInfo("Response: " + response.asPrettyString());

        return response;
    }

    public static Response sendPATCHRequest(String endpoint, Map<String, String> data) {
        ReportLogger.startTest("PATCH Request: " + endpoint);


        Response response = getRequestSpec().body(data).when().patch(endpoint);
        ReportLogger.logInfo("Response: " + response.asPrettyString());

        return response;
    }
}
