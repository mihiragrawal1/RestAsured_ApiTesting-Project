package api.tests;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.base.BaseClass;
import api.endpoints.Methods;
import api.pojoclasses.createOrderPojo;
import api.pojoclasses.orderDetails;
import io.restassured.response.Response;

public class createOrder extends BaseClass{
	createOrderPojo payloadObj;
	@BeforeMethod
	public void payloadSetup(ITestContext context) {
		orderDetails detailObj=new orderDetails();
		String pro_id=(String) context.getSuite().getAttribute("product_id");
		detailObj.setCountry("India");
		detailObj.setProductOrderedId(pro_id);
		
		List <orderDetails> orderDetailsList=new ArrayList<orderDetails>();
		orderDetailsList.add(detailObj);
		
		payloadObj=new createOrderPojo();
		payloadObj.setOrders(orderDetailsList);
		
		System.out.println(payloadObj);
		
	}
	
	@Test(description="validate if user is able to create a order using createorder api")
	public void createOrderApiTest(ITestContext context) {
		String token=(String) context.getSuite().getAttribute("token");
		
		Response resp=Methods.createOrderApi(payloadObj,token);
		 
		JSONObject jo=new JSONObject(resp.asString());
//		System.out.println(resp.getStatusCode());
	int statusCode=resp.getStatusCode();
		
		Assert.assertEquals(statusCode, 201);   //orderSuccessfullmsg
		String successmsg=jo.getString("message");
		Assert.assertEquals(successmsg, orderSuccessfullmsg);
		String pro_id=(String) jo.getJSONArray("productOrderId").get(0);
		Assert.assertEquals(pro_id,context.getSuite().getAttribute("product_id"));
		String  order_id=(String) jo.getJSONArray("orders").get(0);
		context.getSuite().setAttribute("orderId", order_id);

	}
	
}




