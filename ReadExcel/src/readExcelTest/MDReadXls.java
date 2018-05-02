package readExcelTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MDReadXls {
    @Test(dataProvider="getdata")
	public void testA(String RunMode,String Col1,String Col2) {
    	
    }
    @DataProvider
    public Object[][] getdata(){
    	Xls_Reader x = new Xls_Reader("C:\\Users\\ti01045\\Desktop\\TestData.xlsx");
		String sheetName = "sheetName";
		String testname = "TestB";
		int teststart = 1;
		while(!x.getCellData("sheetName", 0, teststart).equals(testname)){
			teststart++;
		}
		System.out.println(teststart);
		int rowdatastart = teststart+2;
		int colstart = teststart+1;
		int row = 0;
		while(!x.getCellData("sheetName", 0, rowdatastart+row).equals("")){
			row++;
		}
		System.out.println("----> " + row);
		
		int col = 0;
		while(!x.getCellData("sheetName", col, colstart).equals("")){
			col++;
		}
		System.out.println("-----> "+ col);
		int datarow =0;
		Object[][] data = new Object[row][col];
		for(int r=rowdatastart;r<rowdatastart+row;r++){
			for(int c=0;c<col;c++){
				System.out.print(x.getCellData("sheetName", c, r)+ " | ");
			}
			System.out.println(" ");
		}
		datarow++;
		return data;
	}
    
}

