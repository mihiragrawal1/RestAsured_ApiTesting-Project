package api.tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import api.endpoints.Methods;
import io.restassured.response.Response;

public class deleteProduct {

	@Test(description="verify is user is able to delete product using delete-product api")
	public void deleteProductApiTest(ITestContext context)
	{
		String token=(String) context.getSuite().getAttribute("token");
	String productId=(String) context.getSuite().getAttribute("product_id");
	Response resp=Methods.deleteProductApi(token, productId);
	
//	System.out.println(resp.getStatusCode());
	int statuscode=resp.getStatusCode();
	Assert.assertEquals(statuscode, 200);
	JSONObject js=new JSONObject(resp.asString());
	String successDeleteMsg=(String) js.get("message");
	Assert.assertEquals(successDeleteMsg,"Product Deleted Successfully");
	
	}
}
