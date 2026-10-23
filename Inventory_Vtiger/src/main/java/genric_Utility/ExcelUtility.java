package genric_Utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility
{

public String readDataFromExcel(String sheetName, int rowNum, int cellNum) throws IOException
{
    FileInputStream fis = new FileInputStream("./src/test/resources/TestData1.xlsx");
    Workbook wb = WorkbookFactory.create(fis);

    String data = "";

    if (wb.getSheet(sheetName) != null &&
        wb.getSheet(sheetName).getRow(rowNum) != null &&
        wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum) != null)
    {
        data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
    }

    wb.close();
    fis.close();

    return data;
}

}

