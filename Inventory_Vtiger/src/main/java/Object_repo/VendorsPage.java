package Object_repo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class VendorsPage {

	WebDriver d;

	public VendorsPage(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d, this);
	}

	// More menu
	@FindBy(xpath = "//td[contains(@onmouseover,'fnvshobjMore')]")
	private WebElement moreMenu;

	// Vendors link
	@FindBy(xpath = "//a[@id='more' and @name='Vendors']")
	private WebElement vendorsLink;

	// Create Vendor button
	@FindBy(xpath = "//img[@title='Create Vendor...']")
	private WebElement createVendorButton;

	// Vendor Name
	@FindBy(name = "vendorname")
	private WebElement vendorNameTextField;

	// Email
	@FindBy(name = "email")
	private WebElement emailTextField;

	// Phone
	@FindBy(id = "phone")
	private WebElement phoneTextField;

	// GL Account
	@FindBy(name = "glacct")
	private WebElement glAccountDropdown;

	// Website
	@FindBy(name = "website")
	private WebElement websiteTextField;

	// Category
	@FindBy(name = "category")
	private WebElement categoryTextField;

	// Street
	@FindBy(name = "street")
	private WebElement streetTextField;

	// PO Box
	@FindBy(name = "pobox")
	private WebElement poBoxTextField;

	// City
	@FindBy(name = "city")
	private WebElement cityTextField;

	// State
	@FindBy(name = "state")
	private WebElement stateTextField;

	// Postal Code
	@FindBy(name = "postalcode")
	private WebElement postalCodeTextField;

	// Country
	@FindBy(name = "country")
	private WebElement countryTextField;

	// Description
	@FindBy(name = "description")
	private WebElement descriptionTextField;

	// Save button
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	// Search field
	@FindBy(name = "search_text")
	private WebElement searchTextField;

	// Search dropdown
	@FindBy(name = "search_field")
	private WebElement searchDropdown;

	// Search Now button
	@FindBy(name = "submit")
	private WebElement searchButton;

	@FindBy(xpath = "//a[contains(@href,'updated-example.com')]")
	private WebElement websiteValue;

	@FindBy(xpath = "//a[contains(@href,'action=EditView') and contains(@href,'module=Vendors')]")
	private WebElement editVendorButton;

	// Open Vendors page
	public void openVendors() {

		Actions a = new Actions(d);
		a.moveToElement(moreMenu).perform();

		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(vendorsLink));

		vendorsLink.click();

		wait.until(ExpectedConditions.urlContains("module=Vendors"));

		Assert.assertTrue(d.getCurrentUrl().contains("module=Vendors"), "Vendors page is not displayed");

		Reporter.log("Vendors page displayed successfully", true);
	}

	// Verify Vendors page
	public void verifyVendorsPage() {

		Assert.assertTrue(d.getCurrentUrl().contains("module=Vendors"), "Vendors page is not displayed");

		Reporter.log("Vendors page verified successfully", true);
	}

	// Open Create Vendor form
	public void clickCreateVendor() {

		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(createVendorButton));

		createVendorButton.click();

		Reporter.log("Create Vendor form opened", true);
	}

	// Enter Vendor Name
	public void enterVendorName(String name) {
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(vendorNameTextField));

		vendorNameTextField.clear();
		vendorNameTextField.sendKeys(name);

		Reporter.log("Vendor name entered: " + vendorNameTextField.getAttribute("value"), true);
	}

	// Enter Email
	public void enterEmail(String email) {
		emailTextField.clear();
		emailTextField.sendKeys(email);
	}

	// Enter Phone
	public void enterPhone(String phone) {
		phoneTextField.clear();
		phoneTextField.sendKeys(phone);
	}

	// Select GL Account
	public void selectGLAccount(String account) {

		Select s = new Select(glAccountDropdown);
		s.selectByVisibleText(account);

		Reporter.log("GL Account selected: " + account, true);
	}

	// Enter Website
	public void enterWebsite(String website) {
		websiteTextField.clear();
		websiteTextField.sendKeys(website);
	}

	// Enter Category
	public void enterCategory(String category) {
		categoryTextField.clear();
		categoryTextField.sendKeys(category);
	}

	// Enter Street
	public void enterStreet(String street) {
		streetTextField.clear();
		streetTextField.sendKeys(street);
	}

	// Enter PO Box
	public void enterPOBox(String poBox) {
		poBoxTextField.clear();
		poBoxTextField.sendKeys(poBox);
	}

	// Enter City
	public void enterCity(String city) {
		cityTextField.clear();
		cityTextField.sendKeys(city);
	}

	// Enter State
	public void enterState(String state) {
		stateTextField.clear();
		stateTextField.sendKeys(state);
	}

	// Enter Postal Code
	public void enterPostalCode(String postalCode) {
		postalCodeTextField.clear();
		postalCodeTextField.sendKeys(postalCode);
	}

	// Enter Country
	public void enterCountry(String country) {
		countryTextField.clear();
		countryTextField.sendKeys(country);
	}

	// Enter Description
	public void enterDescription(String description) {
		descriptionTextField.clear();
		descriptionTextField.sendKeys(description);
	}

	// Click Save

	public void clickSave() {
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(saveButton));
		saveButton.click();
		Reporter.log("Save button clicked", true);
	}

	// Search Vendor by Name
	public void searchVendor(String name) {

		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

		searchTextField.clear();
		searchTextField.sendKeys(name);

		Select s = new Select(searchDropdown);
		s.selectByVisibleText("Vendor Name");

		wait.until(ExpectedConditions.elementToBeClickable(searchButton));

		searchButton.click();

		Reporter.log("Vendor search completed for: " + name, true);
	}

	public void openVendorByName(String name) {

		searchVendor(name);

		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

		WebElement vendorLink = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[@title='Vendors' and normalize-space()='" + name + "']")));

		vendorLink.click();

		Reporter.log("Vendor opened: " + name, true);
	}

	public void updateWebsite(String website) {

		websiteTextField.clear();
		websiteTextField.sendKeys(website);

		Reporter.log("Website updated to: " + website, true);
	}

	// Get page title
	public String getVendorsPageTitle() {
		return d.getTitle();
	}

	// Get page URL
	public String getVendorsPageUrl() {
		return d.getCurrentUrl();
	}

	public String getWebsiteValue() {
		return websiteValue.getText();
	}

	public void clickEditVendor() {
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(editVendorButton)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));

		Reporter.log("Edit Vendor button clicked", true);
	}

	

	public String getVendorPhone() {

		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));

		WebElement phone = d.findElement(By.id("phone"));

		String phoneValue = phone.getAttribute("value");

		Reporter.log("Phone field value: [" + phoneValue + "]", true);

		return phoneValue;
	}



	







	



}
