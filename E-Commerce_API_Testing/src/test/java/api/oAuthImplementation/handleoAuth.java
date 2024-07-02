package api.oAuthImplementation;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class handleoAuth {

	@Test
	public void getTokenFromoAuth2() {

		// was trying to get the code for request from browser url but google has added some changes
		// due to which we cannot get that url and extract code from that

//		WebDriver driver = new ChromeDriver();
//
//		driver.get(
//				"https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//
//		WebElement inputBox = driver.findElement(By.cssSelector("input[type='email']"));
//
//		WebElement enterbtn = driver.findElement(By.cssSelector("div[class='VfPpkd-RLmnJb']"));
//		wait.until(ExpectedConditions.visibilityOf(inputBox));
//
//		inputBox.sendKeys("mihiragrawal296@gmail.com");
//		inputBox.sendKeys(Keys.ENTER);
//
//		WebElement passwordFeild = driver.findElement(By.cssSelector("input[type='password']"));
//
//		wait.until(ExpectedConditions.visibilityOf(passwordFeild));
//		passwordFeild.sendKeys("mihir@296");
//		passwordFeild.sendKeys(Keys.ENTER);
//
//		wait.until(ExpectedConditions.urlContains("code"));

		String Url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0ATx3LY7x559KGXctV35uas5Fvj89eVBXcsGkLG1fs5xpMRnZTcA8UyaZcEeXeykPDnoL3Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&hd=ayssoftwaresolution.com&prompt=none";
		System.out.println(Url);
		String[] arr=Url.split("code=");
		String code = arr[1];
		String[] newarr = code.split("&scope");
		String actualCode = newarr[0];
		System.out.println(actualCode);

		Response resp = given().urlEncodingEnabled(false).queryParam("code", actualCode)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").log().all().when()
				.post("https://www.googleapis.com/oauth2/v4/token");

		JSONObject jo = new JSONObject(resp.asString());

		String accessToken = (String) jo.get("access_token");
		System.out.println(accessToken);

		Response res = given().urlEncodingEnabled(false).queryParam("access_token", accessToken).log().all().when()
				.get("https://rahulshettyacademy.com/getCourse.php");

	}

}
