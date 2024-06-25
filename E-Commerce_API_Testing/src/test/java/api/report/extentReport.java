package api.report;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReport {
	
	
	ExtentReports report;
	public ExtentReports reportConfig()
	{
		
		String path=System.getProperty("user.dir")+ "//Report//Execution-Report.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test-Execution-Report");
		reporter.config().setReportName("Demo Project Execution Report");
		
	    report=new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Mihir");
		return report;
		
	}

}


