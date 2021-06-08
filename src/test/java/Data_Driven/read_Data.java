package Data_Driven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class read_Data {
	static FileInputStream fis = null;

	public FileInputStream getFileInputStream() {
		String filePath = System.getProperty("user.dir") + "/src/test/java/Data_Driven/UserData.xlsx";
		File srcFile = new File(filePath);

		try {
			fis = new FileInputStream(srcFile);
		} catch (FileNotFoundException e) {
			System.out.println("Test Data file not found. treminating Process !! : Check file path of TestData");
			System.exit(0);
		}
		return fis;
	}

	public String[][] getExcelData(String sheetName) throws IOException {
		fis = getFileInputStream();
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int TotalNumberOfRows = (sheet.getLastRowNum());// remove +1
		int TotalNumberOfCols = sheet.getRow(0).getLastCellNum();
		String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfCols];

		for (int i = 1; i <= TotalNumberOfRows; i++) { // i=1; i<=
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < TotalNumberOfCols; j++) {
				XSSFCell cell = row.getCell(j);
				try {
					DataFormatter formatter = new DataFormatter();
					String value = formatter.formatCellValue(cell);
					// This formatter get my all values as string i.e integer, float all type
					arrayExcelData[i - 1][j] = value; // i-1
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		wb.close();
		return arrayExcelData;
	}
}