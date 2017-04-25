package WebElements;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginSecurityTestTF019 {
	public static	WebDriver driver;
	public static String baseURL = "http://www.br.se";
	public static BRLeksakerr br;
	public static WebDriverWait wait;
	public static FileHandler fh;
	private static final Logger log = LogManager.getLogger(loginSecurityTestTF019.class.getName());
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	System.setProperty("webdriver.gecko.driver", "//Users/Bismillah//Downloads//Webdrivers//Firefox//geckodriver");
	driver = new FirefoxDriver();
	
	br = new BRLeksakerr(driver);
	wait = new WebDriverWait(driver, 5);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}	


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void TestFall019() throws InterruptedException{
		log.info("Navigating to URL");
		driver.get(baseURL);
		
		log.info("clicking on Login button on the topright from center");
		br.clickLogIn();
		
		log.info("Typing false user name and password");
		br.alreadyMemberUsername("falseUsername");
		br.alreadyMemberPassword("falsePassword");
		br.clickLoginButton();
		
		Thread.sleep(3000);
		log.info("asserting false account input did not get accses");
		br.assertKontoNotFound();
		
		

	}
	


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Thread.sleep(5000);
		driver.quit();
		
	}
}
