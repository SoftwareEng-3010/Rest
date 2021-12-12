package BusinessEntities;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Restaurant {

    // private fields
    private int id;
    private String name;
    private ArrayList<Branch> branches;

    // empty constructor for deserializing Firestore documents
    public Restaurant(){}

    public Restaurant(int id, String name, ArrayList<Branch> branches) {
        this.id = id;
        this.name = name;
        this.branches = branches;
    }


    // setters & getters
    public void setId(int id) {
        this.id = id;
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
        return branches.size();
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
                "This restaurant has " + getNumOfBranches() + " branches: \n";

        for(Branch branch : branches){
            res += branch.toString();
        }
        return res;
    }
}
