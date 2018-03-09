package com.chess.Chess.server;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Component
public class ApplicationProperties {
        private final static Logger logger = Logger.getLogger(ApplicationProperties.class);

        private static int port;
        private static int poolSize;
        private static long timeToKeepAlive;

        static {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream("/config.properties"));
                port = Integer.valueOf(properties.getProperty("port"));
                poolSize = Integer.valueOf(properties.getProperty("poolSize"));
                timeToKeepAlive = Long.valueOf(properties.getProperty("timeToKeepAlive"));
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }

        public static int getPort() {
            return port;
        }

        public static int getPoolSize() {
            return poolSize;
        }

        public static long getTimeToKeepAlive() {
            return timeToKeepAlive;
        }

 }
