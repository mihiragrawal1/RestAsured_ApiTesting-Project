package api.listeners;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.ITestListener;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import api.report.extentReport;

public class listenersClass implements ITestListener{
	
	extentReport reportObj=new extentReport();
	ExtentReports extent=reportObj.reportConfig();
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test=extent.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		test.fail(result.getThrowable());
	}
	
	@Override
	public void onFinish(ITestContext context) 
	{
		extent.flush();
		
	}

}
