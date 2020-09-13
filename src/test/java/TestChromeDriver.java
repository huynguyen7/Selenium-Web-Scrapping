import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

@Test
public class TestChromeDriver {
    WebDriver driver;
    String baseUrl = "http://www.facebook.com";

    public void test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "./chromedriver");
        driver = new ChromeDriver();


        driver.get(baseUrl);
        driver.findElement(By.xpath("//input[@id='email']"))
                .sendKeys("huy.nguyen712sp@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']"))
                .sendKeys("GvDdk2CQ&a#C8Ho%HDu3Udm&1#Cjnn");
        driver.findElement(By.id("loginbutton"))
                .click();

        Thread.sleep(7000);
        driver.quit();

    }
}