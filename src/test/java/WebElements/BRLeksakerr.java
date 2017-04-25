package WebElements;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BRLeksakerr {

	public  WebDriver driver;
	public static WebElement element = null;
	
	
	@FindBy(xpath="//html/body/nav/div[1]/div[1]/ul/li[2]/a")
	public WebElement kategorier;
	
	@FindBy(linkText="Rollspel")
	public WebElement Rollspel;
	
	@FindBy(xpath="//html/body/div[8]/div[2]/div/div/div[4]/div[1]/div/div[3]/a[1]")
	public WebElement putInBasket;
	
	@FindBy(xpath="//html/body/header/div/div/div[2]/div[4]")
	public WebElement varukorg;
	
	@FindBy(xpath="//html/body/nav/div[2]/div[1]/div[2]/div[2]/div[1]/ul/li[1]/a")
	public WebElement sportOchAktivLek;
			
	@FindBy(xpath="//html/body/div[8]/div[2]/div[4]/div[2]/div/ul/li[1]/div/div/a")
	public WebElement guldMedaljer;
	
	
	public BRLeksakerr(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void clickKategorier(){
		kategorier.click();
	}
	
	public void clickRollspel(){
		System.out.println("Klickar på rollspel " + Rollspel.getText());
		Rollspel.click();
	
	}
	
	public void assertCurrentURL(String expectedURL){
		
		String URL = driver.getCurrentUrl();
		Assert.assertEquals("This is a messafe if assert not true ",URL, expectedURL );
	}
		
	public void getTitle(){
		String title = driver.getTitle();
		System.out.println("Verifierar nuvarande focus: " + title);
		
	}

	@FindBy(id="j_username")
	WebElement alreadyMembereMail;

	public void alreadyMemberUsername(String email){
		alreadyMembereMail.sendKeys(email);
		
	}
	
	@FindBy(xpath="//html/body/header/div/div/div[2]/div[1]/a[1]/div")
	WebElement loggaIn;
	
	public void clickLogIn(){
		loggaIn.click();
	}
	
	@FindBy(id="j_password")
	WebElement alreadyMemberPassword;
	
	public void alreadyMemberPassword(String password){
		alreadyMemberPassword.sendKeys(password);
	}
	
	@FindBy(xpath="//html/body/div[6]/div[2]/div[2]/div/div[2]/form/div[3]/button")
	WebElement loginButton;
	
	public void clickLoginButton(){
		loginButton.click();
	}
	
	@FindBy(linkText="Födelsedagspresent")
	WebElement fodelsedagspresent;
	
	public void verifyFodelsedagspresent(){
		if(fodelsedagspresent.isDisplayed()){
			System.out.println("Födelsedagspresent element is visible");
		}else{
			System.out.println("Födelsedagspresent element is not visible");
		}
	}

	
	@FindBy(className="global-alerts")
	public	WebElement alertAccountNotFound;
	

		
		@FindBy(xpath=".//*[@id='clubbr-nav-menu-container']/div/ul/li[5]/a")
		WebElement loggaUt;
		
		public void logOutFromAccount(){
			loggaUt.click();
			
		}
		
		@FindBy(id="js-site-search-input")
		WebElement searchField;
	
		public void sendInputToSearchField(String input){
		searchField.sendKeys(input);
		
			}
		@FindBy(id="btnSearch")
		WebElement searchButton;
		public void clickSearchButton(){
			searchButton.click();
			
		}			
		@FindBy(xpath=("html/body/div[8]/div[2]/div/div[2]/div/div/p"))
		WebElement searchResult;
		
		public void getSearchResult(){
		String result = searchResult.getText();
		System.out.println("Search Result: " + result);
		}

		public void assertKontoNotFound(){
		String actual =	alertAccountNotFound.getText();
		String Expected = "Kontot hittades inte";
		
		System.out.println(actual.contains(Expected));
		
		
//		Assert.assertEquals(actual.contains(Expected));

		
			
			
			
		}
}