package Vendor;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;
import Object_repo.VendorsPage;

public class VendorsTest extends BaseClass {

	// Test Case 1: Verify vendor creation with valid details
	@Test(priority = 1, groups = { "functional", "positive" })
	public void createVendorTest() {

		VendorsPage vp = new VendorsPage(d);

		vp.openVendors();
		vp.clickCreateVendor();

		vp.enterVendorName("UniqueVendor01");
		vp.selectGLAccount("300-Sales-Software");
		vp.enterEmail("unique01@example.com");
		vp.enterPhone("9876543210");

		vp.clickSave();

		Reporter.log("Vendor creation test completed", true);
	}

	// Test Case 2: Verify vendor records can be searched by Vendor Name
	@Test(priority = 2, groups = { "functional", "positive" })
	public void searchVendorByNameTest() {

		VendorsPage vp = new VendorsPage(d);

		vp.openVendors();
		vp.searchVendor("28CoffeeDay");

		Reporter.log("Vendor search test completed", true);
	}

	@Test(priority = 3, groups = { "functional", "positive" })
	public void updateVendorWebsiteTest() {

		VendorsPage vp = new VendorsPage(d);

		vp.openVendors();
		vp.openVendorByName("28CoffeeDay");
		vp.clickEditVendor();

		vp.enterVendorName("28CoffeeDay");
		vp.updateWebsite("https://updated-example.com");

		vp.clickSave();
		Assert.assertEquals(vp.getWebsiteValue(), "https://updated-example.com");

		Reporter.log("Vendor Website updated and verified successfully", true);
	}





   
	
}
