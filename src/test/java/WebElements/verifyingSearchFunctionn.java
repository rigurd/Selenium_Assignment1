package WebElements;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class verifyingSearchFunctionn {
	public static WebDriver driver;
	public static String baseURL;
	private static final Logger log = LogManager.getLogger(verifyingSearchFunctionn.class.getName());
	public static BRLeksakerr br;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver", "//Users/Bismillah//Downloads//Webdrivers//Firefox//geckodriver");
		driver = new FirefoxDriver();
		br = new BRLeksakerr(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		baseURL = "https://www.br.se";
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFall011() {
		
		log.error("sdfdsfdsfdsfnasdkjnask");
		
		log.info("Navigating to URL and");
		driver.get(baseURL);
		
		log.info("typing \"Lego\" in search field");
		br.sendInputToSearchField("Lego");
		
		log.info("Clicking on ok");
		br.clickSearchButton();
		
		log.info("getting search result");
		br.getSearchResult();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Thread.sleep(3000);
		driver.quit();
	}
}
