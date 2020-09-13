import org.openqa.selenium.WebDriver;
import templates.VNEXPRESS_Template;
import java.io.IOException;
import java.util.Map;

public class Parser {

    private WebDriver driver;
    private Map<String, String> table;

    private Parser() {}

    public Parser(Map<String, String> table, WebDriver driver) throws InterruptedException, IOException {
        this.driver = driver;
        this.table = table;
        parsing();
    }

    public void parsing() throws IOException {
        for(String key: table.keySet()) {
            if(key.contains("vnexpress"))
                new VNEXPRESS_Template(driver, table.get(key));
        }
    }
}