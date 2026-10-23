
package Object_repo;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PurchaseOrderPage {

	WebDriver d;
	WebDriverWait wait;

	public PurchaseOrderPage(WebDriver d) {
		this.d = d;
		wait = new WebDriverWait(d, Duration.ofSeconds(10));
		PageFactory.initElements(d, this);
	}

	@FindBy(xpath = "//img[@title='Create Purchase Order...']")
	private WebElement createPurchaseOrderButton;

	@FindBy(name = "subject")
	private WebElement subjectTextField;

	@FindBy(name = "vendor_name")
	private WebElement vendorNameTextField;

	@FindBy(xpath = "//input[@name='vendor_name']/following-sibling::img[@title='Select']")
	private WebElement selectVendorButton;

	@FindBy(name = "bill_street")
	private WebElement billingAddressTextField;

	@FindBy(name = "ship_street")
	private WebElement shippingAddressTextField;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	@FindBy(xpath = "//td[contains(@onmouseover,'fnvshobjMore')]")
	private WebElement moreMenu;

	@FindBy(xpath = "//a[@name='Purchase Order' and contains(@href,'module=PurchaseOrder')]")
	private WebElement purchaseOrderLink;
	

@FindBy(xpath = "//input[@name='productName']")
private WebElement itemNameTextField;

@FindBy(xpath = "//input[@name='qty1']")
private WebElement quantityTextField;


@FindBy(xpath = "//input[@value='Select Products']")
private WebElement selectProductsButton;

@FindBy(id = "searchIcon1")
private WebElement selectProductButton;

@FindBy(xpath = "//input[@name='selected_id' and @value='113405']")
private WebElement motorDriverCheckbox;

@FindBy(name = "search_text")
private WebElement searchTextField;

@FindBy(id = "bas_searchfield")
private WebElement searchDropdown;

@FindBy(name = "submit")
private WebElement searchButton;




public void searchPurchaseOrder(String poNumber) {
    searchTextField.clear();
    searchTextField.sendKeys(poNumber);
}

public void selectPurchaseOrderNo() {
    Select s = new Select(searchDropdown);
    s.selectByVisibleText("PurchaseOrder No");
}

public void clickSearch() {
    searchButton.click();
}

public void clickEditForPurchaseOrder(String poNumber) {
    WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

    WebElement editLink = wait.until(
        ExpectedConditions.elementToBeClickable(
            By.xpath("//tr[starts-with(@id,'row_')]" +
                     "[.//td[normalize-space()='" + poNumber + "']]" +
                     "//a[contains(@href,'action=EditView')]")
        )
    );

    editLink.click();
}

public void updateSubject(String subject) {
    subjectTextField.clear();
    subjectTextField.sendKeys(subject);
}



public boolean verifyUpdatedSubject(String updatedSubject) {
    try {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        WebElement updatedText = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[starts-with(@id,'row_')]//td[normalize-space()='" 
                + updatedSubject.trim() + "']")
            )
        );

        return updatedText.isDisplayed();

    } catch (Exception e) {
        return false;
    }
    
}


public boolean verifyPurchaseOrderSearch(String poNumber) {
    try {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        WebElement result = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[starts-with(@id,'row_')]//td[normalize-space()='" + poNumber + "']")
            )
        );

        return result.isDisplayed();

    } catch (Exception e) {
        return false;
    }
}





public void selectProduct(String productName) {
    String parentWindow = d.getWindowHandle();

    // Click the product lookup icon
    d.findElement(By.id("searchIcon1")).click();

    WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

    // Wait until the Products popup opens
    wait.until(ExpectedConditions.numberOfWindowsToBe(2));

    Set<String> windows = d.getWindowHandles();

    for (String window : windows) {
        if (!window.equals(parentWindow)) {
            d.switchTo().window(window);
            break;
        }
    }

    // Select product using its visible name
    WebElement product = wait.until(
        ExpectedConditions.elementToBeClickable(
            By.xpath("//a[normalize-space()='" + productName + "']")
        )
    );

    product.click();

    // Return to Purchase Order form
    d.switchTo().window(parentWindow);

    // Wait for the selected product to appear
    wait.until(ExpectedConditions.attributeToBe(
        By.id("productName1"), "value", productName
    ));
}

public void enterQuantity(String quantity) {
    quantityTextField.clear();
    quantityTextField.sendKeys(quantity);
}

public void clickSelectProducts() {
    selectProductsButton.click();
}


	public void openPurchaseOrder() {
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.visibilityOf(moreMenu));

		Actions a = new Actions(d);
		a.moveToElement(moreMenu).perform();

		wait.until(ExpectedConditions.elementToBeClickable(purchaseOrderLink));
		purchaseOrderLink.click();

		wait.until(ExpectedConditions.urlContains("module=PurchaseOrder"));
	}

	public void clickCreatePurchaseOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(createPurchaseOrderButton)).click();
	}

	public void enterSubject(String subject) {
		subjectTextField.clear();
		subjectTextField.sendKeys(subject);
	}
	
	public void selectMotorDriver() {
	    String parentWindow = d.getWindowHandle();

	    // Click the product lookup icon
	    selectProductButton.click();

	    // Wait for the Products popup
	    wait.until(ExpectedConditions.numberOfWindowsToBe(2));

	    for (String window : d.getWindowHandles()) {
	        if (!window.equals(parentWindow)) {
	            d.switchTo().window(window);
	            break;
	        }
	    }

	    // Select Motor driver
	    wait.until(ExpectedConditions.elementToBeClickable(
	            motorDriverCheckbox)).click();

	    // Confirm product selection
	    selectProductsButton.click();

	    // Return to Purchase Order form
	    d.switchTo().window(parentWindow);
	}


public void selectVendor(String vendorName) {
    String parentWindow = d.getWindowHandle();

    wait.until(ExpectedConditions.elementToBeClickable(selectVendorButton)).click();

    wait.until(ExpectedConditions.numberOfWindowsToBe(2));

    Set<String> windows = d.getWindowHandles();

    for (String window : windows) {
        if (!window.equals(parentWindow)) {
            d.switchTo().window(window);
            break;
        }
    }

    WebElement vendor = wait.until(
        ExpectedConditions.elementToBeClickable(
            By.xpath("//a[normalize-space(.)='" + vendorName.trim() + "']")
        )
    );

    vendor.click();

    d.switchTo().window(parentWindow);

    wait.until(ExpectedConditions.visibilityOf(vendorNameTextField));
}

	public void enterBillingAddress(String billingAddress) {
		billingAddressTextField.clear();
		billingAddressTextField.sendKeys(billingAddress);
	}

	public void enterShippingAddress(String shippingAddress) {
		shippingAddressTextField.clear();
		shippingAddressTextField.sendKeys(shippingAddress);
	}
	
	
	public void clickSave() {
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
	}
}
