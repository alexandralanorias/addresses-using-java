# Address Book

The objective is to match Customer IDs, which are `int`s, with `Address` instances.

You will have an `AddressBook` interface, and two implementations of that interface - `AddressBookListImpl` &  `AddressBookMapImpl`. You will modify only the implementation (`...Impl`) classes. **Do not modify the `AddressBook` interface**.

The two implementation (`...Impl`) classes have empty method bodies. You need to implement those method bodies in order for the classes to work. Refer to the comments on the required implementation for each method. 

### `AddressBookListImpl`

* Contains two `List` fields, one containing the Customer IDs, & the other containing the `Address` objects.

### `AddressBookMapImpl`

* Contains a `Map` field that will contain the Customer IDs as keys, & `Address` objects as values. 

## Testing

A JUnit test class has been provided to test your work. You don't need to know anything about JUnit, just run it and check if any tests failed. 

### Testing on Eclipse

Right-click the `src/test/java` directory, then "Run As", then "JUnit Test". A green bar means all tests pass. A red bar means one or more tests failed - check the output to see what failed.

### Testing on the Command Line

Open a terminal on the project root directory. Run the command "`gradle test`".

### Testing on IntelliJ IDEA

Click the `test` directory, then press Ctrl+Shift+F10.
