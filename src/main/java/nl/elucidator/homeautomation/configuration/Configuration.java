package nl.elucidator.homeautomation.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by pieter on 3/5/14.
 */
@Startup
@Singleton
public class Configuration {

    private static final String APPLICATION_PROPERTIES = "application.properties";
    private Properties configData;
    private static final Logger LOGGER = LogManager.getLogger(Configuration.class);


    @PostConstruct
    public void fetchConfiguration() {

        LOGGER.info("Loading configuration");
        String fileName = APPLICATION_PROPERTIES;
        try {
            configData =
                    loadPropertiesFromClasspath(fileName);
        } catch (IOException e) {
            LOGGER.error("Error loading properties. " + e.getMessage());
        }
    }

    /**
     * Load properties file from classpath with Java 7 :-)
     *
     * @param fileName
     * @return properties
     */
    private Properties loadPropertiesFromClasspath(String fileName) throws IOException {
        Properties properties = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(
                fileName)) {
            if (in != null) {
                properties.load(in);
            }
        } catch (IOException ioe) {
            LOGGER.error("Can't load properties.", ioe);
            throw ioe;
        }

        return properties;
    }


    /**
     * Get a String property, named by the annotation {{@link nl.elucidator.homeautomation.configuration.NamedProperty}}
     *
     * @param point
     * @return String
     */
    public
    @Produces
    @NamedProperty
    String getString(final InjectionPoint point) {
        LOGGER.trace("Loading string for a String property " + point);
        String key = getKey(point);
        String propertyValue = configData.getProperty(key);
        if (propertyValue == null && isMandatory(point)) {
            throw new IllegalArgumentException("configuration key: \"" + key + "\" missing but defined as mandatory.");
        }
        String result = (propertyValue == null) ? getDefaultValue(point) : propertyValue;
        LOGGER.info("Property: " + key + "=" + result);
        return result;
    }

    public
    @Produces
    @NamedProperty
    int getInt(final InjectionPoint point) {
        LOGGER.trace("Loading string for a integer property " + point);
        String key = getKey(point);
        String propertyValue = configData.getProperty(key);
        if (propertyValue == null && isMandatory(point)) {
            throw new IllegalArgumentException("configuration key: \"" + key + "\" missing but defined as mandatory.");
        }
        String result = (propertyValue == null) ? getDefaultValue(point) : propertyValue;
        LOGGER.info("Property " + key + "=" + result);
        return Integer.parseInt(result);
    }

    private String getKey(final InjectionPoint point) {
        String key = point.getAnnotated().getAnnotation(NamedProperty.class).key();
        LOGGER.trace("Retrieved property key: \"" + key + "\"");
        if (key != null && key.length() > 0) {
            return key;
        }
        return null;
    }

    private boolean isMandatory(final InjectionPoint point) {
        return point.getAnnotated().getAnnotation(NamedProperty.class).mandatory();
    }

    private String getDefaultValue(final InjectionPoint point) {
        return point.getAnnotated().getAnnotation(NamedProperty.class).defaultValue();
    }
}
