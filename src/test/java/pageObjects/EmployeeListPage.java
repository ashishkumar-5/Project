package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.Waits;

public class EmployeeListPage extends BasePage {
	Waits sync_Wait = new Waits();

	public EmployeeListPage(WebDriver driver) {
		super(driver);
	}

	public void enterEmployeeId(String employeeIdTxt) {
		driver.findElement(txt_employee_id).sendKeys(employeeIdTxt);

	}

	public void clickSearchBtn() {
		driver.findElement(search_btn).click();

	}

	public Boolean verifyEmployeeId(String employeeId) throws InterruptedException {
		Boolean isEmployeeDisplayed = false;
		List<String> employeeIds = driver.findElements(employee_ids).stream().map(x -> x.getText()).toList();
		if (employeeIds.size() > 0) {
			sync_Wait.explicitWait(driver, "xpath", "//div[contains(text(),'" + employeeId + "')]",
					"visibilityOfElementLocated", null);
			if (employeeIds.contains(employeeId)) {
				isEmployeeDisplayed = true;
			} else {
				isEmployeeDisplayed = false;
			}
		} else {
			isEmployeeDisplayed = false;
		}
		return isEmployeeDisplayed;
	}

	public String verifyNoRecordsFoundText(String expectedTxt) {
		sync_Wait.explicitWait(driver, "xpath",
				"//*[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']",
				"textToBePresentInElementLocated", expectedTxt);
		return driver.findElement(No_Records_Found_txt).getText();

	}

	public String verifySuccesfullyDeletedToastMessage(String expectedMsg) {
		String actualMsg = driver.findElement(Successful_toast_message).getText();
		sync_Wait.explicitWait(driver, "xpath", "//span[normalize-space()='No Records Found']",
				"textToBePresentInElementLocated", expectedMsg);
		return actualMsg;

	}

	public void deleteEmployee(String employeeId) {
		WebElement a = driver.findElement(By.xpath("//div[contains(text(),'" + employeeId
				+ "')]/ancestor::div[@class='oxd-table-cell oxd-padding-cell']/following-sibling::div[7]//div//button[2]//i"));
		a.click();
	}

	public void deleteEmployeeAction(String actionName) {
		switch (actionName.toLowerCase()) {
		case "yes,delete":
			driver.findElement(yes_delete_btn).click();
			break;
		case "no,cancel":
			driver.findElement(no_cancel_btn).click();
			break;
		default:
			System.out.println("Given" + actionName + " is not found");
			break;
		}
	}

	By txt_employee_id = By.xpath(
			"//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']");

	By search_btn = By.xpath("//button[normalize-space()='Search']");

	By employee_ids = By.xpath("//div[@class='oxd-table-card']//div//div[2]//div");

	By delete_employee_btns = By.xpath("//div[@class='oxd-table-card']//div//div[9]//div//button[2]");

	By yes_delete_btn = By.xpath("//button[normalize-space()='Yes, Delete']");

	By no_cancel_btn = By.xpath("//button[normalize-space()='No, Cancel']");

	By Successful_toast_message = By
			.xpath("//*[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");

	By No_Records_Found_txt = By.xpath("//span[normalize-space()='No Records Found']");

}
