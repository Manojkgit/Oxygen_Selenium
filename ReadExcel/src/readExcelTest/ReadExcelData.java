package readExcelTest;

import java.util.Hashtable;

public class ReadExcelData {

	public static void main(String[] args) {
		Xls_Reader xls = new Xls_Reader("C:\\Users\\ti01045\\Desktop\\TestData.xlsx");
		
		int row	 = xls.getRowCount("sheetName");
		System.out.println(row);
		
		int col	 = xls.getColumnCount("sheetName");
		System.out.println(col);
		String data = xls.getCellData("sheetName", 1, 7);
		System.out.println(data);
		
		String Coldata = xls.getCellData("sheetName", 1, 21);
		System.out.println(Coldata);
		
		 xls.setCellData("sheetName", "Password", 35, "Sumit");
		 xls.setCellData("sheetName", "Password", 30, "appium/selenium");
		
		Hashtable< String, String> table = new Hashtable<>();
		table.put("Name", "Kushwaha");
		System.out.println(table.get("Name"));
	}

}
