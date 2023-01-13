package UIElements.demo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NavbarTests {
    String key = "webdriver.chrome.driver";
    String value = "src/main/resources/chromedriver.exe";
    WebDriver driver;
    String baseUrl = "http://localhost:3000/";

    @BeforeTest
    public void setUp() {
        System.setProperty(key,value);
        driver = new ChromeDriver();
    }

    @Test(priority = 1)
    public void verifyHomeButton(){
        driver.get(baseUrl);
        driver.findElement(By.linkText("Home")).click();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, baseUrl, "Actual url is incorrect");
    }

    @Test(priority = 2)
    public void verifyCategoriesButton(){
        driver.get(baseUrl);
        driver.findElement(By.linkText("Categories")).click();
        String expectedUrl = "http://localhost:3000/view-all/all-films";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual url is incorrect");
    }

    @Test(priority = 3)
    public void verifyAddFilmButton(){
        driver.get(baseUrl);
        driver.findElement(By.linkText("Add Film")).click();
        String expectedUrl = "http://localhost:3000/film/add-film";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual url is incorrect");
        driver.close();
    }





}
