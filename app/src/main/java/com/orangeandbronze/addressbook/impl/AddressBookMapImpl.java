// Modified by Alexandra Lanorias
// February 18, 2024

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

    // DONE
    @Override
    public int getSize() {
        return this.map.size();
    }

    /**
     * Ruturns the address matching the customer ID.  Returns null if none found.
     **/

    // DONE
    @Override
    public Address getAddressById(int customerId) {
        return this.map.get(customerId);
    }

    /**
     * Returns all Customer IDs with the same address
     */

    // DONE
    @Override
    public Collection<Integer> getCustomerIdsByAddress(Address address) {
        Collection<Integer> customerIdsWithSameAddress = new ArrayList<>();
        for (Map.Entry<Integer, Address> entry : this.map.entrySet()) {
            if (address.equals(entry.getValue())) {
                customerIdsWithSameAddress.add(entry.getKey());
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
        if (!this.map.containsKey(customerId)) {
            this.map.put(customerId, address);
            return true;
        }

        return false;
    }

    /**
     * Removes the entry with the matching Customer ID from the address book.
     * If no matching entry exists, does nothing.
     */

    // did not need to change anything???
    @Override
    public void remove(int customerId) {
        this.map.remove(customerId);
    }

    /**
     * If a matching Customer ID is present, changes the existing address with the new address, & returns true.
     * Otherwise, does nothing and returns false.
     */

    // DONE
    @Override
    public boolean update(int customerId, Address newAddress) {
        if (this.map.containsKey(customerId)) {
            this.map.put(customerId, newAddress);
            return true;
        }

        return false;
    }
}
