package BusinessEntities;

import com.google.firebase.firestore.PropertyName;

public class Item {

    @PropertyName("description")
    private String description;
    private String name;
    private String serviceUnit;
    private double price;
    private String imageURL;
    private boolean inStock;

    public Item(){
        // Empty constructor required by Firebase method .toObject()
    }

    public Item(String name,String description, String imageURL, String serviceUnit,
                boolean inStock, double price) {
        this.description = description;
        this.imageURL = imageURL;
        this.name = name;
        this.serviceUnit = serviceUnit;
        this.inStock = inStock;
        this.price = price;
    }

    @PropertyName("description")
    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getServiceUnit() {
        return serviceUnit;
    }

    public boolean isInStock() {
        return inStock;
    }

    public double getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name + '\t' + price;
    }
}
