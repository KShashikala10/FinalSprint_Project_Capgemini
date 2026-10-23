
package Assests;

import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;
import Object_repo.AssetsPage;

public class AssestsTest extends BaseClass
{
    // Test Case 1: Verify asset record using Asset No
    @Test(priority = 1, groups = { "functional", "positive" })
    public void verifyAssetRecordTest()
    {
        AssetsPage ap = new AssetsPage(d);
        ap.openAssets();

        ap.searchAssetByNo("jxhxij39");
        ap.verifyAssetSearchResult("jxhxij39");

        Reporter.log("Asset record verified successfully", true);
    }

    // Test Case 2: Verify original asset name remains unchanged after cancellation
    @Test(priority = 2, groups = { "regression", "positive" })
    public void cancelAssetEditTest()
    {
        AssetsPage ap = new AssetsPage(d);
        ap.openAssets();

        ap.searchAssetByNo("jxhxij39");
        ap.verifyAssetSearchResult("jxhxij39");

        ap.openAssetForEditing("jxhxij39");
        ap.enterTemporaryAssetName("Cancel Test Laptop");
        ap.clickCancel();

        ap.searchAssetByNo("jxhxij39");
        ap.verifyAssetSearchResult("jxhxij39");
        ap.verifyAssetNameUnchanged("jxhxij39", "final_assest01");

        Reporter.log("Asset name remains unchanged after cancellation", true);
    }
}

