package api.endpoints;

import static io.restassured.RestAssured.*;
import java.io.File;

import api.pojoclasses.createOrderPojo;
import api.pojoclasses.login;
import io.restassured.response.Response;

public class Methods {

//	RequestSpecification reqSpec=new RequestSpecBuilder().setContentType(ContentType.JSON).build();

	public static Response userLoginApi(login payload) {
		Response res = given().headers("Content-Type", "application/json; charset=utf-8").body(payload).log().all()
				.when().post(Routes.loginPostCall);

		return res;
	}

	public static Response addProductApi(String user_id, String token) {

		Response res = given().param("productName", "shirt").param("productAddedBy", user_id)
				.param("productCategory", "anything").param("productSubCategory", "anything")
				.param("productPrice", "5000").param("productDescription", "abcdefghijklmnopqrstuvwxyz")
				.param("productFor", "male")
				.multiPart("productImage", new File(System.getProperty("user.dir") + "/CAR_WALPAPER.jpeg"))
				.headers("Authorization", token).log().all().when().post(Routes.addProductPostCall);

		return res;

	}

	public static Response createOrderApi(createOrderPojo payload, String token) {
		Response res = given().headers("Authorization", token).headers("Content-Type", "application/json").body(payload)
				.log().all().when().post(Routes.createOrderPostCall);
		return res;
	}

	public static Response getOrderDetailsApi(String token, String orderid) {
		Response res = given().headers("Authorization", token).queryParam("id", orderid).log().all().when()
				.get(Routes.getOderDetailsGetCall);
		return res;

	}
	
	public static Response deleteProductApi(String token,String pro_id)
	{
		Response res=given().headers("Authorization",token).pathParam("product_id",pro_id)
		.log().all().when().delete(Routes.deleteProductDeleteCall);
		return res;
	}

}
