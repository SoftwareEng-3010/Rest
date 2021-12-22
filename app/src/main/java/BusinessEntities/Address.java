package BusinessEntities;

import android.util.Log;

import com.google.firebase.firestore.PropertyName;

import org.json.JSONException;
import org.json.JSONObject;

public class Address {

    private final String TAG = "Address";

    public static final int FIELD_CITY = 0;
    public static final int FIELD_STREET = 1;
    public static final int FIELD_BUILDING_NUMBER = 2;

    private String city;
    private String street;
    private String buildingNumber;

    // Empty constructor is required by Firebase method .toObject()
    public Address() {

    }

    public Address(String city, String street, String buildingNumber) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public Address(String addressAsString) {
        Log.e(TAG + "Constructor(Str)", addressAsString);

        try {
            String[] fields = addressAsString.split(",");

            this.city = fields[FIELD_CITY].trim();
            this.street = fields[FIELD_STREET].trim();
            this.buildingNumber = fields[FIELD_BUILDING_NUMBER].trim();
        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public Address(JSONObject json) {
        initFromJSONString(json.toString());
    }


    public Address(Address other) {
        this.city = other.city;
        this.street = other.street;
        this.buildingNumber = other.buildingNumber;

    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    @Override
    public String toString() {
        return city +
                ", " + street +
                " " + buildingNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return buildingNumber.equals(address.buildingNumber) && city.equals(address.city) && street.equals(address.street);
    }

    private void initFromJSONString(String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);
            Log.e(TAG, "Initializing Address with JSON: " + jsonString);
            this.city = json.getString("city");
            this.street = json.getString("street");
            this.buildingNumber = json.getString("building_number");
        }
        catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
