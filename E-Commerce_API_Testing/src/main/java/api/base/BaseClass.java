package api.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.testng.annotations.BeforeMethod;

public class BaseClass {
	
	public static  String username;
	public static String password;
	public static String loginsuccessmsg;
	public static String addProductSuccessMessage;
	public static String orderSuccessfullmsg;
	
	
	@BeforeMethod()
	public void setup()
	{
	 	 //another way to read data from properties file using resourceBundle class object-->
		
//		ResourceBundle data=ResourceBundle.getBundle("api_details");
//		System.out.println(data.getString("username")+"******++++++++");
		
		
			Properties prop = new Properties();
			try {
				FileInputStream fis = new FileInputStream(
						System.getProperty("user.dir") +"/src/test/resources/api_details.properties");
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}


			username=prop.getProperty("username");
			password=prop.getProperty("password");
			
			loginsuccessmsg=prop.getProperty("loginSuccessMsg");
			addProductSuccessMessage=prop.getProperty("addProductSuccessMessage");
			orderSuccessfullmsg=prop.getProperty("orderSuccessfullmsg");
		}


}
