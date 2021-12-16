package BusinessEntities;

public class Address {

    private String city;
    private String street;
    private String buildingNumber;

    public Address(String city, String street, String buildingNumber) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    @Override
    public String toString() {
        return city + '\'' +
                ", " + street + '\'' +
                ", " + buildingNumber + '\'';
    }
}
