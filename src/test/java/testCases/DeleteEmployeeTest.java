package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.EmployeeListPage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import testBase.DataStore;

public class DeleteEmployeeTest extends BaseClass {
	LoginPage lpage;
	DashboardPage dbpage;
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
			dbpage = new DashboardPage(getDriver());
			dbpage.clickPimMenu();

			// PIMPage
			log.info("Successfully landed into Employee List page");

			// EmployeeListPage
			emplistpage = new EmployeeListPage(getDriver());
			// String employeeId = (String) jsonObj.get("deleteemployeeid");
			String storedEmployeeId = DataStore.getInstance().get("storedEmployeeID");
			emplistpage.enterEmployeeId(storedEmployeeId);
			log.info("EmployeeId is entered in the employeeId field");
			emplistpage.clickSearchBtn();
			log.info("Searched Employee entry is not found in the table");
			emplistpage.deleteEmployee(storedEmployeeId);
			emplistpage.deleteEmployeeAction((String) jsonObj.get("actionname"));
			String expectedMsg = (String) jsonObj.get("deletedsuccessmessage");
			Assert.assertEquals(emplistpage.verifySuccesfullyDeletedToastMessage(expectedMsg), expectedMsg);
			log.info("Success Message is matched");

			// Logout
			loginlogout.logout();
		}

		catch (Exception e) {
			log.error("Test Failed");
			Assert.fail();
		}

	}
}
