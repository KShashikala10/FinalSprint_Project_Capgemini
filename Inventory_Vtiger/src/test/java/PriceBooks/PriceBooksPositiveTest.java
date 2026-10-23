
package PriceBooks;

import java.io.IOException;

import org.testng.annotations.Test;

import Object_repo.PriceBooksPage;
import genric_Utility.ExcelUtility;
import genric_Utility.DataProvider_Utility;
import BaseClass_Utility.BaseClass;

public class PriceBooksPositiveTest extends BaseClass
{
    ExcelUtility eu = new ExcelUtility();

    public String readData(int row, int column) throws IOException
    {
        return eu.readDataFromExcel("PriceBooksData", row, column);
    }

    @Test(groups = {"smoke", "positive"})
    public void verifyPriceBooksPage()
    {
        PriceBooksPage pb = new PriceBooksPage(d);
        pb.openPriceBooks();
        pb.verifyPriceBooksPage();
    }

    @Test(groups = {"functional", "positive"})
    public void verifyCreatePriceBookForm()
    {
        PriceBooksPage pb = new PriceBooksPage(d);
        pb.openPriceBooks();
        pb.clickCreatePriceBook();
        pb.verifyPriceBookForm();
    }


@Test(groups = {"functional", "positive"})
public void createPriceBookWithDescription() throws IOException
{
    PriceBooksPage pb = new PriceBooksPage(d);
    pb.openPriceBooks();
    pb.clickCreatePriceBook();

    String name = readData(2, 0);
    String currency = readData(2, 1);
    String description = readData(2, 2);

    pb.enterPriceBookName(name);

    System.out.println("Currency from Excel: [" + currency + "]");

    pb.selectCurrency(currency);
    pb.enterDescription(description);

    pb.clickSave();
    pb.verifyPriceBookCreated(name);
}


    @Test(groups = {"system", "positive"})
    public void verifyPriceBookSystemFlow() throws IOException
    {
        PriceBooksPage pb = new PriceBooksPage(d);
        pb.openPriceBooks();
        pb.clickCreatePriceBook();

        pb.enterPriceBookName(readData(3, 0));
        pb.selectCurrency(readData(3, 1));
        pb.enterDescription(readData(3, 2));

        pb.clickSave();
        pb.verifyPriceBookCreated(readData(3, 0));
    }

    @Test(groups = {"regression", "positive"})
    public void verifyPriceBookRegressionTest() throws IOException
    {
        PriceBooksPage pb = new PriceBooksPage(d);
        pb.openPriceBooks();
        pb.clickCreatePriceBook();

        pb.enterPriceBookName(readData(4, 0));
        pb.selectCurrency(readData(4, 1));
        pb.enterDescription(readData(4, 2));

        pb.clickSave();
        pb.verifyPriceBookCreated(readData(4, 0));
    }



@Test(groups = {"functional", "positive"})
public void createPriceBookForEditTest() throws IOException
{
    PriceBooksPage pb = new PriceBooksPage(d);
    pb.openPriceBooks();
    pb.clickCreatePriceBook();

    String name = readData(5, 0);
    String currency = readData(5, 1);
    String description = readData(5, 2);

    System.out.println("Price Book Name: [" + name + "]");
    System.out.println("Currency: [" + currency + "]");
    System.out.println("Description: [" + description + "]");

    pb.enterPriceBookName(name);
    pb.selectCurrency(currency);
    pb.enterDescription(description);

    pb.clickSave();
    pb.verifyPriceBookCreated(name);
}



@Test(groups = {"functional", "positive"})

public void editPriceBookTest() throws IOException
{
    PriceBooksPage pb = new PriceBooksPage(d);

    String oldName = readData(5, 0);
    String updatedName = readData(8, 0);

    System.out.println("Original name: " + oldName);
    System.out.println("Updated name: " + updatedName);

    pb.searchPriceBook(oldName);

    pb.editPriceBookByName(oldName);

    System.out.println("Current URL after Edit click: " + d.getCurrentUrl());
}


    @Test(groups = {"integration", "positive"})
    public void createPriceBookWithProduct() throws IOException, InterruptedException
    {
        PriceBooksPage pb = new PriceBooksPage(d);
        pb.openPriceBooks();
        pb.clickCreatePriceBook();

        pb.enterPriceBookName(readData(2, 0) + "_Product");
        pb.selectCurrency(readData(2, 1));
        pb.enterDescription(readData(2, 2));
        pb.clickSave();

        pb.clickMoreInformation();
        pb.clickProductsExpandButton();
        pb.clickSelectProducts();

        // This selects the product configured in PriceBooksPage.
        // That product must already exist in Vtiger CRM.
        pb.selectProduct();
        pb.clickAddToPriceBooks();
    }
}
