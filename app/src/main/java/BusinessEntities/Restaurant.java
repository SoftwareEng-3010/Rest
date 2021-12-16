package BusinessEntities;

import java.util.List;

public class Restaurant {

    // Private fields
    private int id;
    private String name;
    // Other fields will be added


    public Restaurant(int id, String name, List<Branch> branches) {
        this.id = id;
        this.name = name;
    }


    // setters & getters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
