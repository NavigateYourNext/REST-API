package org.rest.test;

import java.io.File;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.rest.TestBase.TestBase;
import org.rest.client.RestClient;
import org.rest.data.Users;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PostAPITest extends TestBase
{
	TestBase testBase;
	RestClient restClient;
	String actualUrl = null;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp()
	{
		testBase = new TestBase();
		String url = prop.getProperty("URL");
		String serviceUrl = prop.getProperty("serviceURL");
		
		 actualUrl = url + serviceUrl;
	}
	
	@Test
	public void postAPITestWithHeaders()throws Exception
	{
		restClient = new RestClient();
		
		//Add Header
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("content-type", "application/json");
		
		//JACKSON API to convert JAVA OBJ -> JSON known as MARSHALING
		
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("Akshay","Test Engineer");
		
		//JAVA OBJECT to JSON
		mapper.writeValue(new File(System.getProperty("user.dir")+"/JSON_Files/users.json"), users); //Create JSON file from Java Objects
		
		//Convert JSON OBJECT to JSON STRING
		String usersJsonString = mapper.writeValueAsString(users);
		
		closeableHttpResponse = restClient.post(actualUrl, usersJsonString, headerMap);
		
		//1. Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, response_status_CREATED, "Not Created Succesfully");
		
		//2. JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8").toString();
		
		JSONObject jsonObject = new JSONObject(responseString);
		System.out.println("Response JSON String Is : "+jsonObject);
		
		//3. Read Any Values from Response JSON to JAVA OBJECT known as UnMarshaling
		Users usr = mapper.readValue(responseString, Users.class);
		
		Assert.assertTrue(users.getName().equals(usr.getName()));
		Assert.assertTrue(users.getJob().equals(usr.getJob()));
		
		
	}
}
