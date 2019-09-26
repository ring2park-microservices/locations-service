package com.microfocus.ring2parkms.locations.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Allow the controller to return a 404 if a location is not found by simply
 * throwing this exception. The @ResponseStatus causes Spring MVC to return a
 * 404 instead of the usual 500.
 *
 * @author Kevin A. Lee
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LocationNotFoundException(String locationNumber) {
        super("No such location: " + locationNumber);
    }
}
