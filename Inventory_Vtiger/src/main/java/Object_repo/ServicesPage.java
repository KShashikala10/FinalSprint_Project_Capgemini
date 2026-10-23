
package Object_repo;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ServicesPage {

    WebDriver d;

    public ServicesPage(WebDriver d) {
        this.d = d;
        PageFactory.initElements(d, this);
    }

    // Edit Service locators
    @FindBy(name = "Edit")
    private WebElement editButton;

    @FindBy(name = "description")
    private WebElement descriptionTextField;

    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private WebElement saveButton;

    @FindBy(xpath = "//a[@title='Services' and normalize-space()='Software Testing']")
    private WebElement softwareTestingLink;

    @FindBy(id = "mouseArea_Description")
    private WebElement displayedDescription;

    @FindBy(id = "dtlview_Description")
    private WebElement descriptionText;

    // Navigation locators
    @FindBy(xpath = "//td[contains(@onmouseover,'fnvshobjMore')]")
    private WebElement moreMenu;

    @FindBy(xpath = "//a[@name='Services']")
    private WebElement servicesLink;

    // Search Service locators
    @FindBy(name = "search_text")
    private WebElement searchTextField;

    @FindBy(id = "bas_searchfield")
    private WebElement searchFieldDropdown;

    @FindBy(name = "submit")
    private WebElement searchButton;

    // Create Service locators
    @FindBy(xpath = "//img[contains(@src,'themes/softed/images/btnL3Add.gif')]")
    private WebElement createServiceButton;

    @FindBy(name = "servicename")
    private WebElement serviceNameField;

    @FindBy(name = "service_usageunit")
    private WebElement usageUnitDropdown;

    @FindBy(id = "qty_per_unit")
    private WebElement noOfUnitsField;

    @FindBy(name = "servicecategory")
    private WebElement categoryField;

    @FindBy(id = "unit_price")
    private WebElement priceField;

    @FindBy(name = "description")
    private WebElement serviceDescriptionField;

    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private WebElement createSaveButton;

    // Enter Service Name
    public void enterServiceName(String serviceName) {
        serviceNameField.sendKeys(serviceName);
    }

    // Select Usage Unit
    public void selectUsageUnit(String unit) {
        Select s = new Select(usageUnitDropdown);
        s.selectByVisibleText(unit);
    }

    // Enter Number of Units
    public void enterNoOfUnits(String units) {
        noOfUnitsField.sendKeys(units);
    }

    // Select Service Category
    public void selectCategory(String categoryName) {
        Select s = new Select(categoryField);
        s.selectByVisibleText(categoryName);
    }

    // Enter Price
    public void enterPrice(String price) {
        priceField.clear();
        priceField.sendKeys(price);
    }

    // Enter Service Description
    public void enterServiceDescription(String description) {
        serviceDescriptionField.sendKeys(description);
    }

    // Save Created Service
    public void saveCreatedService() {
        createSaveButton.click();
    }

    // Get Saved Description
    public String getSavedDescription() {
        return descriptionText.getText().trim();
    }

    // Get Displayed Description
    public String getDisplayedDescription() {
        return displayedDescription.getText().trim();
    }

    // Click Edit
    public void clickEdit() {
        editButton.click();

        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.name("description")
        ));
    }

    // Update Description
    public void updateDescription(String description) {
        descriptionTextField.clear();
        descriptionTextField.sendKeys(description);
    }

    // Click Save after Editing
    public void clickSave() {
        saveButton.click();
    }

    // Get Description from Text Field
    public String getDescription() {
        return descriptionTextField.getAttribute("value");
    }

    // Open Services Module
    public void openServices() {
        Actions a = new Actions(d);
        a.moveToElement(moreMenu).perform();
        servicesLink.click();
    }

    // Click Create Service
    public void clickCreateService() {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(createServiceButton));
        createServiceButton.click();
    }

    // Search Service by Number




    // Open Service by Number
    public void openServiceByNumber(String serviceNo) {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(15));

        WebElement record = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//tr[td[normalize-space()='" + serviceNo + "']]//td[3]//a")
            )
        );

        record.click();
    }


    // Open Software Testing Service
    public void openSoftwareTesting() {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(softwareTestingLink));
        softwareTestingLink.click();
    }
    
    

public void openServiceByName(String serviceName) {
    WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(15));

    WebElement record = wait.until(
        ExpectedConditions.elementToBeClickable(
            By.xpath("//a[normalize-space(.)='" + serviceName + "']")
        )
    );

    record.click();
}




public boolean verifyServiceRecord(String serviceNo) {
    try {
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(15));

        WebElement record = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[td[normalize-space()='" + serviceNo + "']]")
            )
        );

        return record.isDisplayed();

    } catch (TimeoutException e) {
        return false;
    }
}


public void searchServiceByNumber(String serviceNo) {

    Select s = new Select(searchFieldDropdown);
    s.selectByVisibleText("Service No");

    searchTextField.clear();
    searchTextField.sendKeys(serviceNo);

    searchButton.click();
}


}


