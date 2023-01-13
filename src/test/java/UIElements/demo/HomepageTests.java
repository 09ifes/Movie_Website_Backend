package UIElements.demo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class HomepageTests {
    String key = "webdriver.chrome.driver";
    String value = "src/main/resources/chromedriver.exe";
    WebDriver driver;
    String baseUrl = "http://localhost:3000/";

    @BeforeTest
    public void setUp() {
        System.setProperty(key,value);
        driver = new ChromeDriver();
    }

    @Test
    public void verifyHomepage(){
        driver.get(baseUrl);
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, baseUrl, "Actual url is incorrect");
        driver.close();
    }





}
