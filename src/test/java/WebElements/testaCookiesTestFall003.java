package WebElements;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class testaCookiesTestFall003 {
	public static WebDriver driver;
	public static String baseURL;
	static public BRLeksakerr br;
	static public JavascriptExecutor jse;
	private static final Logger log = LogManager.getLogger(testaCookiesTestFall003.class.getName());
	public static WebDriverWait wait;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("before gecko");
		System.setProperty("webdriver.chrome.driver", "//Users/Bismillah//Downloads//Webdrivers//Chrome//chromedriver");
		
		log.info("after gecko");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		br = new BRLeksakerr(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		baseURL = "http://www.br.se";
		jse = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 10);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Thread.sleep(4000);
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws InterruptedException {

		log.info("Navigating to Https://www.br.se");
		driver.get(baseURL);

		log.info("clicking on \"Kategori\" List");
		br.kategorier.click();

		log.info("Clicking on subList \"Sport&AktivLek\" in Kategori list");
		br.sportOchAktivLek.click();

		log.info("Scrolling 400pixels verticaly  with javascript ");
		jse.executeScript("window.scrollBy(0,400)", "");

		log.info("clicking on first produkt visible in produkt list of the page");
		br.guldMedaljer.click();

		log.info("Put item in basket and wait 5sek for sync");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[8]/div[2]/div/div/div[2]/div[3]/div/ul/li[1]/img")));
		br.putInBasket.click();
		
		wait.until(ExpectedConditions.textToBe(By.xpath("/html/body/header/div/div/div[2]/div[4]/a/span/span"), "(1)"));
		log.info("waiting");
		

		log.info("navigte to basket");
		br.varukorg.click();

		log.info("put away value of bascet");
		String varor = driver.findElement(By.xpath("//html/body/div[8]/div[2]/div/div[1]/header/h1")).getText();

		log.info("Verify basket not emppty");
		Assert.assertEquals("Message if assert fails", "Varukorg (1 Varor)", varor);

		log.info("open new window with javascript");
		jse.executeScript("window.open()");

		log.info("create and arraylist of tabs");
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());

		log.info("change focus to new window");
		driver.switchTo().window(tabs2.get(1));

		log.info("navigate to basket and verify basket not empty and assure cookies enabled and working");
		driver.get("https://www.br.se");
		br.varukorg.click();
		Assert.assertEquals("Message if assert fails", "Varukorg (1 Varor)", varor);

	}
}
