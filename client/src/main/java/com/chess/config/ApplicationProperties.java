package com.chess.config;

import com.chess.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {
    private static int port;
    private static String host;
    private static int height;
    private static int width;
    private static int maxPlaces;

    static {
        maxPlaces = 40;

        try {
            Properties properties = new Properties();
            InputStream is = Main.class.getResourceAsStream("/config.properties");
            properties.load(is);
            port = Integer.valueOf(properties.getProperty("port"));
            host = properties.getProperty("host");
            height = Integer.valueOf(properties.getProperty("height"));
            width = Integer.valueOf(properties.getProperty("width"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPort() {
        return port;
    }

    public static String getHost() {
        return host;
    }

    public static int getHeight() {
        return height;
    }

    public static int getMaxPlaces() {
        return maxPlaces;
    }

    public static int getWidth() {
        return width;
    }
}
