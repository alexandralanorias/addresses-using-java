package com.orangeandbronze.addressbook.impl;

import com.orangeandbronze.addressbook.*;

import java.util.*;

import static org.apache.commons.lang3.Validate.*;

public class AddressBookMapImpl implements AddressBook {

    private final Map<Integer, Address> map = new HashMap<>();

    public AddressBookMapImpl(Map<Integer, Address> addressBookMap) {
        notNull(addressBookMap);
        this.map.putAll(addressBookMap);
        this.map.remove(null); // remove null keys if present
    }

    public AddressBookMapImpl() {
        super();
    }

    /**
     * Returns the number of entries in the address book.
     **/
    @Override
    public int getSize() {
        return 0;
    }

    /**
     * Ruturns the address matching the customer ID.  Returns null if none found.
     **/
    @Override
    public Address getAddressById(int customerId) {
        return null;
    }

    /**
     * Returns all Customer IDs with the same address
     */
    @Override
    public Collection<Integer> getCustomerIdsByAddress(Address address) {
        return null;
    }

    /**
     * Attempts to add a new Customer ID to the address book.
     * If the Customer ID already exists, does not proceed with the addition and returns false.
     * Otherwise, adds the Customer ID and address to the phone book and then returns true.
     */
    @Override
    public boolean add(int customerId, Address address) {
        return false;
    }

    /**
     * Removes the entry with the matching Customer ID from the address book.
     * If no matching entry exists, does nothing.
     */
    @Override
    public void remove(int customerId) {
        map.remove(customerId);
    }

    /**
     * If a matching Customer ID is present, changes the existing address with the new address, & returns true.
     * Otherwise, does nothing and returns false.
     */
    @Override
    public boolean update(int customerId, Address newAddress) {
        return false;
    }
}
