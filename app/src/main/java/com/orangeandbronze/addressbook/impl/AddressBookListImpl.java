// Modified by Alexandra Lanorias
// February 18, 2024

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

    // DONE
    @Override
    public int getSize() {
        return this.addresses.size();
    }

    /**
     * Returns the address matching the customer ID.  Returns null if none found.
     **/

    // DONE
    @Override
    public Address getAddressById(int customerId) {
        int index = this.customerIds.indexOf(customerId);

        if (index != -1 && index < this.addresses.size()) {
            return this.addresses.get(index);
        } 
        
        else {
            return null;
        }
    }



    /**
     * Returns all Customer IDs with the same address
     */

    // DONE
    @Override
    public Collection<Integer> getCustomerIdsByAddress(final Address address) {
        Collection<Integer> customerIdsWithSameAddress = new ArrayList<>();

        for (int i = 0; i < this.addresses.size(); i++) {
            if (address.equals(this.addresses.get(i))) {
                customerIdsWithSameAddress.add(this.customerIds.get(i));
            }
        }

        return customerIdsWithSameAddress;
    }

    /**
     * Attempts to add a new Customer ID to the address book.
     * If the Customer ID already exists, does not proceed with the addition and returns false.
     * Otherwise, adds the Customer ID and address to the phone book and then returns true.
     */

    // DONE
    @Override
    public boolean add(int customerId, Address address) {
        if (!this.customerIds.contains(customerId)) {
            this.customerIds.add(customerId);
            this.addresses.add(address);
            return true;
        }

        return false;
    }

    /**
     * Removes the entry with the matching Customer ID from the address book.
     * If no matching entry exists, does nothing.
     */

    // DONE
    @Override
    public void remove(int customerId) {
        int index = this.customerIds.indexOf(customerId);

        if (index != -1) {
            this.customerIds.remove(index);
            this.addresses.remove(index);
        }
    }

    /**
     * If a matching Customer ID is present, changes the existing address with the new address, & returns true.
     * Otherwise, does nothing and returns false.
     */

    // DONE
    @Override
    public boolean update(int customerId, Address newAddress) {
        int index = this.customerIds.indexOf(customerId);

        if (index != -1) {
            this.addresses.set(index, newAddress);
            return true;
        }

        return false;
    }
}
