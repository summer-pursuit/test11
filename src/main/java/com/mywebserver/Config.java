package main.java.com.mywebserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 29753
 */
public class Config {
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("server.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}