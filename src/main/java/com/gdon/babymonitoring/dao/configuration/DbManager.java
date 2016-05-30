package com.gdon.babymonitoring.dao.configuration;

import javax.annotation.PostConstruct;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DbManager for couchDB connection.
 *
 * @author gduvinage
 */
//@Service
public class DbManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    @Autowired
    private AbstractCouchDbConfiguration couchDbConfiguration;

    /**
     * db connection
     */
    private CouchDbConnector db;

    @PostConstruct
    protected void dbInit() {
        LOGGER.info("Instanciating DbManager for db {}:{}/{}", couchDbConfiguration.getHost(), couchDbConfiguration.getPort(),
                couchDbConfiguration.getDatabaseName());

        final StdHttpClient.Builder httpClientBuilder = new StdHttpClient.Builder()
                .host(couchDbConfiguration.getHost())
                .port(Integer.parseInt(couchDbConfiguration.getPort()));

        if (couchDbConfiguration.getUsername().isPresent() && couchDbConfiguration.getPassword().isPresent()) {
            LOGGER.trace("Connection to Db with Username {}", couchDbConfiguration.getUsername());
            httpClientBuilder.username(couchDbConfiguration.getUsername().get());
            httpClientBuilder.password(couchDbConfiguration.getPassword().get());
        }

        final HttpClient httpClient = httpClientBuilder.build();

        final CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        LOGGER.trace("dbInstance retrieved.");

        db = new StdCouchDbConnector(couchDbConfiguration.getDatabaseName(), dbInstance);

        LOGGER.trace("Db connector instanciated");

        db.createDatabaseIfNotExists();
    }

    /**
     * getDb
     *
     * @return CouchDbConnector
     */
    public CouchDbConnector getDb() {
        return db;
    }
}
