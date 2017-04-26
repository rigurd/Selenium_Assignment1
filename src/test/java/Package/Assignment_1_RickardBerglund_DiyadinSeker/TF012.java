package Package.Assignment_1_RickardBerglund_DiyadinSeker;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import WebElements.BRLeksakerr;

public class TF012 {
public static	WebDriver driver;
public static	WebDriverWait wait;
public static	String baseURL = "https://www.br.se";
static public BRLeksakerr br;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	System.setProperty("webdriver.gecko.driver", "//Users/Bismillah//Downloads//Webdrivers//Firefox//geckodriver");
	driver = new FirefoxDriver();
	wait = new WebDriverWait(driver,5);
	driver.manage().window().maximize();
	br = new BRLeksakerr(driver);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}


	@Before
	public void setUp() throws Exception {
	}


	
	
	
	@Test
	public void testFall012() throws InterruptedException {
	driver.get(baseURL);
	br.clickKategorier();
	br.clickRollspel();
	Thread.sleep(3000);
	br.assertCurrentURL("https://www.br.se/vaara-kategorier/rollspel");
	br.getTitle();
	String content = driver.findElement(By.className("header-content-box")).getText();
	System.out.println("Content of page text: " + content);
	System.out.println("Content contains Rollspel: " + content.contains("Rollspel"));
	
	}
	
	@Test
	public void testFall013() throws InterruptedException{
		driver.get(baseURL);
		Thread.sleep(3000);
		br.clickLogIn();
		br.alreadyMemberUsername("Rillep22@hotmail.com");
		br.alreadyMemberPassword("nackademin");
		br.clickLoginButton();
		Thread.sleep(2000);
		br.verifyFodelsedagspresent();
		br.assertCurrentURL("https://www.br.se/my-account/profile");
		Thread.sleep(6000);
		br.logOutFromAccount();
		Thread.sleep(4000);
	}
	
	@Test
	public void testFall011(){
		driver.get(baseURL);
		br.sendInputToSearchField("Lego");
		br.clickSearchButton();
		br.getSearchResult();
	}
	
	
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Thread.sleep(3000);
		driver.quit();
		
	}
}
