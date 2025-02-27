package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.EmployeeListPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class NotExistEmployeeDetailsTest extends BaseClass {
	LoginPage lpage;
	DashboardPage dbpage;
	EmployeeListPage emplistpage;
	LoginandLogoutTest loginlogout;

//	@Test(retryAnalyzer = utilities.Retry.class)
	@Test
	void notExistEmployeeDetails() {
		try {
			// Login
			loginlogout = new LoginandLogoutTest();
			loginlogout.login();

			// DashboardPage
			dbpage = new DashboardPage(getDriver());
			dbpage.clickPimMenu();

			// PIMPage
			log.info("Successfully landed into Employee List page");

			// EmployeeListPage
			emplistpage = new EmployeeListPage(getDriver());
			String employeeId = (String) jsonObj.get("notexistemployeeid");
			emplistpage.enterEmployeeId(employeeId);
			log.info("EmployeeId is entered in the employeeId field");
			emplistpage.clickSearchBtn();
			String noRecordsTxt = (String) jsonObj.get("norecordstext");
			Assert.assertEquals(emplistpage.verifyNoRecordsFoundText(noRecordsTxt), noRecordsTxt);
			log.info("Searched Employee is not found in the table");

			// Logout
			loginlogout.logout();
		}

		catch (Exception e) {
			log.error("Test Failed");
			Assert.fail();
		}

	}
}
