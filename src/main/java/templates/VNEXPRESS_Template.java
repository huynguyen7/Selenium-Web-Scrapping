package templates;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VNEXPRESS_Template extends WebTemplate {

    private final String url;
    private final static int MAX_PAGE = 200; //Cannot be smaller than 1
    private final static String FILENAME = "VNEXPRESS-news.txt";

    public VNEXPRESS_Template(WebDriver driver, String url) throws IOException {
        this.url = url;
        int count = 1;

        while(count <= MAX_PAGE) {
            data = new StringBuilder();
            if(count != 1) processTemplate(driver, url + count);
            else processTemplateP1(driver); //process first page of each sections

            saveData(FILENAME);
            count++;
        }
    }

    private void processData(WebDriver driver, List<String> articles) {
        for(String article: articles) {
            driver.navigate().to(article);
            appendData(driver, article);
        }
    }

    //VNEXPRESS has first page - UNIQUE
    public void processTemplateP1(WebDriver driver) {
        driver.get(url + 1);
        List<String> articles = new ArrayList<String>();

        boolean validPage = waitPageLoadSuccess(driver
                , "//section//div//div//div//article//h3//a", url);
        if(!validPage) return;

        //getting urls
        for(WebElement element: driver
                .findElements(By.xpath("//section//div//div//div//article//h3//a"))) {
            if(element.getAttribute("href") != null)
                articles.add(element.getAttribute("href"));
        }

        //processing data
        processData(driver, articles);
    }

    public void processTemplate(WebDriver driver, String url) {
        driver.navigate().to(url);
        List<String> articles = new ArrayList<String>();

        boolean validPage = waitPageLoadSuccess(driver
                , "//div//div[1]//div[2]//article//h3//a", url);
        if(!validPage) return;

        //getting urls
        for(WebElement element: driver
                .findElements(By.xpath("//div//div[1]//div[2]//article//h3//a"))) {
            articles.add(element.getAttribute("href"));
        }

        //processing data
        processData(driver, articles);
    }

    public void processTemplate(WebDriver driver) {
        //do nothing
    }

    private void appendData(WebDriver driver, String url) {
        boolean validPage = waitPageLoadSuccess(driver
                , "//body/section/div/div/article/p", url);
        if(!validPage) return;

        for(WebElement element: driver
                .findElements(By.xpath("//body/section/div/div/article/p"))) {
            data.append(element.getText());
            data.append("\n");
        }
    }

    private boolean waitPageLoadSuccess(WebDriver driver, String xpath, String url) {
        int attempts = 0;
        while(attempts < 3) {
            try {
                driver.findElements(By.xpath("//body/section/div/div/article/p"));
                return true;
            } catch (StaleElementReferenceException e) {
                System.out.println("Cannot process page: " + url);
            }
            attempts++;
        }

        return false;
    }

    public String getData() {
        return data.toString();
    }
}
