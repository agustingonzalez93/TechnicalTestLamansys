package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {
    public static Properties LoadPropertiesFile() {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream("src/main/resources/data.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }

    public static String getProperty(String propertyAtribute) {
        return ((String) LoadPropertiesFile().get(propertyAtribute));
    }

    public static String readJsonFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
