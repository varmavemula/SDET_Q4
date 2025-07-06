package SDET.comprehensive;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class searchFlights {
	
	WebDriver driver;
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\VARMA\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@Test
	public void testFlightSearch() throws InterruptedException {
		driver.get("https://www.makemytrip.com/");
		
		Thread.sleep(5000);
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(7000));
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("commonModal__close"))));
		WebElement ModalClose = driver.findElement(By.className("commonModal__close"));
		ModalClose.click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//li[@data-cy= 'roundTrip']/span")).click();
		
		WebElement FromField = driver.findElement(By.xpath("//input[@id='fromCity']"));
		WebElement toField = driver.findElement(By.xpath("//input[@id='toCity']"));
		
		
		
		FromField.sendKeys("HYD");
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//finding hyderabad xpath from the results//li[@role='option' and .//span[.='Hyderabad']]/div/div/p/span[text()='HYD']
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//span[text()='HYD']"))));
		WebElement fromLoc = driver.findElement(By.xpath("//span[text()='HYD']"));
		String fromLocation = driver.findElement(By.xpath("//span[text()='HYD']/preceding-sibling::span/span")).getText();
		
		fromLoc.click();

		
		 
		toField.sendKeys("MAA");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[text()='MAA']"))));
		Actions action = new Actions(driver);
		
		
		WebElement toLoc = driver.findElement(By.xpath("//div[text()='MAA']"));
		String toLocation = driver.findElement(By.xpath("//div[text()='MAA']/preceding-sibling::div/div/p")).getText();
		action.moveToElement(toLoc).click().perform();
		
		

		
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class='dateInnerCell']/p[text()='7']"))));
		

		WebElement departuredate=driver.findElement(By.xpath("//div[@class='dateInnerCell']/p[text()='7']"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class='dateInnerCell']/p[text()='9']"))));
		WebElement returnDate = driver.findElement(By.xpath("//div[@class='dateInnerCell']/p[text()='9']"));
		
		departuredate.click();
		returnDate.click();
		
		
		WebElement searchButton = driver.findElement(By.xpath("//p[@data-cy = 'submit']/a[text()='Search']"));
		
		searchButton.click();
		
		driver.findElement(By.xpath("//button[contains(text(), 'OKAY')]")).click();

        // Wait and verify result
        Thread.sleep(10000); // You can use WebDriverWait here
        boolean resultShown = driver.getPageSource().contains("Flights from");

        assert resultShown : "❌ Flight results page not displayed";
	}
	
	@AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
