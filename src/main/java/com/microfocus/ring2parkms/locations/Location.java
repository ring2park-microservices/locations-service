package com.microfocus.ring2parkms.locations;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Parking Location where a User may make Bookings.
 *
 * @author Kevin A. Lee
 */
@Entity
@Table(name = "T_LOCATION")
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    public static Long nextId = 0L;

    @Id
    private Long id;

    @NotBlank(message = "A location number must be provided.")
    private String number;

    @NotBlank(message = "A location name must be provided.")
    private String name;

    @NotBlank(message = "A location address must be provided.")
    private String address;

    @NotBlank(message = "A location city must be provided.")
    private String city;

    @NotBlank(message = "A location state must be provided.")
    private String state;

    @NotBlank(message = "A location Zip must be provided.")
    private String zip;

    @NotBlank(message = "A Location country must be provided.")
    private String country;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    @Column(precision = 4, scale = 2)
    private BigDecimal price;

    @Size(max = 3)
    private String currency;

    public Location() {
        id = getNextId();
        this.currency = "USD";
    }

    public Location(BigDecimal price, String number, String currency,
                    String name, String address, String city, String state,
                    String zip, String country) {
        id = getNextId();
        this.price = price;
        this.number = number;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.currency = currency;
    }

    /**
     * This is a very simple, and non-scalable solution to generating unique
     * ids. Not recommended for a real application. Consider using the
     * <tt>@GeneratedValue</tt> annotation and a sequence to generate ids.
     *
     * @return The next available id.
     */
    protected static Long getNextId() {
        synchronized (nextId) {
            return nextId++;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Location(" + name + "," + address + "," + city + "," + zip + ")";
    }

}


