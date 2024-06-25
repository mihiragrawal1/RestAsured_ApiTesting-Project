package api.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.base.BaseClass;
import api.endpoints.Methods;
import api.pojoclasses.login;
import io.restassured.response.Response;

public class loginTest extends BaseClass {

	login loginObj;

	@BeforeMethod()
	public void payloadSetup() {
		loginObj = new login();
		loginObj.setUserEmail(username);
		loginObj.setUserPassword(password);

	}

	@Test(priority=1,description="validate user login with valid credentials")
	public void validUserLoginTest(ITestContext context) {
		Response res = Methods.userLoginApi(loginObj);
		JSONObject jo = new JSONObject(res.asString());

		// validate/assert response 
		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		String loginSuccessMsg = jo.getString("message");
		Assert.assertEquals(loginSuccessMsg, loginsuccessmsg);
		
		// save userid and token from response to use in other api calls
		String user_id = jo.getString("userId");
		String token = jo.getString("token");
		context.getSuite().setAttribute("user_id", user_id);
		context.getSuite().setAttribute("token", token);

	}
	
	@Test(priority=2,description="validate user login with invalid credentials")
	public void invalidUserLoginTest()
	{
		login obj=new login();
		obj.setUserEmail("mihir#gmail.com");
		obj.setUserPassword("nhi pata");
		Response res = Methods.userLoginApi(obj);
		JSONObject jo = new JSONObject(res.asString());

		// validate/assert response 
		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 400);
		String errormsg=jo.getString("message");
		Assert.assertEquals(errormsg,"Incorrect email or password.");
		
	}

	
	

}
