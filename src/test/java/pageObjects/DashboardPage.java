package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {

	public DashboardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickUserProfileDropdown() {
		btn_userprofile.click();
	}

	public void clickLogoutBtn() {
		btn_logout.click();
	}

	public void clickPimMenu() {
		btn_pim_menu.click();
	}

	@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
	WebElement btn_userprofile;
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement btn_logout;
	@FindBy(xpath = "//span[normalize-space()='PIM']")
	WebElement btn_pim_menu;

}
