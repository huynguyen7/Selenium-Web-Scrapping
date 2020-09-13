package config;

import org.openqa.selenium.chrome.ChromeDriver;

//singleton class
public class DriverInitializer {

    private static DriverInitializer single_instance = null;
    private ChromeDriver chromeDriver;

    public DriverInitializer() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver");
        chromeDriver = new ChromeDriver();
    }

    public ChromeDriver getChromeDriver() {
        return chromeDriver;
    }

    public static DriverInitializer getInstance() {
        if(single_instance == null)
            single_instance = new DriverInitializer();
        return single_instance;
    }
}