package inventory_homepage;

import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;
import Object_repo.HomePage;

public class homePageTest extends BaseClass {

	@Test 
	public void test01() {
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.verifyHome();
		h.getServiceslink();
		h.getMorelink();
		h.getAssetslink();
		h.getMorelink();
		h.getPurchaselink();
		h.getMorelink();
		h.getPricelink();
		h.getMorelink();
		h.getVendorslink();
	}
}