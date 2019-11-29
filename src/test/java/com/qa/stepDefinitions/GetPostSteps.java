package com.qa.stepDefinitions;

import java.io.File;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.pojo.Users;
import com.qa.properties.PropertyManager;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class GetPostSteps {

	//TestBase testBase;
	String baseUrl;
	String serviceUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	/*@Before
	public void before(Scenario scenario) {

		testBase = new TestBase();
	
	}*/

	@Given("^I set GET employee service api endpoint$")
	public void i_set_GET_employee_service_api_endpoint() throws Throwable {

		
		/*  baseUrl = prop.getProperty("baseUrl"); 
		  serviceUrl = prop.getProperty("serviceUrl"); 
		  this.url = baseUrl + serviceUrl;
		  */

		  baseUrl = PropertyManager.getInstance().getBaseUrl(); 
		  serviceUrl =PropertyManager.getInstance().getServiceUrl();
		  this.url = baseUrl + serviceUrl;

	}

	@Given("^I perform GET operation for \"(.*?)\"$")
	public void i_perform_GET_operation_for(String arg1) throws Throwable {
		
		//baseUrl = prop.getProperty("baseUrl");
		baseUrl = PropertyManager.getInstance().getBaseUrl();
		serviceUrl = "/api" + arg1;
		this.url = baseUrl + serviceUrl;
	}

	@Given("^I set POST employee service api endpoint$")
	public void i_set_POST_employee_service_api_endpoint() throws Throwable {
		
		 /* baseUrl = prop.getProperty("baseUrl");
		  serviceUrl = prop.getProperty("serviceUrl"); 
		  this.url = baseUrl + serviceUrl;*/
		  
		  
		  baseUrl = PropertyManager.getInstance().getBaseUrl(); 
		  serviceUrl =PropertyManager.getInstance().getServiceUrl();
		  this.url = baseUrl + serviceUrl;
	}

	@When("^I set request header$")
	public void i_set_request_header() throws Throwable {

	}

	@When("^I send GET HTTP request$")
	public void i_send_GET_HTTP_request() throws Throwable {

		restClient = new RestClient();
		restClient.get(url);
	}

	@When("^I send POST HTTP request$")
	public void i_send_POST_HTTP_request() throws Throwable {
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();

		Users userObj = new Users("morpheus", "leader");

		// object to json file
		mapper.writeValue(new File("users.json"), userObj);

		// object to json in string
		String userJsonString = mapper.writeValueAsString(userObj);

		restClient.post(url, userJsonString, headerMap);
	}

	@Then("^I receive valid HTTP Response code (\\d+)$")
	public void i_receive_valid_HTTP_Response_code(int arg1) throws Throwable {

		int actualStatusCode = restClient.getStatusCode();
		int expectedStatusCode = arg1;

		Assert.assertEquals(expectedStatusCode, actualStatusCode);

	}

	@Then("^I should see the first name as \"(.*?)\"$")
	public void i_should_see_the_first_name_as(String arg1) throws Throwable {

		String responseString = restClient.getResponseInString();
		String actualFirstName = restClient.getFirstName(responseString);

		Assert.assertEquals(arg1, actualFirstName);

	}

	@Then("^I should see the last name as \"(.*?)\"$")
	public void i_should_see_the_last_name_as(String arg1) throws Throwable {

		String responseString = restClient.getResponseInString();
		String actualLastName = restClient.getLastName(responseString);

		Assert.assertEquals(arg1, actualLastName);
	}

	@Then("^I should see the id as \"(.*?)\"$")
	public void i_should_see_the_id_as(String arg1) throws Throwable {

		String responseString = restClient.getResponseInString();
		String actualId = restClient.getId(responseString);

		Assert.assertEquals(arg1, actualId);
	}

	@Then("^I should see the email as \"(.*?)\"$")
	public void i_should_see_the_email_as(String arg1) throws Throwable {

		String responseString = restClient.getResponseInString();
		String actualEmail = restClient.getEmail(responseString);

		Assert.assertEquals(arg1, actualEmail);
	}

	@Then("^I receive valid response$")
	public void i_receive_valid_response() throws Throwable {

		String responseString = restClient.getResponseInString();
		System.out.println("Response String: " + responseString);

		JSONObject jsonObject = new JSONObject(responseString);

		String actualName = (String) jsonObject.get("name");
		String actualJob = (String) jsonObject.get("job");
		System.out.println("Name : " + jsonObject.get("name"));
		System.out.println("Job : " + jsonObject.get("job"));

		Assert.assertEquals("morpheus", actualName);
		Assert.assertEquals("leader", actualJob);

	}

	@Then("^I validate the \"(.*?)\" from CSV$")
	public void validateDataFromCSV(String arg1) throws Throwable {

		String expectedValue = TestBase.getCsvData(arg1);
		String actualValue = null;
		String responseString = restClient.getResponseInString();

		if (arg1.equals("First_Name"))
			actualValue = restClient.getFirstName(responseString);
		else if (arg1.equals("Last_Name"))
			actualValue = restClient.getLastName(responseString);
		else if (arg1.equals("ID"))
			actualValue = restClient.getId(responseString);
		else if (arg1.equals("Email"))
			actualValue = restClient.getEmail(responseString);

		Assert.assertEquals(expectedValue, actualValue);

	}

	@Then("^I validate first name as \"(.*?)\"$")
	public void i_validate_first_name_as(String arg1) throws Throwable {

		String responseString = restClient.getResponseInString();
		String actualFirstName = restClient.getFirstName(responseString);

		Assert.assertEquals(arg1, actualFirstName);

	}

	@Then("^I validate last name as \"(.*?)\"$")
	public void i_validate_last_name_as(String arg1) throws Throwable {

		String responseString = restClient.getResponseInString();
		String actualLastName = restClient.getLastName(responseString);

		Assert.assertEquals(arg1, actualLastName);

	}

	@Then("^I validate id as \"(.*?)\"$")
	public void i_validate_id_as(String arg1) throws Throwable {

		String responseString = restClient.getResponseInString();
		String actualId = restClient.getId(responseString);

		Assert.assertEquals(arg1, actualId);
	}

	@Then("^I validate email as \"(.*?)\"$")
	public void i_validate_email_as(String arg1) throws Throwable {

		String responseString = restClient.getResponseInString();
		String actualEmail = restClient.getEmail(responseString);

		Assert.assertEquals(arg1, actualEmail);
	}

}
