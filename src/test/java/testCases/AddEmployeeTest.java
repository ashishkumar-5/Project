package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AddEmployeePage;
import pageObjects.DashboardPage;
import pageObjects.EmployeeListPage;
import pageObjects.PIMPage;
import testBase.BaseClass;
import testBase.DataStore;

//@Listeners(utilities.Listeners.class)
public class AddEmployeeTest extends BaseClass {
	DashboardPage dbpage;
	PIMPage pimpage;
	AddEmployeePage addemppage;
	EmployeeListPage emplistpage;
	LoginandLogoutTest loginlogout;

	// @Test(retryAnalyzer = utilities.Retry.class)
	@Test
	void addEmployee() {
		try {
			// Login
			loginlogout = new LoginandLogoutTest();
			loginlogout.login();

			// DashboardPage
			dbpage = new DashboardPage(getDriver());
			dbpage.clickPimMenu();

			// PIMPage
			pimpage = new PIMPage(getDriver());
			pimpage.clickAddEmployeeTab();
			addemppage = new AddEmployeePage(getDriver());
			addemppage.setFirstName((String) jsonObj.get("firstname"));
			addemppage.setMiddleName((String) jsonObj.get("middlename"));
			addemppage.setLastName((String) jsonObj.get("lastname"));
			String employeeId = (String) jsonObj.get("employeeid") + "" + randomNumber();
			if (!((String) jsonObj.get("employeeid")).isEmpty()) {
				addemppage.setEmployeeId(employeeId);
			}
			DataStore.getInstance().store("storedEmployeeID", employeeId, true);
			addemppage.clickSaveBtn();
			log.info("New Employee added Successfully");
			String expectedMsg = (String) jsonObj.get("successmessage");
			Assert.assertEquals(addemppage.verifySuccessToastMessage(expectedMsg), expectedMsg);
			log.info("Success Message is matched");

			// Logout
			loginlogout.logout();

		} catch (Exception e) {
			log.error("Test Failed");
			Assert.fail();
		}

	}

}
