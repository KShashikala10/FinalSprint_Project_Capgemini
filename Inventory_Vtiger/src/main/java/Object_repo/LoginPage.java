package Object_repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class LoginPage {

	WebDriver d;

	public LoginPage(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d , this);
	}
	
	@FindBy(name = "user_name")
	private WebElement un;
	
	@FindBy(name = "user_password")
	private WebElement pw;
	
	@FindBy(id = "submitButton")
	private WebElement loginBtn;
	
	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement verify;

	public void getUn(String value) {
	    System.out.println("Current URL: " + d.getCurrentUrl());
	    System.out.println("Current page title: " + d.getTitle());
	    un.sendKeys(value);
	}

	public void getPw(String value) {
		pw.sendKeys(value);
	}

	public void getLoginBtn() {
		loginBtn.click();
		Assert.assertTrue(verify.getText().contains("Home"), "Login is not sucessful");
		Reporter.log("Login Successful", true);
	}
	

public void clickLoginOnly()
{
    loginBtn.click();
}


public boolean verifyLoginError() {
    System.out.println("Current URL: " + d.getCurrentUrl());
    System.out.println("Page text: " + d.findElement(
            org.openqa.selenium.By.tagName("body")).getText());

    return d.findElement(
            org.openqa.selenium.By.tagName("body")).getText().length() > 0;
}


	
}