package readExcelTest;

public class DataManagement {
	public static void main(String[] args) {
		Xls_Reader xls = new Xls_Reader("C:\\Users\\ti01045\\Desktop\\TestData.xlsx");
		String sheetName = "sheetName";
		String testname= "TestB";
		int teststart = 1;
		while(!xls.getCellData(sheetName, 0, teststart).equals(testname)){
			teststart++;
		}
		System.out.println("---- > " + teststart);
		int rowstartnum = teststart+2;
		int colrowstart = teststart+1;
		int row = 1;
		while(!xls.getCellData(sheetName, 0, rowstartnum+row).equals("")){
			row++;
		}
		System.out.println("---- > " + row);
		
		int col = 0;
		while(!xls.getCellData(sheetName, col, colrowstart).equals("")){
			col++;
		}
		System.out.println("---- > " + col);
		
		for(int r = rowstartnum;r<rowstartnum+row;r++){
			for(int c=0;c<col;c++){
				System.out.print(xls.getCellData(sheetName, c, r)+" | ");
			}
			System.out.println(" ");
		}
	}
}
