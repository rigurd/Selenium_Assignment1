package Package.Assignment_1_RickardBerglund_DiyadinSeker;

import java.util.logging.SimpleFormatter;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
	public static Logger logger;
	public static FileHandler file;
	
	@BeforeClass
	public static void setup() {
		driver = new FirefoxDriver();
		wait3s = new WebDriverWait(driver, 3);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger = Logger.getLogger(AppTest.class.getName());
		try{
			file = new FileHandler("C:\\Users\\Rigurd\\workspace\\Assignment_1_RickardBerglund_DiyadinSeker\\logs\\log.log");
		}catch(Exception e){
			System.out.println(e.getStackTrace().toString());
		}
		logger.addHandler(file);
		SimpleFormatter formatter = new SimpleFormatter();
		file.setFormatter(formatter);
	}
	@Before
	public void before(){
		driver.get(baseURL);
	}
	@Test
	public void TF001(){
		// Saving the websites current Title to String
		String homeTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		Assert.assertNotNull(homeTitle);
		Assert.assertEquals("Leksaker från BR | Hem", homeTitle);
		// Finding WebElement varukorg and saves it to variable then clicks
		WebElement varukorg = driver.findElement(By.xpath(".//*[@id='iconbar']/div[4]/a/div"));
		varukorg.click();
		// Waiting 3 seconds until element is located
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a")));
		// Saving the websites current (and new) Title to String
		String varukorgTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		Assert.assertNotNull(varukorgTitle);
		Assert.assertEquals("Varukorg | Leksaker från BR", varukorgTitle);
		try{
			if(varukorgTitle.startsWith("Varukorg")){
				logger.info("Title is correct");
			}else{
				logger.info("Title is wrong");
			}
		}catch (Exception e){
			logger.warning("Error : " + e);
		}
		logger.info("TF001 Pass");
	}
	@Test
	public void TF002(){
		// Saving the websites current Title to String
		String homeTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		Assert.assertNotNull(homeTitle);
		Assert.assertEquals("Leksaker från BR | Hem", homeTitle);
		// Finding WebElement varukorg and saves it to variable then clicks
		WebElement varukorg = driver.findElement(By.xpath(".//*[@id='iconbar']/div[4]/a/div"));
		varukorg.click();
		// Waiting 3 seconds until element is located
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a")));
		// Saving the websites current (and new) Title to String
		String varukorgTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		Assert.assertNotNull(varukorgTitle);
		Assert.assertEquals("Varukorg | Leksaker från BR", varukorgTitle);
		// Fungerar EJ pga man måste lägga en vara i kassan för att komma till checkout
		WebElement gåTillKassan = driver.findElement(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a"));
		gåTillKassan.click();
		logger.warning("Kan ej gå till kassan från varukorgen utan att ha något i varukorgen");
		logger.info("TF002 Pass");
		
	}
	@Test
	public void TF004(){
		// Saving the websites current Title to String
		String homeTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		Assert.assertNotNull(homeTitle);
		Assert.assertEquals("Leksaker från BR | Hem", homeTitle);
		// Fetching webelement kategorier and asserts with another driver find element of the same location
		WebElement kategorier = driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a"));
		Assert.assertEquals(kategorier, driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a")));
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
			logger.warning("Error : "+ e );
		}
		// Getting varukorg element and clicks
		WebElement varukorg = driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/div/div[3]/a/div[1]"));
		varukorg.click();
		// Waits for an element inside varukorg
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a")));
		// Asserting that we've arrived to varukorg
		String varukorgTitle = driver.getTitle();
		Assert.assertEquals("Varukorg | Leksaker från BR", varukorgTitle);
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
			Assert.assertTrue(fakturaBtn.isSelected());
		}else if(bankTransferBtn.isSelected()){
			logger.info("bankTransferBtn works");
			Assert.assertTrue(bankTransferBtn.isSelected());
		}else if(creditCardBtn.isSelected()){
			logger.info("creditCardBtn works");
			Assert.assertTrue(creditCardBtn.isSelected());
		}else{
			logger.warning("Nothing works");
		}
		logger.info("TF004 Pass");
	}
	@Test
	public void TF005(){
		// Saving the websites current Title to String
		String homeTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		Assert.assertNotNull(homeTitle);
		Assert.assertEquals("Leksaker från BR | Hem", homeTitle);
		// Fetching webelement kategorier and asserts with another driver find element of the same location
		WebElement kategorier = driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a"));
		Assert.assertEquals(kategorier, driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a")));
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
			logger.warning("Interrupted" + e);
		}
		// Getting top 2 indexes from ul list and parsing from string to int
		WebElement index0price = driver.findElement(By.xpath(".//*[@id='content']/div[4]/div[2]/div/ul/li[1]/div/div/a/div[3]/div/span"));
		String index0 = index0price.getText();
		Assert.assertNotNull(index0);
		String[] newIndex0 = index0.split(",");
		int a = Integer.parseInt(newIndex0[0]);
		Assert.assertEquals(799, a);
		WebElement index1price = driver.findElement(By.xpath(".//*[@id='content']/div[4]/div[2]/div/ul/li[2]/div/div/a/div[3]/div/span"));
		String index1 = index1price.getText();
		Assert.assertNotNull(index1);
		String[] newIndex1 = index1.split(",");
		int b = Integer.parseInt(newIndex1[0]);
		Assert.assertEquals(599, b);
		// Comparing indexes
		if (a > b){
			logger.info("Index0 has the highest price");
		}else if(a == b){
			logger.info("Index0 and Index1 has same price");
		}else if(b > a){
			logger.info("Index1 has higher price than Index0");
		}
		logger.info("TF005 Pass");
	}
	@Test
	public void TF006(){
		// Saving the websites current Title to String
		String homeTitle = driver.getTitle();
		// Asserting that the saved String with title is correct with the expected result
		Assert.assertNotNull(homeTitle);
		Assert.assertEquals("Leksaker från BR | Hem", homeTitle);
		// Getting webelement kategorier and asserts with another driver find element of the same location
		WebElement kategorier = driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a"));
		Assert.assertEquals(kategorier, driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a")));
		kategorier.click();
		// Getting subKategori by xpath and clicks
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
		// Getting index1 from the category list and adds it to varukorg
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content']/div[4]/div[2]/div/ul/li[2]/div/a")));
		WebElement index1 = driver.findElement(By.xpath(".//*[@id='content']/div[4]/div[2]/div/ul/li[2]/div/a"));
		index1.click();
		// Forcing a thread sleep on 3 seconds, no viable element to waitfor in BR website. Didn't find any other option.
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			logger.warning("Error : "+ e );
		}
		// Getting varukorg icon and clicks
		WebElement varukorg = driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/div/div[3]/a/div[1]"));
		varukorg.click();
		// Waiting until an element inside varukorg is visible
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a")));
		// Asserting that we've arrived to varukorg
		String varukorgTitle = driver.getTitle();
		Assert.assertEquals("Varukorg | Leksaker från BR", varukorgTitle);
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
		Assert.assertEquals("Till en privat adress (PostNord) (From 0", newPrivateAdress[0]);
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
			logger.warning("Logger info : " + e);
		}
	}
}
