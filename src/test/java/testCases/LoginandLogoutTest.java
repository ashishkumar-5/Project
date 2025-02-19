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
			LoginPage lpage = new LoginPage(getDriver());
			lpage.setUserName((String) jsonObj.get("username"));
			lpage.setPassword((String) jsonObj.get("password"));
			lpage.clickLoginBtn();
			log.info("Logged in Successfully");
			if (getDriver().getTitle().equals((String) jsonObj.get("pagetitle"))) {
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
			DashboardPage dbpage = new DashboardPage(getDriver());
			dbpage.clickUserProfileDropdown();
			dbpage.clickLogoutBtn();
			log.info("Logged out Successfully");
			Assert.assertEquals(getDriver().getTitle(), (String) jsonObj.get("pagetitle"));
		} catch (Exception e) {
			Assert.fail();
			log.error("Test Failed");
		}

	}
}
