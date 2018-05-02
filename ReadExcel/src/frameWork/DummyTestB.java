package frameWork;
import java.util.Hashtable;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

public class DummyTestB extends BaseTest{
	@Test(dataProvider="getData")
	    public void testB(){
		  test = rep.startTest("testB");
		  test.log(LogStatus.INFO, "Test B Start....");
     	  openBrowser("chrome");
     	  test.log(LogStatus.INFO,"Test B Open Browser...");
		  navigate("appurl");
		  TextType("username_xpath", "admin");
		  //If User Password field is not present.
		  TextTypepass("password_id", "demo123");
		  //reportingFail("Password is not present");
		  Click("Login_xpath");
		  //reportingFail("Title not found....");
		  test.log(LogStatus.PASS, "Test B pass.....");
		  takeScreenShot();
	 }
	
	@AfterMethod
	public void tearDown(){
		rep.endTest(test);
		rep.flush();
	}
	 @DataProvider
	    public Object[][] getData(){
	    	Xls_Reader x = new Xls_Reader("C:\\Users\\ti01045\\Desktop\\Customer ID_1's.xlsx");
	    	String sheetName = "Data";
	    	String test = "TestC";
	    	int testStartrow = 1;
	    	while(!x.getCellData(sheetName, 0, testStartrow).equals(test)){
	    		testStartrow++;
	    	}
	    	System.out.println("testStartrow -- > " + testStartrow);
	    	int ColsStart = testStartrow+1;
	    	int DataStart = testStartrow+2;
	    	int row = 0;
	    	while(!x.getCellData(sheetName, 0, DataStart+row).equals("")){
	    		row++;
	    	}
	    	System.out.println("row -- > " + row);
	    	int col = 0;
	    	while(!x.getCellData(sheetName, col, ColsStart).equals("")){
	    		col++;
	    	}
	    	System.out.println("col -- > " + col);
	    	Object[][] data = new Object[row][1];
	    	int rowdata=0;
	    	Hashtable<String,String> tab  =null; 
	    	for(int r=DataStart;r<DataStart+row;r++){
	        	tab = new Hashtable<String,String>();
	    	for(int c=0;c<col;c++){
	    			String key =   x.getCellData(sheetName, c, ColsStart);
	        		String value = x.getCellData(sheetName, c, r);
	        		tab.put(key, value);
	    		}
	    		data[rowdata][0] = tab;
	    		rowdata++;
	    	}
	    	return data;
	    }
}
