package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.AddEmployeePage;
import pageObjects.DashboardPage;
import pageObjects.EmployeeListPage;
import pageObjects.LoginPage;
import pageObjects.PIMPage;
import testBase.BaseClass;
import testBase.DataStore;

@Listeners(utilities.Listeners.class)
public class AddEmployeeTest extends BaseClass {
	LoginPage lpage;
	DashboardPage dbpage;
	PIMPage pimpage;
	AddEmployeePage addemppage;
	EmployeeListPage emplistpage;
	LoginandLogoutTest loginlogout;

	@Test(retryAnalyzer = utilities.Retry.class)
	// @Test
	void addEmployee() {
		try {
			// Login
			loginlogout = new LoginandLogoutTest();
			loginlogout.login();
			// DashboardPage
			dbpage = new DashboardPage(driver);
			dbpage.clickPimMenu();
			// PIMPage
			pimpage = new PIMPage(driver);
			pimpage.clickAddEmployeeTab();
			addemppage = new AddEmployeePage(driver);
			addemppage.setFirstName((String) jsonObj.get("firstname"));
			addemppage.setMiddleName((String) jsonObj.get("middlename"));
			addemppage.setLastName((String) jsonObj.get("lastname"));
			String employeeId = (String) jsonObj.get("employeeid") + "" + randomNumber();
			DataStore.getInstance().store("storedEmployeeID", employeeId, false);
			if (!((String) jsonObj.get("employeeid")).isEmpty()) {
				addemppage.setEmployeeId(employeeId);
			}

			addemppage.clickSaveBtn();
			log.info("New Employee added Successfully");
			String expectedMsg = (String) jsonObj.get("successmessage");
			Assert.assertEquals(addemppage.verifySuccessToastMessage(expectedMsg), expectedMsg);
			log.info("Success Message is matched");

			pimpage.clickEmployeeListTab();
			log.info("Successfully landed into Employee List page");
			// EmployeeListPage
			emplistpage = new EmployeeListPage(driver);
			String storedEmployeeId = DataStore.getInstance().get("storedEmployeeID");
			emplistpage.enterEmployeeId(storedEmployeeId);
			log.info("Created EmployeeId is entered in the employeeId field");
			emplistpage.clickSearchBtn();
			Assert.assertTrue(emplistpage.verifyEmployeeId(employeeId));
			log.info("Created Employee is displayed in the table");
			// Logout
			loginlogout.logout();
		}

		catch (Exception e) {
			log.error("Test Failed");
			Assert.fail();
		}

	}

}
