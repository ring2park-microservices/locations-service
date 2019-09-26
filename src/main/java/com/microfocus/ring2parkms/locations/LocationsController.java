package com.microfocus.ring2parkms.locations;

import com.microfocus.ring2parkms.locations.exceptions.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * A RESTFul controller for accessing location information.
 *
 * @author Kevin A. Lee
 */
@RestController
public class LocationsController {

    protected Logger logger = Logger.getLogger(LocationsController.class
            .getName());
    protected LocationRepository locationRepository;

    /**
     * Create an instance plugging in the respository of Accounts.
     *
     * @param locationRepository An location repository implementation.
     */
    @Autowired
    public LocationsController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;

        logger.info("LocationRepository says system has "
                + locationRepository.countLocations() + " locations");
    }

    /**
     * Fetch all locations.
     *
     * @return A non-null, non-empty set of locations.
     * @throws LocationNotFoundException If there are no matches at all.
     */
    @RequestMapping("/locations")
    public List<Location> all() {
        logger.info("locations-service all() invoked: "
                + locationRepository.getClass().getName());

        List<Location> locations = locationRepository
                .findAll();
        logger.info("locations-service all() found: " + locations);

        if (locations == null || locations.size() == 0)
            throw new LocationNotFoundException("all");
        else {
            return locations;
        }
    }

    /**
     * Fetch an location with the specified location number.
     *
     * @param locationNumber A numeric, 9 digit location number.
     * @return The location if found.
     * @throws LocationNotFoundException If the Id is not recognised.
     */
    @RequestMapping("/location/{locationNumber}")
    public Location byNumber(@PathVariable("locationNumber") String locationNumber) {

        logger.info("locations-service byNumber() invoked: " + locationNumber);
        Location location = locationRepository.findByNumber(locationNumber);
        logger.info("locations-service byNumber() found: " + location);

        if (location == null)
            throw new LocationNotFoundException(locationNumber);
        else {
            return location;
        }
    }

    /**
     * Fetch locations with the specified country. A partial case-insensitive match
     * is supported. So <code>http://.../locations/country/a</code> will find any
     * locations with upper or lower case 'a' in their country.
     *
     * @param partialCountry
     * @return A non-null, non-empty set of locations.
     * @throws LocationNotFoundException If there are no matches at all.
     */
    @RequestMapping("/locations/country/{name}")
    public List<Location> byCountry(@PathVariable("name") String partialCountry) {
        logger.info("locations-service byCountry() invoked: "
                + locationRepository.getClass().getName() + " for "
                + partialCountry);

        List<Location> locations = locationRepository
                .findByCountryContainingIgnoreCase(partialCountry);
        logger.info("locations-service byCountry() found: " + locations);

        if (locations == null || locations.size() == 0)
            throw new LocationNotFoundException(partialCountry);
        else {
            return locations;
        }
    }

    /**
     * Fetch locations with the specified city. A partial case-insensitive match
     * is supported. So <code>http://.../locations/city/a</code> will find any
     * locations with upper or lower case 'a' in their city.
     *
     * @param partialCity
     * @return A non-null, non-empty set of locations.
     * @throws LocationNotFoundException If there are no matches at all.
     */
    @RequestMapping("/locations/city/{name}")
    public List<Location> byCity(@PathVariable("name") String partialCity) {
        logger.info("locations-service byCity() invoked: "
                + locationRepository.getClass().getName() + " for "
                + partialCity);

        List<Location> locations = locationRepository
                .findByCityContainingIgnoreCase(partialCity);
        logger.info("locations-service byCity() found: " + locations);

        if (locations == null || locations.size() == 0)
            throw new LocationNotFoundException(partialCity);
        else {
            return locations;
        }
    }
}

