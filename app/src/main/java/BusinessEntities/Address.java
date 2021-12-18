package BusinessEntities;

import com.google.firebase.firestore.PropertyName;

public class Address {

    private String city;
    private String street;

    @PropertyName("building_number") // Presented in the database with this name
    private int buildingNumber;

    public Address() {
        // Empty constructor is required by Firebase method .toObject()
    }

    public Address(String city, String street, int buildingNumber) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    @PropertyName("building_number")
    public int getBuildingNumber() {
        return buildingNumber;
    }

    @Override
    public String toString() {
        return city + '\'' +
                ", " + street + '\'' +
                ", " + buildingNumber + '\'';
    }
}
