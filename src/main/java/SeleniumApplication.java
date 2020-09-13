import config.DriverInitializer;
import config.UrlSourceReader;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Map;

public class SeleniumApplication {

    private static DriverInitializer initializer;
    private static WebDriver driver;
    private static UrlSourceReader urlSourceReader;
    private static Map<String, String> urlsTable;

    public static void main(String args[]) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "./chromedriver");
        initConfig();
        Parser parser = new Parser(urlsTable, driver);
        driver.quit();
    }

    private static void initConfig() throws IOException {
        initializer = DriverInitializer.getInstance();
        driver = initializer.getChromeDriver();
        urlSourceReader = new UrlSourceReader();
        urlsTable = urlSourceReader.getUrlsTable();
    }
}
