package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.BaseClass;

public class Waits extends BaseClass {
	public static WebDriverWait wait;

	public void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void explicitWait(WebDriver driver, String locator, String path, String condition, String text) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		switch (condition.toLowerCase()) {
		case "visibilityofelementlocated":
			if (locator.toLowerCase() == "xpath") {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
			}
			break;
		case "texttobepresentinelementlocated":
			if (locator.toLowerCase() == "xpath") {
				wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(path), text));
			}
			break;

		case "elementtobeclickable":
			if (locator.toLowerCase() == "xpath") {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
			}
			break;
		default:
			log.info(condition + " is not found");
			break;
		}

	}

}
