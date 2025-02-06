package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.EmployeeListPage;
import pageObjects.LoginPage;
import pageObjects.PIMPage;
import testBase.BaseClass;

public class DeleteEmployeeTest extends BaseClass {
	LoginPage lpage;
	DashboardPage dbpage;
	PIMPage pimpage;
	EmployeeListPage emplistpage;
	LoginandLogoutTest loginlogout;

//	@Test(retryAnalyzer = utilities.Retry.class)
	@Test
	void deleteEmployee() {
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
			String employeeId = (String) jsonObj.get("deleteemployeeid");
			emplistpage.enterEmployeeId(employeeId);
			log.info("EmployeeId is entered in the employeeId field");
			emplistpage.clickSearchBtn();
			log.info("Searched Employee entry is not found in the table");
			emplistpage.deleteEmployee(employeeId);
			emplistpage.deleteEmployeeAction((String) jsonObj.get("actionname"));
			// Logout
			loginlogout.logout();
		}

		catch (Exception e) {
			log.error("Test Failed");
			Assert.fail();
		}

	}
}
