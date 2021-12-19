package BusinessEntities;

import com.google.firebase.firestore.DocumentId;

import java.util.List;

public class Restaurant {

    @DocumentId
    private String docId;
    private String name;
    private List<Branch> branches;


    public Restaurant() {
        // Empty constructor required by Firebase method .toObject()
    }

    public Restaurant(String name, List<Branch> branches) {
        this.name = name;
        this.branches = branches;
    }


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

    public String getDocId() { return docId; }
}
