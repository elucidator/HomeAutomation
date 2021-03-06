package nl.elucidator.homeautomation.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Factory for providing configuration from a properties file
 */
@Startup
@Singleton
public class ConfigurationFactory {

    private static final String APPLICATION_PROPERTIES = "application.properties";
    private Properties configData;
    private static final Logger LOGGER = LogManager.getLogger(ConfigurationFactory.class);


    @Resource(name = "applicationConfiguration")
    Object applicationConfiguration;

    @PostConstruct
    public void fetchConfiguration() {

        LOGGER.info("Loading configuration");
        try {
            if (applicationConfiguration != null) {
                configData = loadPropertiesFromFile((String) applicationConfiguration);
                return;
            }
            configData =
                    loadDefault();
        } catch (IOException e) {
            LOGGER.error("Error loading properties. " + e.getMessage());
        }
    }

    private Properties loadPropertiesFromFile(final String applicationConfiguration) {
        LOGGER.info("Loading properties from JNDI defined location: " + applicationConfiguration);
        Properties properties = new Properties();

        try (InputStream in = new FileInputStream(applicationConfiguration)) {
            properties.load(in);
        } catch (FileNotFoundException e) {
            LOGGER.error("Unable to read configuration file. (" + applicationConfiguration + ") -> " + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Error during read of configuration file (" + applicationConfiguration + ") -> " + e.getMessage());
        }

        return properties;
    }

    /**
     * Load properties file from classpath with Java 7 :-)
     *
     * @return properties
     */
    private Properties loadDefault() throws IOException {
        LOGGER.info("Loading default properties from classpath " + APPLICATION_PROPERTIES);
        Properties properties = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(
                APPLICATION_PROPERTIES)) {
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
     * @param point injection point
     * @return String
     */
    @Produces
    @NamedProperty
    public String getString(final InjectionPoint point) {
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
