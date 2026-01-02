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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import POJO.*;
import static org.junit.Assert.*;

public class StepDefinition {

	RequestSpecification res;
	ResponseSpecification response1;
	Response Whenresponse;

	@Given("Add Place Payload")
	public void add_place_payload() {

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		response1 = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		AddPlace p = new AddPlace();
		p.setAccuracy(40);
		p.setAddress("29, Default address");
		p.setLanguage("English");
		p.setPhone_number("34234234234");
		p.setWebsite("www.google.com");
		p.setName("FrontLine");
		List<String> MyList = new ArrayList<String>();
		MyList.add("shoe park");
		MyList.add("park");
		p.setTypes(MyList);
		Location l = new Location();
		l.setLat(-342434234);
		l.setLng(341312);
		p.setLocation(l);
		res = given().spec(req).body(p);
	}

	@When("user calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
		Whenresponse = res.log().all().when().post("/maps/api/place/add/json").then().log().all().spec(response1)
				.extract().response();
	}

	@Then("the API is success with status code {int}")
	public void the_api_is_success_with_status_code(Integer int1) {
		assertEquals(Whenresponse.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void status_in_response_body_is_ok(String KeyValue, String ExpectedValue) {
		String actualValue = Whenresponse.jsonPath().getString(KeyValue);
		assertEquals(ExpectedValue, actualValue);
	}
}
