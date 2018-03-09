package com.chess.config;

import java.io.FileInputStream;
import java.io.IOException;
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
            properties.load(new FileInputStream("resources/config.properties"));
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
