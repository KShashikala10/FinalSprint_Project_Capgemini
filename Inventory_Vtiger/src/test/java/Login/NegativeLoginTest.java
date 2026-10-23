
package Login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;
import Object_repo.LoginPage;
import genric_Utility.DataProvider_Utility;
import genric_Utility.PropertyFile;
import genric_Utility.Screenshot_Utility;

public class NegativeLoginTest extends BaseClass
{
    @Test(dataProvider = "loginData", dataProviderClass = DataProvider_Utility.class)
    public void negativeLoginTest(String username, String password, String expectedResult) throws Exception
    {
        if (expectedResult.equalsIgnoreCase("Fail"))
        {
            logout();
            
            WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("user_name")));

            LoginPage lp = new LoginPage(d);
            lp.getUn(username);
            lp.getPw(password);
            lp.clickLoginOnly();
            Screenshot_Utility.takeScreenshot(d, "NegativeLoginTest");

            
            Assert.assertTrue(lp.verifyLoginError(),
                    "Login error was not displayed for invalid credentials");

            Reporter.log("Negative login test passed", true);
        }
    }
}
