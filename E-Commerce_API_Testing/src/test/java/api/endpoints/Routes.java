package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Properties;

import api.utilities.readFromPropertiesFile;

public class Routes {
	
	// api endpoints/routes
	
	public static String baseUrl="https://rahulshettyacademy.com/api/ecom";
	
	
	public static String loginPostCall=baseUrl + "/auth/login";
	public static String addProductPostCall=baseUrl + "/product/add-product";
	public static String createOrderPostCall=baseUrl + "/order/create-order";
	public static String deleteProductDeleteCall=baseUrl + "/product/delete-product/{product_id}";
	public static String getOderDetailsGetCall=baseUrl + "/order/get-orders-details";

	

}
