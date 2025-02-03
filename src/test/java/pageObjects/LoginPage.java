package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void setUserName(String user) {
		driver.findElement(txt_username).sendKeys(user);
	}

	public void setPassword(String password) {
		driver.findElement(txt_password).sendKeys(password);
	}

	public void clickLoginBtn() {
		driver.findElement(btn_login).click();
	}

	By txt_username = By.cssSelector("input[placeholder='Username']");
	By txt_password = By.xpath("//input[@placeholder='Password']");
	By btn_login = By.cssSelector("button[type='submit']");

}
