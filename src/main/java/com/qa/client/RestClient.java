package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.qa.base.TestBase;


public class RestClient  {

	CloseableHttpResponse closeableHttpResponse;
	String responseString;

	// 1. GET Method
	public void get(String url) {

		// Create Client Connection
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(url); // http get request
	
		try {
			// hit the GET Url
			closeableHttpResponse = httpClient.execute(httpGet);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getStatusCode() {

		// 1. Status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

		System.out.println("Status code is: " + statusCode);

		return statusCode;
	}

	public String getResponseInString() {

		// 2. Response string
		if (responseString == null) {

			try {
				responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("*******************************");
			System.out.println("Response string: " + responseString);

		}

		return responseString;
	}

	public void getHeader() {

		// c. All Headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();

		Map<String, String> headerMap = new HashMap<String, String>();

		for (Header h : headerArray) {

			headerMap.put(h.getName(), h.getValue());
		}

		System.out.println("Header array: " + headerMap);

	}

	public String getFirstName(String responseString) {

		JSONObject responseJson = new JSONObject(responseString);

		JSONObject childJSONObject = responseJson.getJSONObject("data");

		Object firstName = childJSONObject.get("first_name");
		//System.out.println("First Name: " + childJSONObject.get("first_name"));

		return firstName.toString();
	}

	public String getAuthorName(String responseString) {

		JSONObject responseJson = new JSONObject(responseString);

		Object author = responseJson.get("author");
		System.out.println("Author Name: " + responseJson.get("author"));

		return author.toString();
	}
	
	public String getTitle(String responseString) {

		JSONObject responseJson = new JSONObject(responseString);

		Object title = responseJson.get("title");
		System.out.println("Title: " + responseJson.get("title"));

		return title.toString();
	}
	
	public String getLastName(String responseString) {

		JSONObject responseJson = new JSONObject(responseString);

		JSONObject childJSONObject = responseJson.getJSONObject("data");

//		System.out.println("Last Name: " + childJSONObject.get("last_name"));
		Object lastName = childJSONObject.get("last_name");

		return lastName.toString();
	}

	public String getId(String responseString) {

		JSONObject responseJson = new JSONObject(responseString);

		JSONObject childJSONObject = responseJson.getJSONObject("data");

//		System.out.println("ID : " + childJSONObject.get("id"));

		Object id = childJSONObject.get("id");

		return id.toString();

	}

	public String getEmail(String responseString) {

		JSONObject responseJson = new JSONObject(responseString);

		JSONObject childJSONObject = responseJson.getJSONObject("data");

//		System.out.println("Email: " + childJSONObject.get("email"));
		Object email = childJSONObject.get("email");

		return email.toString();

	}
	// Post Method
	
	    public void post(String url,String entityString,HashMap <String,String> headerMap) {

			// Create Client Connection
			CloseableHttpClient httpClient = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost(url); // http POST request
			try {
				httpPost.setEntity(new StringEntity(entityString));
				for(Map.Entry<String, String> entry : headerMap.entrySet()){
					
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
				
				closeableHttpResponse = httpClient.execute(httpPost);
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			//return closeableHttpResponse;
		}
	    
	    // DELETE method
	    public void delete(String url) {

			// Create Client Connection
			CloseableHttpClient httpClient = HttpClients.createDefault();

			HttpDelete httpDelete = new HttpDelete(url); // http get request
		
			try {
				// hit the DELETE Url
				closeableHttpResponse = httpClient.execute(httpDelete);
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    
	    //PUT method
	    public void put(String url,String entityString,HashMap <String,String> headerMap) {

			// Create Client Connection
			CloseableHttpClient httpClient = HttpClients.createDefault();

			HttpPut httpPut = new HttpPut(url); // http PUT request
			try {
				httpPut.setEntity(new StringEntity(entityString));
				for(Map.Entry<String, String> entry : headerMap.entrySet()){
					
					httpPut.addHeader(entry.getKey(), entry.getValue());
				}
				
				closeableHttpResponse = httpClient.execute(httpPut);
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//return closeableHttpResponse;
		}
	    
}
