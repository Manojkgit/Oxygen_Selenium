package frameWork;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

public class DummyTestC extends BaseTest{
	    @Test
	    public void testC1(){
	    test=rep.startTest("testC1");
	    test.log(LogStatus.INFO, "Start Test C.....");
	    test.log(LogStatus.PASS, "Result pass");
 }
	    @AfterMethod()
	    public void testDown(){
	    	rep.endTest(test);
	    	rep.flush();
	    }
}
