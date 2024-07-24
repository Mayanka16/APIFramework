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
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
	RequestSpecification request;
	ResponseSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	

	@Given("Add place Payload")
	public void add_place_payload() throws IOException {
		
		request = given().spec(requestSpecification()).body(data.addPlacePayLoad());
		//res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}

	@When("user calls {string} with POST Http Payload")
	public void user_calls_with_post_http_payload(String string) {
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = request.when().post("/maps/api/place/add/json").then().spec(res).extract().response();

	}

	@Then("the API call got sucess with sucess code {int}")
	public void the_api_call_got_sucess_with_sucess_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(), Expectedvalue);
	}

}
