package pageObjects;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import utilities.Waits;

public class AddEmployeePage extends BasePage {
	Waits sync_Wait = new Waits();

	public AddEmployeePage(WebDriver driver) {
		super(driver);
	}

	public void setFirstName(String firstName) {
		driver.findElement(first_name).sendKeys(firstName);
	}

	public void setMiddleName(String middleName) {
		driver.findElement(middle_name).sendKeys(middleName);
	}

	public void setLastName(String lastName) {
		driver.findElement(last_name).sendKeys(lastName);
	}

	public void setEmployeeId(String employeeId) throws IOException, InterruptedException {
		if (!employeeId.isEmpty()) {
			driver.findElement(employee_id).sendKeys(Keys.CONTROL + "a");
			driver.findElement(employee_id).sendKeys(Keys.DELETE);
			driver.findElement(employee_id).sendKeys(employeeId);
		} else {
			System.out.print("employeeId is generated automatically");

		}
		File sourceFile = driver.findElement(By.xpath("//div[@class='oxd-form-row']")).getScreenshotAs(OutputType.FILE);
		File targetFile = new File(System.getProperty("user.dir") + "\\screenshots\\addemployee.png");
		FileUtils.copyFile(sourceFile, targetFile);

		String c = driver.findElement(employee_id).getDomProperty("value");
		System.out.println(c + "!!!!!!!!!!!!");
	}

	public void clickSaveBtn() {
		driver.findElement(btn_save).click();
	}

	public String verifySuccessToastMessage(String expectedMsg) {
		sync_Wait.explicitWait(driver, "xpath",
				"//*[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']",
				"textToBePresentInElementLocated", expectedMsg);
		return driver.findElement(Successful_toast_message).getText();

	}

	By first_name = By.name("firstName");
	By middle_name = By.xpath("//input[@placeholder='Middle Name']");
	By last_name = By.cssSelector("input[placeholder='Last Name']");
	By employee_id = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[2]//input");
	By btn_cancel = By.xpath("//div[@class='oxd-form-actions']//button[1]");
	By btn_save = By.xpath("//div[@class='oxd-form-actions']//button[2]");
	By Successful_toast_message = By
			.xpath("//*[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");

}