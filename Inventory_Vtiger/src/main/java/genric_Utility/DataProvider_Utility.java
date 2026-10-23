package genric_Utility;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProvider_Utility
{
    @DataProvider(name = "assetsData")
    public Object[][] getAssetsData() throws IOException
    {
        ExcelUtility eu = new ExcelUtility();

        Object[][] data = new Object[2][4];

        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                data[i][j] = eu.readDataFromExcel("AssetsData", i + 1, j);
            }
        }

        return data;
    }
    
    

@DataProvider(name = "loginData")
public Object[][] getLoginData() throws IOException {
    ExcelUtility eu = new ExcelUtility();

    Object[][] data = new Object[4][3];

    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 3; j++) {
            data[i][j] = eu.readDataFromExcel("LoginData", i + 1, j);
        }
    }

    return data;
}




@DataProvider(name = "priceBooksData")
public Object[][] getPriceBooksData() throws IOException
{
    ExcelUtility eu = new ExcelUtility();

    Object[][] data = new Object[7][3];

    for (int i = 0; i < 7; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            data[i][j] = eu.readDataFromExcel("PriceBooksData", i + 1, j);
        }
    }

    return data;
}


@DataProvider(name = "priceBooksPositiveData")
public Object[][] getPriceBooksPositiveData() throws IOException
{
    ExcelUtility eu = new ExcelUtility();

    Object[][] data = new Object[5][3];

    for (int i = 0; i < 5; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            data[i][j] = eu.readDataFromExcel("PriceBooksData", i + 1, j);
        }
    }

    return data;
}

@DataProvider(name = "priceBooksNegativeData")
public Object[][] getPriceBooksNegativeData() throws IOException
{
    ExcelUtility eu = new ExcelUtility();

    Object[][] data = new Object[2][3];

    for (int i = 0; i < 2; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            data[i][j] = eu.readDataFromExcel("PriceBooksData", i + 6, j);
        }
    }

    return data;
}


}