package testBase;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;

import utilities.Waits;

public class BaseClass {
	private static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	public static Properties prop;
	public static Logger log;
	public static JSONParser jsonParser;
	public static JSONObject jsonObj;

	WebDriver driver;

	public void setDriver(WebDriver driver) {
		tdriver.set(driver);
	}

	public WebDriver getDriver() {
		return tdriver.get();
	}

	@Parameters({ "browsername" })
	@BeforeMethod
	public void setup(String browserName) throws IOException, InterruptedException, ParseException {
		Waits sync_Wait = new Waits();
		log = LogManager.getLogger(this.getClass());

		String genericPath = System.getProperty("user.dir");
		prop = new Properties();
		FileInputStream file = new FileInputStream(genericPath + "\\src\\test\\resources\\data.properties");
		prop.load(file);

		jsonParser = new JSONParser();
		jsonObj = (JSONObject) jsonParser
				.parse(new FileReader(genericPath + "\\src\\test\\resources\\testData\\testdata.json"));

		if (browserName.equals("Chrome")) {
			tdriver.set(new ChromeDriver());
		} else if (browserName.equals("Firefox")) {
			tdriver.set(new FirefoxDriver());
		} else if (browserName.equals("Edge")) {
			tdriver.set(new EdgeDriver());
		} else if (browserName.equals("IE")) {
			tdriver.set(new InternetExplorerDriver());
		} else {
			System.out.println("Invalid browser name");
			return;
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		sync_Wait.implicitWait(getDriver());
		getDriver().get(prop.getProperty("websiteurl"));

	}

	public Media captureScreen(String tName) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) getDriver());
		return MediaEntityBuilder.createScreenCaptureFromBase64String(scrShot.getScreenshotAs(OutputType.BASE64))
				.build();
	}

	public int randomNumber() {
		Random random = new Random();
		int generatedNumber = random.nextInt(10000);
		return generatedNumber;
	}

	@AfterMethod
	public void tearDown() {
		getDriver().quit();
		tdriver.remove();
	}
}
