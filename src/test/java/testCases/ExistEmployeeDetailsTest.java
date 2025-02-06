package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.EmployeeListPage;
import pageObjects.LoginPage;
import pageObjects.PIMPage;
import testBase.BaseClass;

@Listeners(utilities.Listeners.class)
public class ExistEmployeeDetailsTest extends BaseClass {

	LoginPage lpage;
	DashboardPage dbpage;
	PIMPage pimpage;
	EmployeeListPage emplistpage;
	LoginandLogoutTest loginlogout;

//	@Test(retryAnalyzer = utilities.Retry.class)
	@Test
	void existEmployeeDetails() {
		try {
			// Login
			loginlogout = new LoginandLogoutTest();
			loginlogout.login();
			// DashboardPage
			dbpage = new DashboardPage(driver);
			dbpage.clickPimMenu();
			// PIMPage
			pimpage = new PIMPage(driver);
			log.info("Successfully landed into Employee List page");
			// EmployeeListPage
			emplistpage = new EmployeeListPage(driver);
			String employeeId = (String) jsonObj.get("existingemployeeid");
			emplistpage.enterEmployeeId(employeeId);
			log.info("EmployeeId is entered in the employeeId field");
			emplistpage.clickSearchBtn();
			if (emplistpage.verifyEmployeeId(employeeId)) {
				Assert.assertTrue(true);
				log.info("Searched Employee entry is found in the table");
			} else {
				Assert.assertFalse(false);
				log.info("No employees in the list");
			}
			// Logout
			loginlogout.logout();
		}

		catch (Exception e) {
			log.error("Test Failed");
			Assert.fail();
		}
	}
}
