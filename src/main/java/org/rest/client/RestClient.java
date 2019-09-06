package org.rest.client;

import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;



public class RestClient 
{
	//1. GET Method Without Headers
	
	/*public void get(String url)throws Exception
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();   //It will create 1 client connection and it returns CloseableHttpClient
		HttpGet httpget = new HttpGet(url); //It will create GET connection with particular URL known as HTTP Get Request
		
		CloseableHttpResponse closableHttpResponse =  httpClient.execute(httpget); //Hit the GET URL and will get response after URL execution
		
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode(); // To get status code of executed URL
		System.out.println("Status Code Is : "+statusCode);
		
		
		String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8"); //UTF-8 is a character format
		JSONObject responseJSON = new JSONObject(responseString); //Convert String to JSON
		System.out.println("Response JSON from API -> "+responseJSON);
		
		
		Header[] headerArray = closableHttpResponse.getAllHeaders(); //It will return all Headers
		HashMap<String, String> allHeaders = new HashMap<String,String>();
		
		for(Header header : headerArray)
		{
			allHeaders.put(header.getName(), header.getValue()); //Store all headers in HashMap
		}
		
		System.out.println("Total Headers Are :"+allHeaders);
		
	}*/
	
	// 2. 1. GET Method Without Headers
	
	public CloseableHttpResponse getResponse(String url)throws Exception
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();   //It will create 1 client connection and it returns CloseableHttpClient
		HttpGet httpget = new HttpGet(url); //It will create GET connection with particular URL known as HTTP Get Request
		
		CloseableHttpResponse closableHttpResponse =  httpClient.execute(httpget); //Hit the GET URL and will get response after URL execution
		
		return closableHttpResponse;
	}
	
	
	//3. 1. GET Method With Headers
	
	
	public CloseableHttpResponse getResponseWithHeaders(String url, HashMap<String, String> hashMap)throws Exception
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();   //It will create 1 client connection and it returns CloseableHttpClient
		HttpGet httpget = new HttpGet(url); //It will create GET connection with particular URL known as HTTP Get Request
		
		for(Entry<String,String> m : hashMap.entrySet())
		{
			httpget.addHeader(m.getKey(), m.getValue());
		}
		
		
		CloseableHttpResponse closableHttpResponse =  httpClient.execute(httpget); //Hit the GET URL and will get response after URL execution
		
		return closableHttpResponse;
	}
	
	
	//4. POST Method
	
	public CloseableHttpResponse post(String url, String entityString, HashMap<String,String> headerMap)throws Exception
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();   //It will create 1 client connection and it returns CloseableHttpClient
		HttpPost httppost = new HttpPost(url);
		
		httppost.setEntity(new StringEntity(entityString)); //Create Payload Here
		
		//for headers
		for(Entry<String,String> m : headerMap.entrySet())
		{
			httppost.addHeader(m.getKey(), m.getValue());
		}
		
		CloseableHttpResponse closableHttpResponse = httpClient.execute(httppost);
		
		return closableHttpResponse;
	}

}
