
package com.gdon.babymonitoring.service;

import java.util.Optional;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parameterized Helper to get anything out of JNDI.
 *
 * @author gduvinage
 */
public class JndiHelper<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JndiHelper.class);

    private static final String JAVA_CONTEXT = "java:";
    private static final String INITIAL_CONTEXT = JAVA_CONTEXT + "comp/env";

    public Optional<T> getElementFromContext(final String contextName) throws NamingException {
        final Context initialContext = new InitialContext();
        // Get the Environement variable (from the global context) identified by its name
        Optional<T> elementFromContext;
        // Try one after the other different JNDI configuration (depending on wether we are deploying on Tomcat or WebSphere Liberty)
        try {
            // Get the global context
            final Context environmentContext = (Context) initialContext.lookup(INITIAL_CONTEXT);
            elementFromContext = Optional.of((T) environmentContext.lookup(contextName));
            LOGGER.info("Successfully getting element from JNDI context: {}/{}: {}", INITIAL_CONTEXT, contextName, elementFromContext.get());
        } catch (NamingException e) {
            LOGGER.warn(String.format("Problem %s while accessing context %s/%s. Trying with another context.", e.getMessage(), INITIAL_CONTEXT, contextName));
            try {
                elementFromContext = Optional.of((T) initialContext.lookup(JAVA_CONTEXT + contextName));
                LOGGER.info("Successfully getting element from second JNDI context: {}{}: {}", JAVA_CONTEXT, contextName, elementFromContext.get());
            } catch (NamingException e2) {
                LOGGER.warn(String.format("Problem %s while accessing context %s%s. Trying with a third context.", e2.getMessage(), JAVA_CONTEXT, contextName));
                try {
                    elementFromContext = Optional.of((T) initialContext.lookup(contextName));
                    LOGGER.info("Successfully getting element from third JNDI context: {}: {}", contextName, elementFromContext.get());
                } catch (Exception e3) {
                    LOGGER.error(String.format("Error %s while accessing context %s", e3.getMessage(), contextName), e3);
                    elementFromContext = Optional.empty();
                }
            }
        }

        return elementFromContext;
    }
}
