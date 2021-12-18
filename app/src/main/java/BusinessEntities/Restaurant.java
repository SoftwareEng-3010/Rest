package BusinessEntities;

import java.util.List;

public class Restaurant {

    private int id;
    private String name;
    private List<Branch> branches;
    // Other fields will be added

    public Restaurant() {
        // Empty constructor required by Firebase method .toObject()
    }

    public Restaurant(int id, String name, List<Branch> branches) {
        this.id = id;
        this.name = name;
        this.branches = branches;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Branch> getBranches() {return this.branches;}

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", branches=" + branches +
                '}';
    }
}
