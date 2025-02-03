package testCases;

import org.testng.Assert;

import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class LoginandLogoutTest extends BaseClass {

	public void login() {
		try {
			log.info("Landed into login page");
			// LoginPage
			LoginPage lpage = new LoginPage(driver);
			lpage.setUserName(prop.getProperty("username"));
			lpage.setPassword(prop.getProperty("password"));
			lpage.clickLoginBtn();
			log.info("Logged in Successfully");
			if (driver.getTitle().equals(prop.getProperty("pagetitle"))) {
				Assert.assertTrue(true);
				log.info("page title is matched");
			} else {
				Assert.assertFalse(false);
				log.error("page title is not matched");
			}
		} catch (Exception e) {
			Assert.fail();
			log.error("Test Failed");
		}

	}

	public void logout() {
		try {
			// DashboardPage
			DashboardPage dbpage = new DashboardPage(driver);
			dbpage.clickUserProfileDropdown();
			dbpage.clickLogoutBtn();
			log.info("Logged out Successfully");
			Assert.assertEquals(driver.getTitle(), prop.getProperty("pagetitle"));
		} catch (Exception e) {
			Assert.fail();
			log.error("Test Failed");
		}

	}

}
