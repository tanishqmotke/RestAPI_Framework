package stepDefinitions;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;
import POJO.*;
import static org.junit.Assert.*;

public class StepDefinition extends Utils {

	RequestSpecification request;
	ResponseSpecification response;
	Response ResponseReceived;
	TestDataBuild tb = new TestDataBuild();

	@Given("Add Place Payload")
	public void add_place_payload() {
	response = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	request = given().spec(requestSpecification()).body(tb.addPlacePayLoad());
	}
	

	@When("user calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
		ResponseReceived = request.log().all().when().post("/maps/api/place/add/json").then().log().all().spec(response)
				.extract().response();
	}

	@Then("the API is success with status code {int}")
	public void the_api_is_success_with_status_code(Integer int1) {
		assertEquals(ResponseReceived.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void status_in_response_body_is_ok(String KeyValue, String ExpectedValue) {
		String actualValue = ResponseReceived.jsonPath().getString(KeyValue);
		assertEquals(ExpectedValue, actualValue);
	}
}
