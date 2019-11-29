package com.qa.stepDefinitions1;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.pojo.Posts;
import com.qa.pojo.Users;
import com.qa.properties.PropertyManager;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class GetPostSteps1 {

	public String baseUrl;
	public String serviceUrl;
	public String url;
	public RestClient restClient;

	@Given("^I perform GET operation for \"([^\"]*)\"$")
	public void i_perform_GET_operation_for(String arg1) throws Throwable {

		baseUrl = PropertyManager.getInstance().getBaseUrl();
		this.url = baseUrl + arg1;

	}

	@Given("^I perform GET operation for post number \"([^\"]*)\"$")
	public void i_perform_GET_operation_for_post_number(String arg1) throws Throwable {

		baseUrl = PropertyManager.getInstance().getBaseUrl();
		this.url = baseUrl + arg1;

	}

	@Given("^I set POST employee service api endpoint for \"([^\"]*)\"$")
	public void i_set_POST_employee_service_api_endpoint_for(String arg1) throws Throwable {

		baseUrl = PropertyManager.getInstance().getBaseUrl();
		this.url = baseUrl + arg1;

	}

	@When("^I send GET HTTP request$")
	public void i_send_GET_HTTP_request() throws Throwable {

		restClient = new RestClient();
		restClient.get(url);
	}

	@Then("^I should see the author name as \"([^\"]*)\"$")
	public void i_should_see_the_author_name_as(String expectedAuthor) throws Throwable {

		String responseString = restClient.getResponseInString();
		String actualAuthor = restClient.getAuthorName(responseString);

		Assert.assertEquals(expectedAuthor, actualAuthor);

	}

	@Then("^I should see the title as \"([^\"]*)\"$")
	public void i_should_see_the_title_as(String expectedTitle) throws Throwable {

		String responseString = restClient.getResponseInString();
		String actualTitle = restClient.getTitle(responseString);

		Assert.assertEquals(expectedTitle, actualTitle);

	}
	@When("^I send POST HTTP request$")
	public void i_send_POST_HTTP_request(DataTable table) throws Throwable {
		
		List <List<String>> data = table.raw();
		
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();

		Posts postsObj = new Posts();
	
		postsObj.setTitle(data.get(1).get(0));
		postsObj.setAuthor(data.get(1).get(1));

		// object to json in string
		String postsJsonString = mapper.writeValueAsString(postsObj);

		restClient.post(url, postsJsonString, headerMap);
	}

	@Then("^I receive valid HTTP Response code (\\d+)$")
	public void i_receive_valid_HTTP_Response_code(int arg1) throws Throwable {

		int actualStatusCode = restClient.getStatusCode();
		int expectedStatusCode = arg1;

		Assert.assertEquals(expectedStatusCode, actualStatusCode);

	}

	@Then("^I receive valid response as$")
	public void i_receive_valid_response(DataTable table) throws Throwable {
		
		List <List<String>> data = table.raw();

		String responseString = restClient.getResponseInString();
		System.out.println("Response String: " + responseString);

		JSONObject jsonObject = new JSONObject(responseString);

		String actualAuthor = (String) jsonObject.get("author");
		String actualTitle = (String) jsonObject.get("title");
		System.out.println("Author : " + jsonObject.get("author"));
		System.out.println("Title : " + jsonObject.get("title"));

		Assert.assertEquals(data.get(1).get(0), actualTitle);
		Assert.assertEquals(data.get(1).get(1), actualAuthor);

	}
	
	@Given("^I perform POST operation for \"([^\"]*)\" with body as$")
	public void i_perform_POST_operation_for_with_body_as(String arg1, DataTable table) throws Throwable {

		restClient = new RestClient();
		List<List<String>> data = table.raw();
		
		baseUrl = PropertyManager.getInstance().getBaseUrl();
		//this.url = baseUrl + arg1 + "/" + data.get(1).get(0);
		this.url = baseUrl + arg1 ;
		
		System.out.println("url: "+url);

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();
		
		Posts postsObj = new Posts();
		
		System.out.println("data.get(1).get(1): "+data.get(1).get(1));
		System.out.println("data.get(1).get(2): "+data.get(1).get(2));
		postsObj.setId(data.get(1).get(0));
		postsObj.setTitle(data.get(1).get(1));
		postsObj.setAuthor(data.get(1).get(2));

		// object to json in string
		String postsJsonString = mapper.writeValueAsString(postsObj);

		restClient.post(url, postsJsonString, headerMap);
		
	}

	@When("^I perform DELETE operation for \"([^\"]*)\"$")
	public void i_perform_DELETE_operation_for(String arg1, DataTable table) throws Throwable {

		List<List<String>> data = table.raw();
		
		baseUrl = PropertyManager.getInstance().getBaseUrl();
		this.url = baseUrl + arg1 + data.get(1).get(0);
		
		restClient = new RestClient();
		restClient.delete(url);
	}

	@Then("^I should not see any response$")
	public void i_should_not_see_any_response() throws Throwable {
		
		
		String responseString = restClient.getResponseInString();
		System.out.println("Response String: " + responseString);

		if(responseString.isEmpty()){
			
			Assert.assertTrue(true);
		}
//		else
//			Assert.assertTrue(false);
		
	}
	@When("^I perform GET operation for \"([^\"]*)\" with post number as$")
	public void i_perform_GET_operation_for_with_post_number_as(String arg1, DataTable table) throws Throwable {

		List<List<String>> data = table.raw();
		
		baseUrl = PropertyManager.getInstance().getBaseUrl();
		this.url = baseUrl + arg1 + data.get(1).get(0);

		System.out.println("Get url: "+url);
		restClient = new RestClient();
		restClient.get(url);
	}
	
	@When("^I perform PUT operation for \"([^\"]*)\"$")
	public void i_perform_PUT_operation_for(String arg1, DataTable table) throws Throwable {

		List<List<String>> data = table.raw();
		
		baseUrl = PropertyManager.getInstance().getBaseUrl();
		this.url = baseUrl + arg1 +"/"+data.get(1).get(0);
		
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();

		Posts postsObj = new Posts();
		postsObj.setAuthor(data.get(1).get(2));
		postsObj.setTitle(data.get(1).get(1));
		postsObj.setId(data.get(1).get(0));

		// object to json in string
		String postsJsonString = mapper.writeValueAsString(postsObj);

		restClient.put(url, postsJsonString, headerMap);
	
	}

	@Then("^I should see body with title as \"([^\"]*)\"$")
	public void i_should_see_body_with_title_as(String expectedTitle) throws Throwable {
		
		String responseString = restClient.getResponseInString();
		System.out.println("Response String: " + responseString);

		JSONObject jsonObject = new JSONObject(responseString);

		String actualTitle = (String) jsonObject.get("title");
		System.out.println("Title : " + jsonObject.get("title"));

		Assert.assertEquals(expectedTitle, actualTitle);

		
	
	}

	
}
