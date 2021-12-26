package BusinessEntities;

import com.google.firebase.firestore.DocumentId;

import java.util.ArrayList;
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

    public Restaurant(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public List<Branch> getBranches() {return this.branches;}

    public void addBranch(Branch b) {
        if (null == branches)
            this.branches = new ArrayList<>();
        this.branches.add(b);
    }

    @Override
    public String toString() {
        return "Restaurant{\n" +
                "name='" + name + '\'' +
                ", branches=" + branches +
                "\n}";
    }

    public String getDocId() { return docId; }
}
