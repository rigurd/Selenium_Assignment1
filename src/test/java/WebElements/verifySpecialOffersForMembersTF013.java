package WebElements;



import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class verifySpecialOffersForMembersTF013 {
	public static WebDriver driver;
	public static String baseURL = "https://www.br.se";
	public static BRLeksakerr br;
	private static final Logger log = LogManager.getLogger(verifySpecialOffersForMembersTF013.class.getName());
	private static WebDriverWait wait;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	System.setProperty("webdriver.gecko.driver", "//Users/Bismillah//Downloads//Webdrivers//Firefox//geckodriver");
	driver = new FirefoxDriver();
	br = new BRLeksakerr(driver);
	wait = new WebDriverWait(driver,10);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}




	@Test
	public void testFall013() throws InterruptedException{
		log.info("navigate to URL and wait for 3sek");
		
		driver.get(baseURL);
	
		
		log.info("click on log in button");
		br.clickLogIn();

		log.info("Enter valid username and password");
		Thread.sleep(5000);

		driver.findElement(By.xpath("/html/body/div[8]/div[2]/div[2]/div/div[2]/form/div[1]/div/input")).sendKeys("Rillep22@ngt.se");
		Thread.sleep(5000);
		
		
//		br.alreadyMemberUsername("Rillep22@hotmail.com");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_username")));
//		br.alreadyMemberPassword("nackademin");
		
		
		
		
		
		log.info("click log in");
		br.clickLoginButton();
		
		log.info("verify \"Födelsedagspresent\" visible ");
		br.verifyFodelsedagspresent();
		
		log.info("assert current url2");
		br.assertCurrentURL("https://www.br.se/my-account/profile");
		
		log.info("log out from account");
		br.logOutFromAccount();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Thread.sleep(4000);
		driver.quit();
	}

	
}
