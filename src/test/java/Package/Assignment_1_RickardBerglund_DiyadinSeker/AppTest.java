package Package.Assignment_1_RickardBerglund_DiyadinSeker;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

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
	
	private static WebDriver driver;
	private static WebDriverWait wait3s;
	private static String baseURL = "https://www.br.se";
	
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
	public void TF001(){
		WebElement varukorg = driver.findElement(By.xpath(".//*[@id='iconbar']/div[4]/a/div"));
		varukorg.click();
		wait3s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='basket']/div[1]/header/div/p/a")));
		String webTitle = driver.getTitle();
		assertNotNull(webTitle);
		assertEquals("Varukorg | Leksaker från BR", webTitle);
	}
	@Test
	public void TF002(){
		System.out.println("a");
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
			e.printStackTrace();
		}
	}
}
