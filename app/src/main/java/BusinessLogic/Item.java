package BusinessLogic;

public class Item {

    private String description;
    private String imageURL;
    private String name;
    private String serviceUnit;

    private boolean inStock;

    private int price;

    public Item(String description, String imageURL, String name, String serviceUnit, boolean inStock, int price) {
        this.description = description;
        this.imageURL = imageURL;
        this.name = name;
        this.serviceUnit = serviceUnit;
        this.inStock = inStock;
        this.price = price;
    }

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

    public int getPrice() {
        return price;
    }
}
