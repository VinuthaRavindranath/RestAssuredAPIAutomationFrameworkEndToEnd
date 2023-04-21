package com.qa.api.gorest.base;

import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected Properties prop;
    protected String baseUri;
    protected String basePath;
    protected String token;


    @BeforeTest
    public void setUp() {
        prop = new Properties();
        FileInputStream ip;
        try {
            ip = new FileInputStream("./src/test/resources/config/config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        baseUri = prop.getProperty("baseUri").trim();
        basePath = prop.getProperty("basePath").trim();
        token = getToken().trim();
    }

    public String getToken() {
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();
        return dotenv.get("token");
    }
}
