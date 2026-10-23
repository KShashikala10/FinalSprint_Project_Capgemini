
package Service;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;
import Object_repo.ServicesPage;
import genric_Utility.ExcelUtility;

public class ServicesTest extends BaseClass {


@Test(priority = 1, groups = {"smoke"})
public void createServiceTest() throws IOException {

    ExcelUtility eLib = new ExcelUtility();
    ServicesPage sp = new ServicesPage(d);

    String serviceName = eLib.readDataFromExcel("CreateService", 1, 1).trim();
    String usageUnit = eLib.readDataFromExcel("CreateService", 1, 3).trim();
    String noOfUnits = "1";
    String category = eLib.readDataFromExcel("CreateService", 1, 4).trim();
    String price = eLib.readDataFromExcel("CreateService", 1, 2).trim();
    String description = eLib.readDataFromExcel("CreateService", 1, 5).trim();

    sp.openServices();
    sp.clickCreateService();

    sp.enterServiceName(serviceName);
    sp.selectUsageUnit(usageUnit);
    sp.enterNoOfUnits(noOfUnits);
    sp.selectCategory(category);
    sp.enterPrice(price);
    sp.enterServiceDescription(description);

    sp.saveCreatedService();

    Reporter.log("Service creation submitted: " + serviceName, true);
}



@Test(priority = 2, groups = {"functional", "positive"})
public void searchServiceByNumberTest() throws IOException {

    ExcelUtility eLib = new ExcelUtility();
    ServicesPage sp = new ServicesPage(d);

    // Read Service Number from Excel
    String serviceNumber = eLib.readDataFromExcel("CreateService", 1, 0).trim();

    // Open Services module
    sp.openServices();

    // Search Service by Number
    sp.searchServiceByNumber(serviceNumber);

    // Verify Service Record
    Assert.assertTrue(sp.verifyServiceRecord(serviceNumber),
            "Service " + serviceNumber + " was not displayed");

    Reporter.log("Service " + serviceNumber + " displayed successfully", true);
}



@Test(priority = 3, groups = {"functional", "positive"})
public void updateServiceDescriptionTest() throws IOException {

    ExcelUtility eLib = new ExcelUtility();
    ServicesPage sp = new ServicesPage(d);

    // Read Service Number from Excel
    String serviceNumber = eLib.readDataFromExcel("CreateService", 1, 0).trim();

    // Open Services module
    sp.openServices();

    // Search Service by Number
    sp.searchServiceByNumber(serviceNumber);

    // Verify Service Record
    Assert.assertTrue(sp.verifyServiceRecord(serviceNumber),
            "Service " + serviceNumber + " was not displayed");

    // Open the service and edit its description
    sp.openServiceByNumber(serviceNumber);
    sp.clickEdit();

    sp.updateDescription("Updated software testing and support service");
    sp.clickSave();

    Reporter.log("Service description updated successfully for " + serviceNumber, true);
}

}


   