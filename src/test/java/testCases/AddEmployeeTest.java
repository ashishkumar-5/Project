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
			addemppage.setFirstName(prop.getProperty("firstname"));
			addemppage.setMiddleName(prop.getProperty("middlename"));
			addemppage.setLastName(prop.getProperty("lastname"));
			String employeeId = prop.getProperty("employeeid") + "" + randomNumber();
			if (!prop.getProperty("employeeid").isEmpty()) {
				addemppage.setEmployeeId(employeeId);
			}

			addemppage.clickSaveBtn();
			log.info("New Employee added Successfully");
			String expectedMsg = prop.getProperty("successmessage");
			Assert.assertEquals(addemppage.verifySuccessToastMessage(expectedMsg), expectedMsg);
			log.info("Success Message is matched");

			pimpage.clickEmployeeListTab();
			log.info("Successfully landed into Employee List page");
			// EmployeeListPage
			emplistpage = new EmployeeListPage(driver);
			emplistpage.enterEmployeeId(employeeId);
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
