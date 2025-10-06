package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Properties;

public class LecturaPropiedades {

    private static Properties properties = new Properties();
    private final String url;
    private final String user;
    private final String password;

    LecturaPropiedades() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/config/application.properties"))){
            properties.load(br);
             url = properties.getProperty("url");
             user = properties.getProperty("user");
             password = properties.getProperty("password");
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
