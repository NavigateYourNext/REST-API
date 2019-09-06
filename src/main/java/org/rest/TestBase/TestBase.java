package org.rest.TestBase;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase 
{
	public Properties prop;
	
	public static int response_status_OK = 200;
	public static int response_status_CREATED = 201;
	public static int response_status_BADREQUEST = 400;
	public static int response_status_UNAUTHORISED = 401;
	public static int response_status_FORBIDDEN = 403;
	public static int response_status_NOTFOUND = 404;
	public static int response_status_INTERNALSERVERERROR = 500;
	
	
	public TestBase()
	{
		try
		{	
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/org/rest/configuration/config.properties");
			prop.load(fis);
		}
		catch(Exception e)
		{
			System.out.println("Caught Error In Base Constructor: "+e.getMessage());
		}
	}
}
