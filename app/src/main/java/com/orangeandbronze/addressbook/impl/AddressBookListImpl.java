package com.orangeandbronze.addressbook.impl;

import com.orangeandbronze.addressbook.*;

import java.util.*;

import static org.apache.commons.lang3.Validate.*;

public class AddressBookListImpl implements AddressBook {

    private final List<Integer> customerIds = new ArrayList<>();
    private final List<Address> addresses = new ArrayList<>();

    public AddressBookListImpl(Collection<Integer> customerIds, Collection<Address> addresses) {
        this.customerIds.addAll(customerIds);
        while (this.customerIds.remove(null)) ; // remove nulls
        checkForDuplicateCustomerIds();
        this.addresses.addAll(addresses);
        while (this.addresses.remove(null)) ; // remove nulls
        isTrue(this.customerIds.size() == this.addresses.size(), "The two collections need to be the same size. customerIds size: " + this.customerIds.size() + " addresses size: " + this.addresses.size());
    }

    private void checkForDuplicateCustomerIds() {
        for (int i = 0; i < this.customerIds.size(); i++) {
            final int customerId = this.customerIds.get(i);
            final int frequency = Collections.frequency(this.customerIds, customerId);
            if (frequency > 1) {
                throw new IllegalStateException("customerIds cannot contain duplicates; customerId " + customerId + " has " + frequency + " occurrences" );
            }
        }
    }

    public AddressBookListImpl() {
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
    public Collection<Integer> getCustomerIdsByAddress(final Address address) {
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
