package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MakeMyTripSteps {
    WebDriver driver;
    WebDriverWait wait;

    @Given("I launch the Chrome browser")
    public void i_launch_the_chrome_browser() {
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\VARMA\\\\Downloads\\\\chromedriver-win64\\\\chromedriver-win64\\\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @And("I open the MakeMyTrip website")
    public void i_open_the_makemytrip_website() {
        driver.get("https://www.makemytrip.com/");
    }

    @And("I close the initial modal if present")
    public void i_close_the_initial_modal_if_present() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("commonModal__close")));
        driver.findElement(By.className("commonModal__close")).click();
    }

    @When("I select Round Trip option")
    public void i_select_round_trip_option() {
        driver.findElement(By.xpath("//li[@data-cy='roundTrip']/span")).click();
    }

    @And("I enter From location as {string} and To location as {string}")
    public void i_enter_from_and_to_locations(String from, String to) {
        WebElement fromField = driver.findElement(By.id("fromCity"));
        fromField.sendKeys(from);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + from + "']")));
        driver.findElement(By.xpath("//span[text()='" + from + "']")).click();

        WebElement toField = driver.findElement(By.id("toCity"));
        toField.sendKeys(to);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + to + "']")));
        Actions action = new Actions(driver);
        WebElement toLoc = driver.findElement(By.xpath("//div[text()='" + to + "']"));
        action.moveToElement(toLoc).click().perform();
    }

    @And("I select departure date as {string} and return date as {string}")
    public void i_select_departure_and_return_dates(String depDate, String retDate) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dateInnerCell']/p[text()='" + depDate + "']")));
        WebElement departureDate = driver.findElement(By.xpath("//div[@class='dateInnerCell']/p[text()='" + depDate + "']"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dateInnerCell']/p[text()='" + retDate + "']")));
        WebElement returnDate = driver.findElement(By.xpath("//div[@class='dateInnerCell']/p[text()='" + retDate + "']"));

        departureDate.click();
        returnDate.click();
    }

    @And("I click on the Search button")
    public void i_click_on_the_search_button() {
        driver.findElement(By.xpath("//p[@data-cy='submit']/a[text()='Search']")).click();
    }

    @Then("I should see the search results page")
    public void i_should_see_the_search_results_page() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Page Title: " + driver.getTitle());
        driver.quit();
    }
}