package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification request;
	ResponseSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String placeId;
	JsonPath js;

	@Given("Add place Payload with {string} {string} and {string}")
	public void add_place_payload_with_and(String name, String language, String address) throws IOException {

		request = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));

	}

	@When("user calls {string} with {string} Http Payload")
	public void user_calls_with_post_http_payload(String resource, String httpMethod) {

		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println("The API is : " + resourceAPI.getResource());

		if (httpMethod.equalsIgnoreCase("POST")) {
			res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			response = request.when().post(resourceAPI.getResource());
		} else if (httpMethod.equalsIgnoreCase("GET"))
			response = request.when().get(resourceAPI.getResource());

	}

	@Then("the API call got success with success code {int}")
	public void the_api_call_got_sucess_with_sucess_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		assertEquals(getJsonPath(response, keyValue), Expectedvalue);

	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		placeId = getJsonPath(response, "place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_post_http_payload(resource, "Get");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		request = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));

	}
}
