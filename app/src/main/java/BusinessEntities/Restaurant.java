package BusinessEntities;

import java.util.ArrayList;

public class Restaurant {

    private int id;
    private int numOfBranches;

    private String name;
    private ArrayList<Branch> branches;

    public void setId(int id) {
        this.id = id;
    }

    public void setNumOfBranches(int numOfBranches) {
        this.numOfBranches = numOfBranches;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBranches(ArrayList<Branch> branches) {
        this.branches = branches;
    }

    public Restaurant(int id, int numOfBranches, String name, ArrayList<Branch> branches) {
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

    public ArrayList<Branch> getBranches() {
        return branches;
    }
}
