package api.tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import api.endpoints.Methods;
import io.restassured.response.Response;

public class getOrderDetails {

	@Test(description="validate if user is able to retrive the order details")
	public void getOrderApiTest(ITestContext context) {
		String orderId = (String) context.getSuite().getAttribute("orderId");
		String token = (String) context.getSuite().getAttribute("token");

		Response resp = Methods.getOrderDetailsApi(token, orderId);

		int statuscode = resp.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		JSONObject jo = new JSONObject(resp.asString());
		String resp_orderid = (String) jo.getJSONObject("data").get("_id");
		Assert.assertEquals(resp_orderid, orderId);
		String productId = (String) context.getSuite().getAttribute("product_id");
		String resp_productId = (String) jo.getJSONObject("data").get("productOrderedId");

		Assert.assertEquals(resp_productId, productId);

	}

}
