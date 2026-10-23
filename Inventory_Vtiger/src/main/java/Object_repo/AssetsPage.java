
package Object_repo;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class AssetsPage
{
    WebDriver d;

    public AssetsPage(WebDriver d)
    {
        this.d = d;
        PageFactory.initElements(d, this);
    }

    @FindBy(xpath = "//a[contains(@href,'action=EditView') and contains(@href,'module=Assets')]")
    private WebElement editAssetLink;

    @FindBy(xpath = "//img[@title='Create Asset...']")
    private WebElement createAssetButton;

    @FindBy(id = "product_display")
    private WebElement productNameField;

    @FindBy(xpath = "//img[@title='Select' and contains(@onclick,'Popup_picker') and contains(@onclick,'field=product')]")
    private WebElement productSelectIcon;

    @FindBy(name = "serialnumber")
    private WebElement serialNumberField;

    @FindBy(name = "assetname")
    private WebElement assetNameField;

    @FindBy(name = "description")
    private WebElement notesField;

    @FindBy(name = "button")
    private WebElement saveButton;

    @FindBy(xpath = "//img[@title='Select' and contains(@onclick,'field=account')]")
    private WebElement customerSelectIcon;

    @FindBy(name = "search_text")
    private WebElement searchTextField;

    @FindBy(id = "bas_searchfield")
    private WebElement searchDropdown;

    @FindBy(name = "submit")
    private WebElement searchButton;
    

@FindBy(xpath = "//td[contains(@onmouseover,'fnvshobjMore')]")
private WebElement moreMenu;

@FindBy(xpath = "//a[@id='more' and @name='Assets']")
private WebElement assetsLink;

public void openAssets()
{
    WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.elementToBeClickable(moreMenu));

    org.openqa.selenium.interactions.Actions a =
            new org.openqa.selenium.interactions.Actions(d);

    a.moveToElement(moreMenu).perform();

    wait.until(ExpectedConditions.elementToBeClickable(assetsLink));
    assetsLink.click();

    wait.until(ExpectedConditions.urlContains("module=Assets"));

    Reporter.log("Assets page opened successfully", true);
}



    public void clickCreateAsset()
    {
        createAssetButton.click();
        Reporter.log("Create Asset button clicked", true);
    }

    public void clickProductSelectIcon()
    {
        productSelectIcon.click();
        Reporter.log("Product lookup clicked", true);
    }

    public void selectProduct(String productName)
    {
        String parentWindow = d.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> windows = d.getWindowHandles();

        for (String window : windows)
        {
            if (!window.equals(parentWindow))
            {
                d.switchTo().window(window);
                break;
            }
        }

        WebElement product = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='" + productName + "']")
            )
        );

        product.click();
        d.switchTo().window(parentWindow);

        wait.until(ExpectedConditions.visibilityOf(productNameField));

        Reporter.log("Product selected: " + productName, true);
    }

    public void enterSerialNumber(String serialNo)
    {
        serialNumberField.sendKeys(serialNo);
        Reporter.log("Serial Number entered: " + serialNo, true);
    }

    public void enterAssetName(String assetName)
    {
        assetNameField.clear();
        assetNameField.sendKeys(assetName);
        Reporter.log("Asset Name entered: " + assetName, true);
    }

    public void enterNotes(String notes)
    {
        notesField.sendKeys(notes);
        Reporter.log("Notes entered", true);
    }

    public void clickCustomerSelectIcon()
    {
        customerSelectIcon.click();
        Reporter.log("Customer lookup clicked", true);
    }

    public void selectCustomer(String customerName)
    {
        String parentWindow = d.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> windows = d.getWindowHandles();

        for (String window : windows)
        {
            if (!window.equals(parentWindow))
            {
                d.switchTo().window(window);
                break;
            }
        }

        WebElement customer = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='" + customerName + "']")
            )
        );

        customer.click();
        d.switchTo().window(parentWindow);

        Reporter.log("Customer selected: " + customerName, true);
    }

    public void clickSave()
    {
        saveButton.click();
        Reporter.log("Asset saved", true);
    }

    public void searchAssetByNo(String assetNo)
    {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(searchTextField));

        searchTextField.clear();
        searchTextField.sendKeys(assetNo);

        Select s = new Select(searchDropdown);
        s.selectByVisibleText("Asset No");

        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();

        Reporter.log("Asset searched using Asset No: " + assetNo, true);
    }

    public void verifyAssetSearchResult(String assetNo)
    {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        WebElement assetRow = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[td[normalize-space()='" + assetNo + "']]")
            )
        );

        Assert.assertTrue(
            assetRow.isDisplayed(),
            "Matching Asset No was not displayed: " + assetNo
        );

        Reporter.log("Matching Asset No displayed: " + assetNo, true);
    }

    public void openExistingAsset(String assetName)
    {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        WebElement assetLink = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@title='Assets' and normalize-space()='" + assetName + "']")
            )
        );

        assetLink.click();

        Reporter.log("Existing asset opened: " + assetName, true);
    }

    public void editAssetName(String assetName)
    {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        WebElement editButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.name("Edit"))
        );

        editButton.click();

        WebElement assetNameTextbox = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("assetname"))
        );

        assetNameTextbox.clear();
        assetNameTextbox.sendKeys(assetName);

        WebElement save = wait.until(
            ExpectedConditions.elementToBeClickable(By.name("button"))
        );

        save.click();

        Reporter.log("Asset Name updated: " + assetName, true);
    }

    public void openAssetForEditing(String assetNo)
    {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        WebElement assetRow = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[td[normalize-space()='" + assetNo + "']]")
            )
        );

        WebElement editLink = assetRow.findElement(
            By.xpath(".//a[contains(@href,'action=EditView') and contains(@href,'module=Assets')]")
        );

        wait.until(ExpectedConditions.elementToBeClickable(editLink)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.name("assetname")
        ));

        Reporter.log("Asset Edit page opened for: " + assetNo, true);
    }

    public void enterTemporaryAssetName(String assetName)
    {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        WebElement nameField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.name("assetname"))
        );

        nameField.clear();
        nameField.sendKeys(assetName);

        Reporter.log("Temporary Asset Name entered: " + assetName, true);
    }

    public void verifyAssetNameUnchanged(String assetNo, String originalName)
    {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        WebElement assetRow = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[td[normalize-space()='" + assetNo + "']]")
            )
        );

        WebElement assetName = assetRow.findElement(
            By.xpath("./td[normalize-space()='" + originalName + "']")
        );

        Assert.assertTrue(
            assetName.isDisplayed(),
            "Original Asset Name was changed after cancellation"
        );

        Reporter.log("Original Asset Name remains unchanged: " + originalName, true);
    }

    public void clickCancel()
    {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        WebElement cancelButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@title='Cancel [Alt+X]']")
            )
        );

        cancelButton.click();

        Reporter.log("Cancel button clicked", true);
    }
}
