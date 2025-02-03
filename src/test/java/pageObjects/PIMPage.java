package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage extends BasePage {

	public PIMPage(WebDriver driver) {
		super(driver);
	}

	public void clickEmployeeListTab() {
		driver.findElement(employee_list_tab).click();
	}

	public void clickAddEmployeeTab() {
		driver.findElement(add_employee_tab).click();
	}

	By add_employee_tab = By.xpath("//a[normalize-space()='Add Employee']");
	By employee_list_tab = By.xpath("//a[normalize-space()='Employee List']");

}
