package Data_Driven;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Data_Driven {
	@DataProvider(name="ExcelData")
	public Object[][] userRegisterData() throws IOException, InvalidFormatException
	{
		// get data from Excel Reader class 
		read_Data ER = new read_Data();
		return ER.getExcelData("sheet1");
	}
  @Test(dataProvider="ExcelData")
  public void DataDriven_TestCase(String name , String year , String color , String pantone_value ) {
	String Response = RestAssured.get("https://reqres.in/api/unknown").andReturn().asString();
	
	Assert.assertTrue(Response.contains(name));
	Assert.assertTrue(Response.contains(year));
	Assert.assertTrue(Response.contains(color));
	Assert.assertTrue(Response.contains(pantone_value));
  }
	  
  
}
