package templates;

import org.openqa.selenium.WebDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class WebTemplate {

    protected StringBuilder data;

    public abstract void processTemplate(WebDriver driver, String url);
    public abstract void processTemplate(WebDriver driver);
    public String getData() {
        return data.toString();
    }

    protected void saveData(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("/Users/nguyenhuy/workspace/selenium-test-space/output/" + fileName, true)  //Set true for append mode
        );
        writer.write(data.toString());
        writer.close();
    }
}
