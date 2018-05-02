package readExcelTest;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTestA {
  @Test(dataProvider="getdata")
  public void testA(Hashtable<String, String> data){
	  System.out.println(data.get("RunMode") + "--- "+data.get("Col1") + "--- "+data.get("Col2"));
 }
  @DataProvider
  public Object[][] getdata(){
	  Xls_Reader x = new Xls_Reader("C:\\Users\\ti01045\\Desktop\\TestData.xlsx");
	  String sheetName = "sheetName";
	  String testcase = "TestB";
	  int teststart = 1;
	  while(!x.getCellData(sheetName, 0, teststart).equals(testcase)){
		  teststart++;
	  }
	  System.out.println(teststart);
	  int rowstart = teststart+2;
	  int colstart = teststart+1;
	  int row = 1;
	  while(!x.getCellData(sheetName, 0, rowstart+row).equals("")){
		  row++;
	  }
	  System.out.println(row);
	  int col = 0;
	  while(!x.getCellData(sheetName, col, colstart).equals("")){
		  col++;
	  }
	  System.out.println(col);
	  int rowfound=0;
	  Object[][] data = new Object[row][1];
	  Hashtable<String, String> table =null;
	  for(int r=rowstart;r<rowstart+row;r++){
		  table = new Hashtable<String, String>();
		  for(int c=0;c<col;c++){
			  String key = x.getCellData(sheetName, c, colstart);
			  String value = x.getCellData(sheetName, c, r);
			  table.put(key, value);
		  }
		  data[rowfound][0] = table;
		  rowfound++;
	  }
	  return data;	  
  }
}
