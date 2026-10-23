
package PriceBooks;

import java.io.IOException;

import org.testng.annotations.Test;

import Object_repo.PriceBooksPage;
import genric_Utility.ExcelUtility;
import BaseClass_Utility.BaseClass;

public class PriceBooksNegativeTest extends BaseClass
{
    ExcelUtility eu = new ExcelUtility();

    @Test(groups = {"functional", "negative"})
    public void createPriceBookWithoutNameTest() throws IOException
    {
        PriceBooksPage pb = new PriceBooksPage(d);
        pb.openPriceBooks();
        pb.clickCreatePriceBook();

        String name = eu.readDataFromExcel("PriceBooksData", 6, 0);
        String currency = eu.readDataFromExcel("PriceBooksData", 6, 1);
        String description = eu.readDataFromExcel("PriceBooksData", 6, 2);

        pb.enterPriceBookName(name);
        pb.selectCurrency(currency);
        pb.enterDescription(description);
        pb.clickSave();

        // Add your existing validation/screenshot logic here
        // to verify that the application rejects the blank name.
    }

    @Test(groups = {"functional", "negative"})
    public void createPriceBookWithoutCurrencyTest() throws IOException
    {
        PriceBooksPage pb = new PriceBooksPage(d);
        pb.openPriceBooks();
        pb.clickCreatePriceBook();

        String name = eu.readDataFromExcel("PriceBooksData", 7, 0);
        String currency = eu.readDataFromExcel("PriceBooksData", 7, 1);
        String description = eu.readDataFromExcel("PriceBooksData", 7, 2);

        pb.enterPriceBookName(name);
        pb.enterDescription(description);

        // Skip currency selection when the Excel value is blank.
        if (!currency.trim().isEmpty())
        {
            pb.selectCurrency(currency);
        }

        pb.clickSave();

        // Add your existing validation/screenshot logic here
        // to verify that the application rejects the blank currency.
    }
}
