import java.util.List;
import java.util.ArrayList;
/**
 * The supplied code assumes use of external library to convert json file into java array/list of POJOs
 * */
public class PrettyPrintAddress {
   
    public String prettyPrintAddress(Address address) {
        // Output the address, using String.format, the %s means a String value will replace this placeholder
        AddressValidity addressValidity = getAddressValidity(address);
        if(addressValidity.isValid == false) {
            System.out.println("The address provided is invalid for reasons below:");
            for(String message: addressValidity.messages)
                System.out.println(message);
        } else {
            System.out.println(
                String.format("Type: %s - %s - %s - %d – %s"),
                address.getAddressLineDetail().line1 + address.getAddressLineDetail().line2,
                address.cityOrTown,
                address.provinceOrState,// we can create getter methods for provinceOrState, e.g getArovinceOrState()
                address.postalCode);
        }
    }
   
    /**
     * Determine validity of address, if not valid attach messages of why it is not valid
     * */
    public AddressValidity getAddressValidity(Address address) {
        // Try to convert postal code from String to int, if it doesn't throw a NumberFormatException then it is an int, then continue with other validations
        AddressValidity addressValidity = new AddressValidity();
        try {
            int postalCode = Integer.parseInt(postalCode);
        } catch (NumberFormatException e) {
            addressValidity.isValid = false;
            addressValidity.messages.add("Postal code is invalid, it should be numeric");
        }
        // If address country code is null or blank, address is invalid
        if(address.country.code == null address.country.code.isEmpty()) {
            addressValidity.isValid = false;
            addressValidity.messages.add("Country for address must be specified");
        }
        // If country is ZA and province is null
        if(address.country.code.equals("ZA") && address.provinceOrState.code == null || address.provinceOrState.code.isEmpty()) {
            addressValidity.isValid = false;
            addressValidity.messages.add("Provice must be specified for addresses from ZA");
        }
        return addressValidity;
    }
   
    /*print all addresses*/
    public String printAllAddresses(Address[] addresses) {
        for(Address address: addresses) {
            prettyPrintAddress(address);
        }
    }
   
    /**
     * This function prints an array of addresses by its type
     * e.g printAllAddresses(addresses, AddressType.PHYSICAL_ADDRESS)
     * */
    public String printAddressesByType(Address[] addresses, AddressType addressType) {
        for(Address address: addresses) {
            if(address.addressType == addressType) {
                prettyPrintAddress(address);
            }
        }
    }
   
    /**
     * In here we process the json file we got, to convert to java POJO
     * using an external library, we can have a for loop for each address
     * */
    public Address readAddressFromFile(File file) {
       
        // An example of attaching values from the file to a POJO
        String line1 = "Address 1";
        String line2 = "Line 2";
        Address address = new Address(
            1,
            "1234",
            AddressType.getByCode(1),
            new AddressLineDetail(line1, line2),
            "City 1",
            new ProviceOrState("5", "Eastern Cape"),
            new Country("ZA", "South Africa")
            );
    }
   
}

// POJO to store our Address object
class Address {
    int id;
    String postalCode;
    AddressType addressType;
    AddressLineDetail addressLineDetail;
    String cityOrTown;
    ProvinceOrState provinceOrState;
    Country country;
   
    public Address(int id, String postalCode, AddressType addressType, AddressLineDetail addressLineDetail, String cityOrTown, ProvinceOrState provinceOrState, Country country) {
        this.id = id;
        this.postalCode = postalCode;
        this.addressType = addressType;
        this.addressLineDetail = addressLineDetail;
        this.cityOrTown = cityOrTown;
        this.provinceOrState = provinceOrState;
        this.country = country;
    }
   
    public AddressLineDetail getAddressLineDetail() {
        return this.addressLineDetail;
    }
}

// POJO to store address line
class AddressLineDetail {
   
    String line1;
    String line2;
   
    public AddressLineDetail(String line1, String line1) {
        this.line1 = line1;
        this.line2 = line2;
    }
}

// POJO to store country
class Country {
    String code;
    String name;
   
    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }
}

class ProvinceOrState {
    int code;
    String name;
   
    public ProvinceOrState(String code, String name) {
        this.code = code;
        this.name = name;
    }
}

// ENUM to store address types, using ENUM because the list of address types is known and constant
enum AddressType {
    PHYSICAL_ADDRESS(1, "Physical Address")
    POSTAL_ADDRESS(2, "Postal Address")
    BUSINESS_ADDRESS(5, "Business Address")
   
    int code;
    String name;
    AddressType(int code, String name) {
        this.code = code;
        this.name = name;
    }
   
    public static AddressType getByCode(int code) {
        for (AddressType at : values()) {
            if (at.code == code) {
                return at;
            }
        }
        return null;
    }
}

class AddressValidity {
    boolean isValid = true;
    List<String> messages = new ArrayList<>();
}