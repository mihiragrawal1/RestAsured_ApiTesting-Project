package api.tests;

import org.testng.annotations.Test;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import api.base.BaseClass;
import api.endpoints.Methods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class addProduct extends BaseClass{
	
	
	@Test(description="verify if user is able to add a product from addproduct api")
	public void addProductApiTest(ITestContext context)
	{
		//get the userid and token from ITestContext
		String userid=(String) context.getSuite().getAttribute("user_id");
		String token=(String) context.getSuite().getAttribute("token");
		//api call
		Response response=Methods.addProductApi(userid,token);
		
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 201);
		JSONObject jo=new JSONObject(response.asString());

		String successmsg=(String) jo.get("message");
		Assert.assertEquals(successmsg, addProductSuccessMessage);
		String product_id=jo.getString("productId");
		context.getSuite().setAttribute("product_id",product_id );
		
	}
	
	
	

}
