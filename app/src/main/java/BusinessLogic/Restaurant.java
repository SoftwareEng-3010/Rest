package BusinessLogic;

import java.util.ArrayList;

public class Restaurant {

    private int id;
    private int numOfBranches;

    private String name;
    private ArrayList<RestBranch> branches;

    public Restaurant(int id, int numOfBranches, String name, ArrayList<RestBranch> branches) {
        this.id = id;
        this.numOfBranches = numOfBranches;
        this.name = name;
        this.branches = branches;
    }

    public int getId() {
        return id;
    }

    public int getNumOfBranches() {
        return numOfBranches;
    }

    public String getName() {
        return name;
    }

    public ArrayList<RestBranch> getBranches() {
        return branches;
    }
}
