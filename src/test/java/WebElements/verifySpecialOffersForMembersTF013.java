package WebElements;



import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class verifySpecialOffersForMembersTF013 {
	public static WebDriver driver;
	public static String baseURL = "https://www.br.se";
	public static BRLeksakerr br;
	private static final Logger log = LogManager.getLogger(verifySpecialOffersForMembersTF013.class.getName());
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	System.setProperty("webdriver.gecko.driver", "//Users/Bismillah//Downloads//Webdrivers//Firefox//geckodriver");
	driver = new FirefoxDriver();
	br = new BRLeksakerr(driver);
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
}




	@Test
	public void testFall013() throws InterruptedException{
		log.info("navigate to URL and wait for 3sek");
		
		driver.get(baseURL);
		Thread.sleep(3000);
		
		log.info("click on log in button");
		br.clickLogIn();
		
		log.info("Enter valid username and password");
		br.alreadyMemberUsername("Rillep22@hotmail.com");
		br.alreadyMemberPassword("nackademin");
		
		log.info("click log in");
		br.clickLoginButton();
		Thread.sleep(2000);
		
		log.info("verify \"Födelsedagspresent\" visible ");
		br.verifyFodelsedagspresent();
		
		log.info("assert current url2");
		br.assertCurrentURL("https://www.br.se/my-account/profile");
		Thread.sleep(6000);
		
		log.info("log out from account");
		br.logOutFromAccount();
		Thread.sleep(4000);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Thread.sleep(4000);
		driver.quit();
	}

	
}
