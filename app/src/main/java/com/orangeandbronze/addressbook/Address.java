package com.orangeandbronze.addressbook;

import static org.apache.commons.lang3.Validate.*;

public class Address {

    private final String streetAddress;
    private final String city;
    private final int zip;

    public Address(String streetAddress, String city, int zip) {
        notBlank(streetAddress);
        notBlank(city);
        isTrue(zip > 0);
        this.streetAddress = streetAddress;
        this.city = city;
        this.zip = zip;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public int getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return streetAddress + " " + city + " " + zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (zip != address.zip) return false;
        if (streetAddress != null ? !streetAddress.equals(address.streetAddress) : address.streetAddress != null)
            return false;
        return city != null ? city.equals(address.city) : address.city == null;
    }

    @Override
    public int hashCode() {
        int result = streetAddress != null ? streetAddress.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + zip;
        return result;
    }
}
