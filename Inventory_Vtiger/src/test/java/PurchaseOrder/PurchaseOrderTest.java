
package PurchaseOrder;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Object_repo.PurchaseOrderPage;
import genric_Utility.ExcelUtility;

public class PurchaseOrderTest extends BaseClass_Utility.BaseClass {

    @Test(groups = {"functional"})
    public void createPurchaseOrderTest() throws IOException {

        ExcelUtility eLib = new ExcelUtility();
        PurchaseOrderPage po = new PurchaseOrderPage(d);

        // Step 1: Open Purchase Order page
        po.openPurchaseOrder();

        // Step 2: Click Create Purchase Order
        po.clickCreatePurchaseOrder();

        // Step 3: Read data from Excel
        String subject = eLib.readDataFromExcel(
                "CreatePurchaseOrderDataSubject", 1, 0);

        String vendorName = eLib.readDataFromExcel(
                "CreatePurchaseOrderDataSubject", 1, 1);

        String billingAddress = eLib.readDataFromExcel(
                "CreatePurchaseOrderDataSubject", 1, 2);

        String shippingAddress = eLib.readDataFromExcel(
                "CreatePurchaseOrderDataSubject", 1, 3);

        // Step 4: Fill Purchase Order details
        po.enterSubject(subject);
        po.selectVendor(vendorName);
        po.enterBillingAddress(billingAddress);
        po.enterShippingAddress(shippingAddress);

        po.selectProduct("laptop");
        po.enterQuantity("1");
        po.clickSave();

        System.out.println(
                "Create Purchase Order submitted: " + subject);
    }
    
    

@Test(groups = {"regression"})
public void editPurchaseOrderTest() throws IOException {

    ExcelUtility excel = new ExcelUtility();
    String poNumber = excel.readDataFromExcel("Purchaseorder", 1, 0);
    String updatedSubject = excel.readDataFromExcel("Purchaseorder", 1, 2);

    PurchaseOrderPage po = new PurchaseOrderPage(d);

    // Open Purchase Order module
    po.openPurchaseOrder();

    // Search for the existing Purchase Order
    po.searchPurchaseOrder(poNumber);
    po.selectPurchaseOrderNo();
    po.clickSearch();

    // Click Edit for the matching Purchase Order
    po.clickEditForPurchaseOrder(poNumber);

    // Update Subject and save
    po.updateSubject(updatedSubject);
    System.out.println("Subject entered: " + updatedSubject);

    po.clickSave();
    System.out.println("Current URL after Save: " + d.getCurrentUrl());

    // Verify updated Subject
    if (po.verifyUpdatedSubject(updatedSubject)) {
        System.out.println("Edit Purchase Order passed: " + updatedSubject);
    } else {
        Assert.fail("Edit Purchase Order failed: " + updatedSubject);
    }
}



@Test(groups = {"smoke"})
public void searchPurchaseOrderTest() throws IOException {
    
    ExcelUtility excel = new ExcelUtility();
    PurchaseOrderPage po = new PurchaseOrderPage(d);

    String poNumber = excel.readDataFromExcel("Purchaseorder", 1, 0).trim();

    po.openPurchaseOrder();
    po.searchPurchaseOrder(poNumber);
    po.selectPurchaseOrderNo();
    po.clickSearch();

    if (po.verifyPurchaseOrderSearch(poNumber)) {
        System.out.println("Search Purchase Order passed: " + poNumber);
    } else {
        Assert.fail("Search Purchase Order failed: " + poNumber);
    }
}


}
