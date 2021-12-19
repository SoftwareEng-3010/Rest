package BusinessEntities;

import java.util.List;

public class Restaurant {

//    private int id;
    private String name;
    private List<Branch> branches;
//    public static final String FIELD_ID = "restaurant_id";
//    public static final String FIELD_NAME = "name";
//    public static final String FIELD_BRANCHES = "branches";
    // Other fields will be added

    public Restaurant() {
        // Empty constructor required by Firebase method .toObject()
    }

    public Restaurant(String name, List<Branch> branches) {
//        this.id = id;
        this.name = name;
        this.branches = branches;
    }

//    public String getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public List<Branch> getBranches() {return this.branches;}

    @Override
    public String toString() {
        return "Restaurant{\n" +
                "name='" + name + '\'' +
                ", branches=" + branches +
                "\n}";
    }
}
