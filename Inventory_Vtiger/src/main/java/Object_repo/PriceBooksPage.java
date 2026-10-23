package Object_repo;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

public class PriceBooksPage {

	WebDriver d;

	public PriceBooksPage(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d, this);
	}

	// Locators

	@FindBy(xpath = "//td[contains(@onmouseover,'fnvshobjMore')]")
	private WebElement moreMenu;

	@FindBy(xpath = "//a[@id='more' and @name='Price Books']")
	private WebElement priceBooksLink;

	@FindBy(name = "bookname")
	private WebElement priceBookNameTextField;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	@FindBy(xpath = "//input[@title='Cancel [Alt+X]']")
	private WebElement cancelButton;

	@FindBy(name = "currency_id")
	private WebElement currencyDropdown;

	@FindBy(name = "active")
	private WebElement activeCheckbox;

	@FindBy(xpath = "//input[@title='Select Products']")
	private WebElement selectProductsButton;

	@FindBy(name = "description")
	private WebElement descriptionTextArea;

	@FindBy(xpath = "//td[normalize-space()='akash']/ancestor::tr[1]//input[@name='selected_id']")
	private WebElement productCheckbox;

	@FindBy(xpath = "//img[@title='Create Price Book...']")
	private WebElement createPriceBookButton;

	@FindBy(xpath = "//input[@value='Add To Price Books']")
	private WebElement addToPriceBooksButton;

	@FindBy(xpath = "//td[normalize-space()='More Information']")
	private WebElement moreInformationTab;

	@FindBy(id = "show_PriceBooks_Products")
	private WebElement productsExpandButton;

	@FindBy(xpath = "//span[normalize-space()='PB_System_Test']")
	private WebElement savedPriceBookName;

	@FindBy(xpath = "//a[contains(@href,'module=PriceBooks') and contains(@href,'action=EditView')]")
	private WebElement editPriceBookLink;
	
	@FindBy(name = "basicSearch")
	private WebElement searchForm;
	
	@FindBy(name = "search_text")
	private WebElement searchTextField;

	@FindBy(xpath = "//input[@name='submit' and @value=' Search Now ']")
	private WebElement searchNowButton;
	
	@FindBy(name = "search_field")
	private WebElement searchFieldDropdown;

	// Methods

	public void openPriceBooks() {
		Actions actions = new Actions(d);
		actions.moveToElement(moreMenu).perform();

		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(priceBooksLink));

		priceBooksLink.click();

		Assert.assertTrue(d.getCurrentUrl().contains("module=PriceBooks"), "Price Books page is not displayed");

		Reporter.log("Price Books page displayed successfully", true);
	}

	public void enterPriceBookName(String name) {
		priceBookNameTextField.clear();
		priceBookNameTextField.sendKeys(name);
	}

	public void clickSave() {
		saveButton.click();
		Reporter.log("Save button clicked", true);
	}

	public void clickCancel() {
		cancelButton.click();
		Reporter.log("Cancel button clicked", true);
	}

	public void uncheckActive() {
		if (activeCheckbox.isSelected()) {
			activeCheckbox.click();
		}
	}

	public void checkActive() {
		if (!activeCheckbox.isSelected()) {
			activeCheckbox.click();
		}
	}

	public void enterDescription(String description) {
		descriptionTextArea.sendKeys(description);
	}

	public void clickCreatePriceBook() {
		createPriceBookButton.click();
		Reporter.log("Create Price Book button clicked", true);
	}

	public String getPriceBooksPageTitle() {
		return d.getTitle();
	}

	public String getPriceBooksPageUrl() {
		return d.getCurrentUrl();
	}

	public void verifyPriceBookForm() {
		Assert.assertTrue(d.getPageSource().contains("PriceBook No"), "Create Price Book form is not displayed");
		Reporter.log("Create Price Book form displayed successfully", true);
	}

	public void clickSelectProducts() {
		selectProductsButton.click();
		Reporter.log("Select Products button clicked", true);
	}

	public void selectProduct() {
		productCheckbox.click();
		Reporter.log("Product selected: akash", true);

	}

	public void clickAddToPriceBooks() {
		addToPriceBooksButton.click();
		Reporter.log("Product added to Price Book", true);
	}

	public void clickMoreInformation() throws InterruptedException {
		moreInformationTab.click();
		Thread.sleep(2000);
		Reporter.log("More Information tab clicked", true);
	}

	public void clickProductsExpandButton() {
		productsExpandButton.click();
		Reporter.log("Products section expanded", true);
	}

	public void verifyPriceBookCreated(String name) {
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

		WebElement savedPriceBookName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("dtlview_Price Book Name")));

		Assert.assertEquals(savedPriceBookName.getText().trim(), name, "Price Book name does not match");

		Reporter.log("Price Book created successfully: " + name, true);
	}

	public void verifyPriceBooksPage() {
		Assert.assertTrue(d.getCurrentUrl().contains("module=PriceBooks"), "Price Books page is not displayed");

		Reporter.log("Price Books page verified successfully", true);
	}

	public void clickEditPriceBook() {
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(editPriceBookLink));
		editPriceBookLink.click();

		Assert.assertTrue(d.getCurrentUrl().contains("action=EditView"), "Edit Price Book form is not displayed");

		Reporter.log("Edit Price Book form opened successfully", true);
	}

	public void updatePriceBookName(String name) {
		priceBookNameTextField.clear();
		priceBookNameTextField.sendKeys(name);

		Reporter.log("Price Book name updated to: " + name, true);
	}
	

public void editPriceBookByName(String oldName) {
    WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

    By editLink = By.xpath(
        "//a[normalize-space()='edit' and " +
        "ancestor::tr[.//a[normalize-space()='" + oldName + "']]]"
    );

    wait.until(ExpectedConditions.elementToBeClickable(editLink)).click();

    wait.until(ExpectedConditions.urlContains("action=EditView"));

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("bookname")));

    Reporter.log("Edit Price Book form opened successfully", true);
}


public void searchPriceBook(String name) {
    openPriceBooks();

    WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.visibilityOf(searchTextField));

    Select sel = new Select(searchFieldDropdown);
    sel.selectByVisibleText("Price Book Name");

    searchTextField.clear();
    searchTextField.sendKeys(name);

    searchNowButton.click();

    wait.until(ExpectedConditions.visibilityOf(searchTextField));

    Reporter.log("Price Book searched by name: " + name, true);
}

	public void selectCurrency(String currency) {
		Select sel = new Select(currencyDropdown);
		sel.selectByVisibleText(currency.trim());
		Reporter.log("Currency selected: " + currency.trim(), true);
	}

}
