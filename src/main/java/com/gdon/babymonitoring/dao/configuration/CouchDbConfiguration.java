
package com.gdon.babymonitoring.dao.configuration;

import org.springframework.context.annotation.Configuration;

/**
 * Database configuration.
 * 
 * TODO Update CONTEXT_NAME
 *
 * @author gduvinage
 */
//@Configuration
public class CouchDbConfiguration extends AbstractCouchDbConfiguration {

    /**
     * Couchdb JNDI context. TODO Update this field.
     */
    public static final String CONTEXT_NAME = "couchdb/baby-monitor-backend";

    public CouchDbConfiguration() {
        this(CONTEXT_NAME);
    }

    public CouchDbConfiguration(final String dbName) {
        super(dbName);
    }
}