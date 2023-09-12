package com.orangeandbronze.addressbook;

import java.util.*;

public interface AddressBook {

    /**
     * Returns the number of entries in the address book.
     **/
    int getSize();

    /** Ruturns the address matching the customer ID. Returns null if none found. **/
    Address getAddressById(int customerId);


    /**
     * Returns all Customer IDs with the same address.
     **/
    Collection<Integer> getCustomerIdsByAddress(Address address);

    /**
     * Attempts to add a new Customer ID to the address book.
     * If the Customer ID already exists, does not proceed with the addition and returns false.
     * Otherwise, adds the Customer ID and address to the phone book and then returns true.
     */
    boolean add(int customerId, Address address);

    /** Removes the entry with the matching Customer ID from the address book.
     * If no matching entry exists, does nothing. */
    void remove(int customerId);

    /** If a matching Customer ID is present, changes the existing address with the new address, & returns true.
     * Otherwise, does nothing and returns false.
     */
    boolean update(int customerId, Address newAddress);
}
