package Package.Assignment_1_RickardBerglund_DiyadinSeker;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	public static WebDriver driver;
	public static WebDriverWait wait3s;
	public static String baseURL = "https://www.br.se";
	public static final Logger logger = Logger.getLogger(AppTest.class.getName());
	
	@BeforeClass
	public static void setup() {
		driver = new FirefoxDriver();
		wait3s = new WebDriverWait(driver, 3);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	@Before
	public void before(){
		driver.get(baseURL);
	}
	@Test
	public void TF001() throws InterruptedException{
		// Saving the websites current Title to String
		String homeTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		assertNotNull(homeTitle);
		assertEquals("Leksaker från BR | Hem", homeTitle);
		// Finding WebElement varukorg and saves it to variable then clicks
		WebElement varukorg = driver.findElement(By.xpath(".//*[@id='iconbar']/div[4]/a/div"));
		varukorg.click();
		// Waiting 3 seconds until element is located
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a")));
		// Saving the websites current (and new) Title to String
		String varukorgTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		assertNotNull(varukorgTitle);
		assertEquals("Varukorg | Leksaker från BR", varukorgTitle);
		try{
			if(varukorgTitle.startsWith("Varukorg")){
				logger.info("Title is correct");
			}else{
				logger.info("Title is wrong");
			}
		}catch (Exception e){
			logger.error("Error : " + e);
		}
		logger.info("TF001 Pass");
	}
	@Test
	public void TF002(){
		// Saving the websites current Title to String
		String homeTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		assertNotNull(homeTitle);
		assertEquals("Leksaker från BR | Hem", homeTitle);
		// Finding WebElement varukorg and saves it to variable then clicks
		WebElement varukorg = driver.findElement(By.xpath(".//*[@id='iconbar']/div[4]/a/div"));
		varukorg.click();
		// Waiting 3 seconds until element is located
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a")));
		// Saving the websites current (and new) Title to String
		String varukorgTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		assertNotNull(varukorgTitle);
		assertEquals("Varukorg | Leksaker från BR", varukorgTitle);
		// Fungerar EJ pga man måste lägga en vara i kassan för att komma till checkout
		WebElement gåTillKassan = driver.findElement(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a"));
		gåTillKassan.click();
		logger.error("Kan ej gå till kassan från varukorgen utan att ha något i varukorgen");
		logger.info("TF002 Pass");
		
	}
	@Test
	public void TF004(){
		// Saving the websites current Title to String
		String homeTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		assertNotNull(homeTitle);
		assertEquals("Leksaker från BR | Hem", homeTitle);
		// Fetching webelement kategorier and asserts with another driver find element of the same location
		WebElement kategorier = driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a"));
		assertEquals(kategorier, driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a")));
		kategorier.click();
		// Getting webelement subkategori by xpath and clicks
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='dd_1']/div[2]/div[1]/ul/li[1]/a")));
		WebElement subKategori = driver.findElement(By.xpath(".//*[@id='dd_1']/div[2]/div[1]/ul/li[1]/a"));
		subKategori.click();
		// Getting index1 from the product list and adds to varukorg
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/div[4]/div[2]/div/ul/li[2]/div/a")));
		WebElement index1 = driver.findElement(By.xpath(".//*[@id='content']/div[4]/div[2]/div/ul/li[2]/div/a"));
		index1.click();
		// Forcing a Thread.sleep because BR website is bad and slow, and other waits does not work.
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			logger.error("Error : "+ e );
		}
		// Getting varukorg element and clicks
		WebElement varukorg = driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/div/div[3]/a/div[1]"));
		varukorg.click();
		// Waits for an element inside varukorg
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a")));
		// Asserting that we've arrived to varukorg
		String varukorgTitle = driver.getTitle();
		assertEquals("Varukorg | Leksaker från BR", varukorgTitle);
		// Getting webelement "gå till kassan" and clicks
		WebElement kassan = driver.findElement(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a"));
		kassan.click();
		// Waits for an element inside kassan (Email required to continue)
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.id("guest.email")));
		// Getting two inputfield elements then adds values to these inputfields
		WebElement email = driver.findElement(By.id("guest.email"));
		WebElement emailConfirm = driver.findElement(By.id("guest.emailConfirm"));
		String fakeMail = "abc@hotmail.com";
		email.sendKeys(fakeMail);
		emailConfirm.sendKeys(fakeMail);
		// Getting kassaBtn again after giving guest emails a value
		WebElement kassaBtn = driver.findElement(By.xpath(".//*[@id='toptoyGuestForm']/div[3]/button"));
		kassaBtn.click();
		// Waits for an element inside kassan
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.id("brSe-creditcard")));
		// Getting 3 checkbox elements and clicks one of them
		WebElement creditCardBtn = driver.findElement(By.id("brSe-creditcard"));
		WebElement bankTransferBtn = driver.findElement(By.id("brSe-bank"));
		WebElement fakturaBtn = driver.findElement(By.id("brSe-onlineinvoice"));
		fakturaBtn.click();
		// if statement testing which checkbox is selected
		if (fakturaBtn.isSelected()){
			logger.info("fakturaBtn works");
			assertTrue(fakturaBtn.isSelected());
		}else if(bankTransferBtn.isSelected()){
			logger.info("bankTransferBtn works");
			assertTrue(bankTransferBtn.isSelected());
		}else if(creditCardBtn.isSelected()){
			logger.info("creditCardBtn works");
			assertTrue(creditCardBtn.isSelected());
		}else{
			logger.error("Nothing works");
		}
		logger.info("TF004 Pass");
	}
	@Test
	public void TF005(){
		// Saving the websites current Title to String
		String homeTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		assertNotNull(homeTitle);
		assertEquals("Leksaker från BR | Hem", homeTitle);
		// Fetching webelement kategorier and asserts with another driver find element of the same location
		WebElement kategorier = driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a"));
		assertEquals(kategorier, driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a")));
		kategorier.click();
		// Waiting for an element inside kategorier then getting subKategori element by xpath and clicks
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='dd_1']/div[2]/div[1]/ul/li[1]/a")));
		WebElement subKategori = driver.findElement(By.xpath(".//*[@id='dd_1']/div[2]/div[1]/ul/li[1]/a"));
		subKategori.click();
		// Waiting for an element inside subKategori then getting dropDown by xpath and clicks
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/div[4]/div[1]/div/div/select")));
		WebElement dropDown = driver.findElement(By.xpath(".//*[@id='content']/div[4]/div[1]/div/div/select"));
		dropDown.click();
		// Waiting for an element inside dropDown then getting highestPrice element and clicks
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/div[4]/div[1]/div/div/select/option[2]")));
		WebElement highestPrice = driver.findElement(By.xpath(".//*[@id='content']/div[4]/div[1]/div/div/select/option[6]"));
		highestPrice.click();
		// Forcing a Thread.sleep because BR.se is a slow website and explicit waits doesn't fully work
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			logger.error("Interrupted" + e);
		}
		// Getting top 2 indexes from ul list and parsing from string to int
		WebElement index0price = driver.findElement(By.xpath(".//*[@id='content']/div[4]/div[2]/div/ul/li[1]/div/div/a/div[3]/div/span"));
		String index0 = index0price.getText();
		assertNotNull(index0);
		String[] newIndex0 = index0.split(",");
		int a = Integer.parseInt(newIndex0[0]);
		assertEquals(799, a);
		WebElement index1price = driver.findElement(By.xpath(".//*[@id='content']/div[4]/div[2]/div/ul/li[2]/div/div/a/div[3]/div/span"));
		String index1 = index1price.getText();
		assertNotNull(index1);
		String[] newIndex1 = index1.split(",");
		int b = Integer.parseInt(newIndex1[0]);
		assertEquals(599, b);
		// Comparing indexes
		if (a > b){
			logger.info("Index0 has the highest price");
		}else if(a == b){
			logger.info("Index0 and Index1 has same price");
		}else if(b > a){
			logger.error("This test is not working");
		}
		logger.info("TF005 Pass");
	}
	@Test
	public void TF006(){
		// Saving the websites current Title to String
		String homeTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		assertNotNull(homeTitle);
		assertEquals("Leksaker från BR | Hem", homeTitle);
		// Getting webelement kategorier and asserts with another driver find element of the same location
		WebElement kategorier = driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a"));
		assertEquals(kategorier, driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a")));
		kategorier.click();
		// Getting subKategori by xpath and clicks
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='dd_1']/div[2]/div[1]/ul/li[1]/a")));
		WebElement subKategori = driver.findElement(By.xpath(".//*[@id='dd_1']/div[2]/div[1]/ul/li[1]/a"));
		subKategori.click();
		// Getting index1 from the category list and adds it to varukorg
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/div[4]/div[2]/div/ul/li[2]/div/a")));
		WebElement index1 = driver.findElement(By.xpath(".//*[@id='content']/div[4]/div[2]/div/ul/li[2]/div/a"));
		index1.click();
		// Forcing a thread sleep on 3 seconds, no viable element to waitfor in BR website. Didn't find any other option.
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			logger.error("Error : "+ e );
		}
		// Getting varukorg icon and clicks
		WebElement varukorg = driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/div/div[3]/a/div[1]"));
		varukorg.click();
		// Waiting until an element inside varukorg is visible
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a")));
		// Asserting that we've arrived to varukorg
		String varukorgTitle = driver.getTitle();
		assertEquals("Varukorg | Leksaker från BR", varukorgTitle);
		// Getting kassan element and clicks (gå till kassan)
		WebElement kassan = driver.findElement(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a"));
		kassan.click();
		// Getting input fields and giving them value
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.id("guest.email")));
		WebElement email = driver.findElement(By.id("guest.email"));
		WebElement emailConfirm = driver.findElement(By.id("guest.emailConfirm"));
		String fakeMail = "abc@hotmail.com";
		email.sendKeys(fakeMail);
		emailConfirm.sendKeys(fakeMail);
		// Getting kassa button and clicks
		WebElement kassaBtn = driver.findElement(By.xpath(".//*[@id='toptoyGuestForm']/div[3]/button"));
		kassaBtn.click();
		// Getting delivery option "privat adress"
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='yourInformationForm']/div[4]/div[3]/a")));
		WebElement privateAdress = driver.findElement(By.xpath(".//*[@id='yourInformationForm']/div[4]/div[3]/a"));
		String privateAdress1 = privateAdress.getText();
		String[] newPrivateAdress = privateAdress1.split(",");
		// Asserting that correct element has been found by string
		assertEquals("Till en privat adress (PostNord) (From 59", newPrivateAdress[0]);
		logger.info("TF006 pass");
	}
	@After
	public void after(){
		
	}
	
	
	@AfterClass
	public static void afterClass(){
		try{
			Thread.sleep(10000);
			driver.close();
			driver.quit();
		}catch (InterruptedException e){
			logger.error("Logger info : " + e);
		}
	}
}
