package Rest_Script;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;

import static io.restassured.RestAssured.get;

import static io.restassured.RestAssured.given;

public class rest_testcases {
	
	@Test
	public void Validate_response_code_testcase1() {
		given().get("https://reqres.in/api/users?page=2").then().assertThat().statusCode(200); 
	}
	
	@Test
	public void Validate_response_code_testcase2() {
		Response Responce_body = RestAssured.get("https://reqres.in/api/users/2"); 
		Assert.assertEquals(Responce_body.statusCode(), 200);
	}
	
	@Test
	public void Validate_response_data_testcase3() {
		given().get("https://reqres.in/api/users/2").then().
		assertThat().body("data.'email'", equalTo ("janet.weaver@reqres.in") ).and().
		assertThat().body("data.first_name", equalTo("Janet")).and().
		assertThat().body("data.last_name",equalTo("Weaver")); 
	}
	
	@Test
	public void Validate_response_code_negativeCase() {
		given().get("https://reqres.in/api/users/23").then().assertThat().statusCode(200); 
	}
	
	@Test
	public void Validate_invalid_data_testCase() {
		String response = get("https://reqres.in/api/users?page=10000").andReturn().asString();
		Assert.assertTrue(response.contains("server costs are appreciated"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void userCreation_testCase()
	{		
		RestAssured.baseURI ="https://reqres.in/api/users";
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "khaled"); // Cast
		requestParams.put("job", "leader");
		request.body(requestParams.toJSONString());
		Response response = request.post();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
	}


	@SuppressWarnings("unchecked")
	@Test
	public void RegistrationUnSuccessful_testCase()
	{ 
		RestAssured.baseURI ="https://reqres.in/api/register";
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "sydney@fife");		
		request.body(requestParams.toJSONString());
		Response response = request.post();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);
	}
	
	@Test
	public void deleteUser_testCase () 
	{
		RestAssured.baseURI ="https://reqres.in/api/users";
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		request.body(requestParams.toJSONString());
		Response response = request.delete("/2");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 204);
	}
}
