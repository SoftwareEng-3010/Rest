package BusinessEntities;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Restaurant {

    // private fields
    private int id;
    private int numOfBranches;
    private String name;
    private ArrayList<Branch> branches;

    // empty constructor for deserializing Firestore document
    public Restaurant(){}

    public Restaurant(int id, int numOfBranches, String name, ArrayList<Branch> branches) {
        this.id = id;
        this.numOfBranches = numOfBranches;
        this.name = name;
        this.branches = branches;
    }


    // setters & getters
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


    // A String representation of a Restaurant object
    @Override
    public String toString(){
        String res = "";
        res += "Restaurant id: " + this.id + '\n' +
                "Restaurant name: " + this.name + '\n' +
                "This restaurant has " + this.numOfBranches + " branches: \n";

        for(Branch branch : branches){
            res += "\t" + branch.toString();
        }
        return res;
    }
}
