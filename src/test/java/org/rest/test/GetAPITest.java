package org.rest.test;

import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.rest.TestBase.TestBase;
import org.rest.client.RestClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetAPITest extends TestBase
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
	
	/*@Test
	public void testRestClient()throws Exception
	{
		restClient = new RestClient();
		restClient.get(actualUrl);
	}*/
	
	/*@Test
	public void testRestClient()throws Exception
	{
		restClient = new RestClient();
		closeableHttpResponse = restClient.getResponse(actualUrl);
		
		//Get Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code Is : "+statusCode);
		Assert.assertEquals(statusCode, response_status_OK, "Status code is not 200");
		
		//Get Value of a JSON attribute (per_page) single value
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8"); //UTF-8 is a character format
		JSONObject responseJSON = new JSONObject(responseString); //Convert String to JSON
		System.out.println("Response JSON from API -> "+responseJSON);
		String perPageValue = TestUtil.getValueByJPath(responseJSON, "/per_page");
		Assert.assertEquals(perPageValue, "6","Value Not Mached");
		
		//Get Value of a JSON attribute(data[0]) Multiple values
		String id = TestUtil.getValueByJPath(responseJSON, "/data[0]/id");
		String last_name = TestUtil.getValueByJPath(responseJSON, "/data[0]/last_name");
		String avatar = TestUtil.getValueByJPath(responseJSON, "/data[0]/avatar");
		String first_name = TestUtil.getValueByJPath(responseJSON, "/data[0]/first_name");
		System.out.println(id+" "+avatar+" "+first_name+" "+last_name);
		
		//Get Headers
		Header[] allHeaders = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> headersMap = new HashMap<String, String>();
		
		for(Header h : allHeaders)
		{
			headersMap.put(h.getName(), h.getValue());
		}
		System.out.println("All Headers Are : "+headersMap);
	}*/
	
	@Test
	public void testRestClient()throws Exception
	{
		restClient = new RestClient();
		
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		closeableHttpResponse = restClient.getResponseWithHeaders(actualUrl,headerMap);
		
	}
}
