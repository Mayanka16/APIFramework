package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;

	/*
	 * Method to logging the request and response using
	 * addfilter(RequestLoggingFilter) and addfilter(ResponseLoggingFilter)for both
	 * request and response will give the txt file as mentioned in FileOutputStream
	 * class which will create in project structure rather to give the path manually
	 * because it will give the logs by itself and made *public static
	 * RequestSpecification req;* as static and req==null because without static
	 * keyword the 2nd run with set of data will be treated null and will not have
	 * any data so the logging.txt will have only the latest logs rather to have all
	 */

	public RequestSpecification requestSpecification() throws IOException {

		if (req == null) {

			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	/*
	 * Properties class used to load file and FileInputStream will take the path of
	 * the file from local where Properties object will load the file using
	 * FileInputStream object
	 */

	public static String getGlobalValues(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				"//Users//mayanka//eclipse-workspace//APIFramework//src//test//java//resources//global.properties");
		prop.load(file);
		return prop.getProperty(key);

	}

	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();

	}

}
