
package Login;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;

public class PositiveLoginTest extends BaseClass
{
    @Test
    public void positiveLoginTest()
    {
        Assert.assertTrue(d.getCurrentUrl().contains("module=Home"),
                "Login failed with valid credentials");

        Reporter.log("Positive login test passed", true);
    }
}
