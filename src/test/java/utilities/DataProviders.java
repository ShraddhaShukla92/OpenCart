package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		String path = ".\\TestData\\LoginTest.xlsx";
		ExcelUtility xlUtil = new ExcelUtility(path);
		int totalrows = xlUtil.getRowCount("LoginDetails");
		int totalcols = xlUtil.getCellCount("LoginDetails", 1);
		String loginData[][] = new String[totalrows][totalcols];
		for(int i=1; i<=totalrows; i++) {
			for(int j=0; j<=totalcols; j++) {
				loginData[i-1][j] = xlUtil.getCellData("LoginDetails", i, j);
				
			}
		}
		return loginData;
	}

}
