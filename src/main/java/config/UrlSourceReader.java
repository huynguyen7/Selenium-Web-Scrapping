package config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UrlSourceReader {

    private HashMap<String, String> table;
    private final String path = "../urls.properties";

    public UrlSourceReader() throws IOException {
        Properties prop = readProperties(path);
        table = new HashMap<String, String>();

        for(final Map.Entry<Object, Object> entry: prop.entrySet()) {
            table.put((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public HashMap<String, String> getUrlsTable() {
        return table;
    }

    private Properties readProperties(String path) throws IOException {
        InputStream fis = null;
        Properties prop = null;

        try {
            prop = new Properties();
            fis = this.getClass().getResourceAsStream(path);

            if(fis != null) {
                prop.load(fis);
            } else {
                throw new FileNotFoundException("Cannot find file with given path.");
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }

        return prop;
    }
}
