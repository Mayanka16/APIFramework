package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification req;

	/*
	 * Method to logging the request and response using
	 * addfilter(RequestLoggingFilter) and addfilter(ResponseLoggingFilter)for both
	 * request and response will give the txt file as mentioned in FileOutputStream
	 * class which will create in project structure rather to give the path manually because it will give the logs by itself
	 */

	public RequestSpecification requestSpecification() throws IOException {

		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
	}
	
	
	/*
	 * Properties class used to load file and FileInputStream will take the path of
	 * the file from local where Properties object will load the file using
	 * FileInputStream object
	 */
	
	public static String  getGlobalValues(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("//Users//mayanka//eclipse-workspace//APIFramework//src//test//java//resources//global.properties");
		prop.load(file);
		return prop.getProperty(key);

		
	}

}
