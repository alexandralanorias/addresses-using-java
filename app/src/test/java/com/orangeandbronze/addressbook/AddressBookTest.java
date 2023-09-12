package com.orangeandbronze.addressbook;

import com.orangeandbronze.addressbook.impl.*;
import org.hamcrest.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    private final static Address ADD1 = new Address("1 Ayala Ave", "Makati", 123);
    private final static Address ADD2 = new Address("2 Ayala Ave", "Makati", 123);
    private final static Address ADD3 = new Address("1 Quezon Ave", "Quezon City", 234);
    private final static Address ADD4 = new Address("1 Roxas Blvd", "Manila", 345);

    @Test
    void getSize_3_elements() {
        final AddressBook listImpl = new AddressBookListImpl(List.of(1, 2, 3), List.of(ADD1, ADD2, ADD3));
        final AddressBook mapImpl = new AddressBookMapImpl(Map.of(1, ADD1, 2, ADD2, 3, ADD3));
        final int EXPECTED_SIZE = 3;
        assertAll(
                () -> assertEquals(EXPECTED_SIZE, listImpl.getSize(), "AddressBookListImpl"),
                () -> assertEquals(EXPECTED_SIZE, mapImpl.getSize(), "AddressBookMapImpl")
        );
    }

    @Test
    void newAddressBookListImpl_duplicate_keys() {
        assertThrows(Exception.class, () -> new AddressBookListImpl(List.of(1, 2, 1), List.of(ADD1, ADD2, ADD3)));
    }

    @Test
    void newAddressBookListImpl_mismatch_list_sizes() {
        assertAll(
                () -> assertThrows(Exception.class, () -> new AddressBookListImpl(List.of(1, 2), List.of(ADD1, ADD2, ADD3)), "customerIds fewer than addresses"),
                () -> assertThrows(Exception.class, () -> new AddressBookListImpl(List.of(1, 2, 3), List.of(ADD1, ADD2)), "customerIds more than addresses")
        );
    }

    @Test
    void getAddressById_after_fresh_init() {
        final AddressBook listImpl = new AddressBookListImpl(List.of(2, 3, 1), List.of(ADD2, ADD3, ADD1));
        final AddressBook mapImpl = new AddressBookMapImpl(Map.of(2, ADD2, 3, ADD3, 1, ADD1));
        final int PARAM = 2;
        final Address EXPECTED = ADD2;
        assertAll(
                () -> assertEquals(EXPECTED, listImpl.getAddressById(PARAM), "AddressBookListImpl"),
                () -> assertEquals(EXPECTED, mapImpl.getAddressById(PARAM), "AddressBookMapImpl")
        );
    }

    @Test
    void getCustomerIdsByAddress_has_duplicate_addresses() {
        // Customers 1 & 4 have the same address
        final AddressBook listImpl = new AddressBookListImpl(List.of(2, 3, 1, 4), List.of(ADD2, ADD3, ADD1, ADD1));
        final AddressBook mapImpl = new AddressBookMapImpl(Map.of(2, ADD2, 3, ADD3, 1, ADD1, 4, ADD1));
        final Matcher MATCHER = containsInAnyOrder(1, 4);
        assertAll(
                () -> assertThat(listImpl.getCustomerIdsByAddress(ADD1), MATCHER),
                () -> assertThat(mapImpl.getCustomerIdsByAddress(ADD1), MATCHER)
        );
    }

    @Test
    void add_to_addressbook_with_entries_but_no_matching_customerId() {
        final AddressBook listImpl = new AddressBookListImpl(List.of(2, 3, 1), List.of(ADD2, ADD3, ADD1));
        final AddressBook mapImpl = new AddressBookMapImpl(Map.of(2, ADD2, 3, ADD3, 1, ADD1));
        final int CUSTOMER_ID = 5;
        final int EXPECTED_SIZE = 4;
        final Matcher MATCHER = containsInAnyOrder(1, CUSTOMER_ID);
        assertAll(
                () -> assertTrue(listImpl.add(CUSTOMER_ID, ADD1) , "AddressBookListImpl"),
                () -> assertEquals(EXPECTED_SIZE, listImpl.getSize(), "AddressBookListImpl"),
                () -> assertEquals(ADD1, listImpl.getAddressById(CUSTOMER_ID), "AddressBookListImpl"),
                () -> assertThat("AddressBookListImpl", listImpl.getCustomerIdsByAddress(ADD1), MATCHER),
                () -> assertTrue(mapImpl.add(CUSTOMER_ID, ADD1), "AddressBookMapImpl"),
                () -> assertEquals(EXPECTED_SIZE, mapImpl.getSize(), "AddressBookMapImpl"),
                () -> assertEquals(ADD1, mapImpl.getAddressById(CUSTOMER_ID), "AddressBookMapImpl"),
                () -> assertThat("AddressBookMapImpl", mapImpl.getCustomerIdsByAddress(ADD1), MATCHER)
        );
    }

    @Test
    void add_to_empty_addressbook() {
        final AddressBook listImpl = new AddressBookListImpl();
        final AddressBook mapImpl = new AddressBookMapImpl();
        final int CUSTOMER_ID = 1;
        assertAll(
                () -> assertTrue(listImpl.add(CUSTOMER_ID, ADD1) , "AddressBookListImpl"),
                () -> assertEquals(1, listImpl.getSize(), "AddressBookListImpl"),
                () -> assertEquals(ADD1, listImpl.getAddressById(CUSTOMER_ID), "AddressBookListImpl"),
                () -> assertThat("AddressBookListImpl", listImpl.getCustomerIdsByAddress(ADD1), containsInAnyOrder(CUSTOMER_ID)),
                () -> assertTrue(mapImpl.add(CUSTOMER_ID, ADD1), "AddressBookMapImpl"),
                () -> assertEquals(1, mapImpl.getSize(), "AddressBookMapImpl"),
                () -> assertEquals(ADD1, mapImpl.getAddressById(CUSTOMER_ID), "AddressBookMapImpl"),
                () -> assertThat("AddressBookMapImpl", mapImpl.getCustomerIdsByAddress(ADD1), containsInAnyOrder(CUSTOMER_ID))
        );
    }

    @Test
    void add_to_addressbook_with_entries_but_has_duplicate_customerId() {
        final AddressBook listImpl = new AddressBookListImpl(List.of(2, 3, 1), List.of(ADD2, ADD3, ADD1));
        final AddressBook mapImpl = new AddressBookMapImpl(Map.of(2, ADD2, 3, ADD3, 1, ADD1));
        final int CUSTOMER_ID = 2;
        final int EXPECTED_SIZE = 3;
        assertAll(
                () -> assertFalse(listImpl.add(CUSTOMER_ID, ADD1), "AddressBookListImpl"),
                () -> assertEquals(EXPECTED_SIZE, listImpl.getSize(), "AddressBookListImpl"),
                () -> assertEquals(ADD2, listImpl.getAddressById(CUSTOMER_ID), "AddressBookListImpl"),
                () -> assertThat("AddressBookListImpl", listImpl.getCustomerIdsByAddress(ADD2), containsInAnyOrder(CUSTOMER_ID)),
                () -> assertFalse(mapImpl.add(CUSTOMER_ID, ADD1), "AddressBookMapImpl"),
                () -> assertEquals(EXPECTED_SIZE, mapImpl.getSize(), "AddressBookMapImpl"),
                () -> assertEquals(ADD2, mapImpl.getAddressById(CUSTOMER_ID), "AddressBookMapImpl"),
                () -> assertThat("AddressBookMapImpl", mapImpl.getCustomerIdsByAddress(ADD2), containsInAnyOrder(CUSTOMER_ID))
        );
    }

    @Test
    void remove_existing_entry() {
        final AddressBook listImpl = new AddressBookListImpl(List.of(2, 3, 1), List.of(ADD2, ADD3, ADD1));
        final AddressBook mapImpl = new AddressBookMapImpl(Map.of(2, ADD2, 3, ADD3, 1, ADD1));
        final int CUSTOMER_ID = 2;
        mapImpl.remove(CUSTOMER_ID);
        listImpl.remove(CUSTOMER_ID);
        final int EXPECTED_SIZE = 2;
        assertAll(
                () -> assertEquals(EXPECTED_SIZE, mapImpl.getSize()),
                () -> assertNull(mapImpl.getAddressById(CUSTOMER_ID)),
                () -> assertEquals(EXPECTED_SIZE, listImpl.getSize()),
                () -> assertNull(listImpl.getAddressById(CUSTOMER_ID))
        );
    }

    @Test
    void remove_nonexistent_entry() {
        final AddressBook listImpl = new AddressBookListImpl(List.of(2, 3, 1), List.of(ADD2, ADD3, ADD1));
        final AddressBook mapImpl = new AddressBookMapImpl(Map.of(2, ADD2, 3, ADD3, 1, ADD1));
        final int CUSTOMER_ID = 5;
        mapImpl.remove(CUSTOMER_ID);
        listImpl.remove(CUSTOMER_ID);
        final int EXPECTED_SIZE = 3;
        assertAll(
                () -> assertEquals(EXPECTED_SIZE, mapImpl.getSize()),
                () -> assertNull(mapImpl.getAddressById(CUSTOMER_ID)),
                () -> assertEquals(EXPECTED_SIZE, listImpl.getSize()),
                () -> assertNull(listImpl.getAddressById(CUSTOMER_ID))
        );
    }

    @Test
    void update_existing_entry() {
        final AddressBook listImpl = new AddressBookListImpl(List.of(2, 3, 1), List.of(ADD2, ADD3, ADD1));
        final AddressBook mapImpl = new AddressBookMapImpl(Map.of(2, ADD2, 3, ADD3, 1, ADD1));
        final int CUSTOMER_ID = 2;
        final int EXPECTED_SIZE = 3;
        assertAll(
                () -> assertTrue(mapImpl.update(CUSTOMER_ID, ADD4), "AddressBookMapImpl"),
                () -> assertEquals(EXPECTED_SIZE, mapImpl.getSize(), "AddressBookMapImpl"),
                () -> assertEquals(ADD4, mapImpl.getAddressById(CUSTOMER_ID), "AddressBookMapImpl"),
                () -> assertTrue(listImpl.update(CUSTOMER_ID, ADD4), "AddressBookListImpl"),
                () -> assertEquals(EXPECTED_SIZE, listImpl.getSize(), "AddressBookListImpl"),
                () -> assertEquals(ADD4, listImpl.getAddressById(CUSTOMER_ID), "AddressBookListImpl")
        );
    }

    @Test
    void update_nonexisting_entry() {
        final AddressBook listImpl = new AddressBookListImpl(List.of(2, 3, 1), List.of(ADD2, ADD3, ADD1));
        final AddressBook mapImpl = new AddressBookMapImpl(Map.of(2, ADD2, 3, ADD3, 1, ADD1));
        final int CUSTOMER_ID = 4;
        final int EXPECTED_SIZE = 3;
        assertAll(
                () -> assertFalse(mapImpl.update(CUSTOMER_ID, ADD4), "AddressBookMapImpl"),
                () -> assertEquals(EXPECTED_SIZE, mapImpl.getSize(), "AddressBookMapImpl"),
                () -> assertNull( mapImpl.getAddressById(CUSTOMER_ID), "AddressBookMapImpl"),
                () -> assertFalse(listImpl.update(CUSTOMER_ID, ADD4), "AddressBookListImpl"),
                () -> assertEquals(EXPECTED_SIZE, listImpl.getSize(), "AddressBookListImpl"),
                () -> assertNull( listImpl.getAddressById(CUSTOMER_ID), "AddressBookListImpl")
        );
    }


}
