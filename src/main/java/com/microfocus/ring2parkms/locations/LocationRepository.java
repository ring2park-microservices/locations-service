package com.microfocus.ring2parkms.locations;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository for Location data implemented using Spring Data JPA.
 *
 * @author Kevin A. Lee
 */
public interface LocationRepository extends Repository<Location, Long> {

    /**
     * Find all locations
     *
     * @return The locations if found, null otherwise.
     */
    List<Location> findAll();

    /**
     * Find a location with the specified location number.
     *
     * @param locationNumber
     * @return The location if found, null otherwise.
     */
    Location findByNumber(String locationNumber);

    /**
     * Find locations whose owner country contains the specified string
     *
     * @param country Any alphabetic string.
     * @return The list of matching locations - always non-null, but may be
     * empty.
     */
    List<Location> findByCountryContainingIgnoreCase(String country);

    /**
     * Find locations whose owner city contains the specified string
     *
     * @param city Any alphabetic string.
     * @return The list of matching locations - always non-null, but may be
     * empty.
     */
    List<Location> findByCityContainingIgnoreCase(String city);

    /**
     * Fetch the number of locations known to the system.
     *
     * @return The number of locations.
     */
    @Query("SELECT count(*) from Location")
    int countLocations();
}

