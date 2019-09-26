package com.microfocus.ring2parkms.locations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The locations Spring configuration.
 *
 * @author Kevin A. Lee
 */
@Configuration
@ComponentScan
@EntityScan("com.microfocus.ring2parkms.locations")
@EnableJpaRepositories("com.microfocus.ring2parkms.locations")
@PropertySource("classpath:db-config.properties")
public class LocationsConfiguration {

    protected Logger logger;

    public LocationsConfiguration() {
        logger = Logger.getLogger(getClass().getName());
    }

    /**
     * Creates an in-memory "rewards" database populated with test data for fast
     * testing
     */
    @Bean
    public DataSource dataSource() {
        logger.info("dataSource() invoked");

        // Create an in-memory H2 relational database containing some demo
        // locations.
        DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema.sql")
                .addScript("classpath:testdb/data.sql").build();

        logger.info("dataSource = " + dataSource);

        // Sanity check
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> locations = jdbcTemplate.queryForList("SELECT number FROM T_LOCATION");
        logger.info("System has " + locations.size() + " locations");

        return dataSource;
    }
}

