package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import utilities.Waits;

public class BaseClass {
	public static WebDriver driver;
	public static Properties prop;
	public static Logger log;
	public static JSONParser jsonParser;
	public static JSONObject jsonObj;

	@BeforeMethod
	public void setup() throws IOException, InterruptedException, ParseException {
		Waits sync_Wait = new Waits();
		log = LogManager.getLogger(this.getClass());

		String genericPath = System.getProperty("user.dir");
//		prop = new Properties();
//		FileInputStream file = new FileInputStream(genericPath + "\\src\\test\\resources\\data.properties");
//		prop.load(file);
//
//		if (prop.getProperty("browser").equals("Chrome")) {
//			driver = new ChromeDriver();
//		} else if (prop.getProperty("browser").equals("Firefox")) {
//			driver = new FirefoxDriver();
//		} else if (prop.getProperty("browser").equals("Edge")) {
//			driver = new EdgeDriver();
//		} else if (prop.getProperty("browser").equals("IE")) {
//			driver = new InternetExplorerDriver();
//		} else {
//			System.out.println("Invalid browser name");
//		}
//		
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		sync_Wait.implicitWait(driver);
//		driver.get(prop.getProperty("websiteurl"));

		JSONParser jsonParser = new JSONParser();
		jsonObj = (JSONObject) jsonParser.parse(new FileReader(genericPath + "\\src\\test\\resources\\testdata.json"));

		String browserName = (String) jsonObj.get("browser");

		if (browserName.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("Edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equals("IE")) {
			driver = new InternetExplorerDriver();
		} else {
			System.out.println("Invalid browser name");
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		sync_Wait.implicitWait(driver);
		driver.get((String) jsonObj.get("websiteurl"));

	}

	public String captureScreen(String tName) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File sourceFile = scrShot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tName + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

	public int randomNumber() {
		Random random = new Random();
		int generatedNumber = random.nextInt(10000);
		return generatedNumber;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
