
package com.gdon.babymonitoring.dao.configuration;

import com.gdon.babymonitoring.service.JndiServices;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Database configuration.
 *
 * @author Astek
 */
public abstract class AbstractCouchDbConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCouchDbConfiguration.class);

    private static final String NO_USERNAME = "NO_USERNAME";
    private static final String NO_PASSWORD = "NO_PASSWORD";

    /**
     * Couchdb host name with default value
     */
    private String host = "localhost";
    /**
     * Couchdb port
     */
    private String port = "5984";

    /**
     * Couchdb data base name
     */
    private String databaseName = "dirty";

    /**
     * Couchdb data base name.
     */
    private Optional<String> username = Optional.empty();

    /**
     * Couchdb data base name.
     */
    private Optional<String> password = Optional.empty();
    /**
     * FIX EKTORP Autowired wan't work because we are using this bean in the constructor. We can't use it in PostConstruct 'cause Ektorp needs it in
     * constructor. Ektorp sucks.
     */
    // @Autowired
    private final JndiServices jndiServices = JndiServices.getInstance();

    /**
     * Configure the database looking for a property file pointed out by the given context. <br/>
     * In the context.xml of Tomcat, there is an Environment tag that contains the location of couchdb.properties of our application. Via JNDI we get the path
     * of this file and load properties to set host/port etc
     *
     * Example of META-INF/context.xml in your project: <br/>
     * <code>
     * &lt;Context&gt; <br/>
     * &lt;ResourceLink name="couchdb/emodule" global="couchdb/emodule" type="java.lang.String"/&gt;
     * <br/>
     * &lt;/Context&gt; <br/>
     * </code>
     *
     * Example of Tomcat context.xml:  <br/>
     * <code>
     * &lt;Context&gt; <br/>
     * &lt;Environment name="couchdb/emodule" type="java.lang.String" value="${catalina.base}/conf/emodule-couchdb.properties"/&gt; <br/>
     * &lt;/Context&gt; <br/>
     * </code>
     *
     * @param contextName the name of the context where the file name must be located. e.g. <code>"couchdb/emodule"</code> to get file path from
     * <code>java:comp/env/couchdb/emodule</code> in JNDI.
     */
    public AbstractCouchDbConfiguration(final String contextName) {
        LOGGER.trace("Instanciating AbstractCouchDbConfiguration for context {}, with jndiServices: {}", contextName, jndiServices);

        try {
            LOGGER.info("Overwriting initial configuration for {} CouchDB: {}:{}@{}:{}/{}", contextName, username.orElse(NO_USERNAME),
                    password.orElse(NO_PASSWORD), host, port, databaseName);

            final Properties properties = jndiServices.getPropertiesFromContext(contextName);

            // Get properties value by keys
            final String dbConfName = "db";
            host = properties.getProperty(dbConfName + ".host");
            port = properties.getProperty(dbConfName + ".port");
            databaseName = properties.getProperty(dbConfName + ".databaseName");
            username = Optional.ofNullable(properties.getProperty(dbConfName + ".username"));
            password = Optional.ofNullable(properties.getProperty(dbConfName + ".password"));

            String fakePassword = NO_PASSWORD;
            if (password.isPresent()) {
                fakePassword = password.get().charAt(0) + "*****" + password.get().charAt(password.get().length() - 1);
            }
            LOGGER.info("Configuration for {} CouchDB: {}:{}@{}:{}/{}", contextName, username.orElse(NO_USERNAME), fakePassword, host, port, databaseName);
        } catch (NamingException | IOException e) {
            LOGGER.error(String.format("Error %s while accessing context %s", e.getMessage(), contextName), e);
        }
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(final String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(final String port) {
        this.port = port;
    }

    /**
     * @return the databaseName
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * @param databaseName the databaseName to set
     */
    public void setDatabaseName(final String databaseName) {
        this.databaseName = databaseName;
    }

    public Optional<String> getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = Optional.ofNullable(username);
    }

    public Optional<String> getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = Optional.ofNullable(password);
    }
}
