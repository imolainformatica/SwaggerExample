package it.imolinfo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by morlins on 06/06/17.
 */
public class ConfigurationManager {
    static final Logger LOG = LoggerFactory.getLogger(ConfigurationManager.class);
    static final Properties properties = new Properties();
    private static final String BASE_PATTERN = "events/hr/v1";

    static{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("api.properties");
        try {
            properties.load(input);
        } catch (IOException e) {
            LOG.error("parsing property failed",e);
        }
    }
    public static boolean testMode(){
        return Boolean.parseBoolean(properties.getProperty("worker.testmode","false"));
    }
    public static String getHCMHost(){
        return properties.getProperty("worker.host","http://localhost:8080");
    }
    public static String getBasePatternEvent() {
        return BASE_PATTERN;
    }
}
