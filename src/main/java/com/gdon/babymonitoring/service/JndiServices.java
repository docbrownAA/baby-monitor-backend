
package com.gdon.babymonitoring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.function.Supplier;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helpers to access to resources exposed on JNDI. Can't be managed by Spring (@Service/@Component/@Repository...) because we are using this bean in some
 * constructors. We can't use it in PostConstruct 'cause Ektorp needs it in constructor.
 *
 * @author gduvinage
 */
//@Service
public class JndiServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(JndiServices.class);
    /**
     * Singleton.
     */
    private static JndiServices instance;

    /**
     * private for Singleton pattern.
     */
    private JndiServices() {
        LOGGER.warn("Cannot be managed by Spring, because Ektorp needs it in constructor. Ektorp must evoluate to fix this. "
                + "Look for FIX EKTORP comment in the code to see everywhere it happens.");
    }

    /**
     * Get the unique instance of this bean. Cannot be managed by Spring, because Ektorp needs it in constructor. Ektorp must evoluate to fix this. Look for FIX
     * EKTORP comment in the code to see everywhere it happens.
     *
     * @return the unique instance of this bean.
     */
    public static JndiServices getInstance() {
        if (null == instance) {
            instance = new JndiServices();
        }

        return instance;
    }

    /**
     * Retrieve Properties exposed on JDNI by the server.
     *
     * @param contextName the contextName we are lookig for in JNDI.
     *
     * @return the Properties pointed out by the context name in the JNDI directory.
     *
     * @throws NamingException in case the contextName is not exposed in the JNDI directory
     * @throws IOException in case the contextName points to a properties files which is not available on the FileSystem.
     */
    public Properties getPropertiesFromContext(final String contextName) throws NamingException, IOException {
        final Supplier<NamingException> contextNamingException = () -> new NamingException(String.format("Cannot read context %s from JNDI", contextName));

        final String propertiesFileLocation = new JndiHelper<String>().getElementFromContext(contextName).orElseThrow(contextNamingException);
        final File file = new File(propertiesFileLocation);

        LOGGER.info("Using file located at {}", file.getAbsolutePath());
        // Initialize a Property element
        final Properties properties = new Properties();
        // The environment variable couchdb is a config file
        // We load properties from the config file
        properties.load(new FileInputStream(file));

        return properties;
    }

    /**
     * Retrieve URL exposed on JDNI by the server.
     *
     * @param contextName the contextName we are looking for in JNDI.
     *
     * @return the URL pointed out by the context name in the JNDI directory.
     *
     * @throws NamingException in case the contextName is not exposed in the JNDI directory
     */
    public URL getUrlFromContext(final String contextName) throws NamingException {
        final Supplier<NamingException> contextNamingException = () -> new NamingException(String.format("Cannot read context %s from JNDI", contextName));

        final URL serverUrl = new JndiHelper<URL>().getElementFromContext(contextName).orElseThrow(contextNamingException);
        LOGGER.info("URL retrieved from JNDI {}", serverUrl.toString());

        return serverUrl;
    }

    /**
     * Retrieve String exposed on JDNI by the server.
     *
     * @param contextName the contextName we are looking for in JNDI.
     *
     * @return the String pointed out by the context name in the JNDI directory.
     *
     * @throws NamingException in case the contextName is not exposed in the JNDI directory
     */
    public String getStringFromContext(final String contextName) throws NamingException {
        final Supplier<NamingException> contextNamingException = () -> new NamingException(String.format("Cannot read context %s from JNDI", contextName));

        final String string = new JndiHelper<String>().getElementFromContext(contextName).orElseThrow(contextNamingException);
        LOGGER.info("String retrieved from JNDI {}", string);

        return string;
    }
}
