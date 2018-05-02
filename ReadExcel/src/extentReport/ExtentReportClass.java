package extentReport;

import java.io.File;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportClass {
    ExtentReports extent;
    ExtentTest test;
    @BeforeTest
    public void startReport(){
    	extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/MyTestReport.html", true);
    	extent.addSystemInfo("HostName", "Kushwaha").addSystemInfo("Environment", "QA").addSystemInfo("UserName", "Manoj");
    	extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
      }
    @Test
    public void demoReportPass(){
    	test = extent.startTest("demoReportPass");
    	Assert.assertTrue(true);
    	System.out.println("Condition pass report generated Succeffully....");
    	test.log(LogStatus.PASS, "Condition pass report generated.");
    }
    
    @Test
    public void demoReportFail(){
    	test = extent.startTest("demoReportFail");
    	Assert.assertTrue(false);
    	test.log(LogStatus.FAIL, "Condition fail report.");
    }
    
    @AfterMethod
    public void getResult(ITestResult result){
    	if(result.getStatus()==ITestResult.FAILURE){
    		test.log(LogStatus.FAIL, result.getThrowable());
    	}
    	extent.endTest(test);
    }
    @AfterTest
    public void teardown(){
    	extent.flush();
    	extent.endTest(test);
    	extent.close();
    }
}
