package Model;

//Stores and handles data about a member
//You can add more data
public class Member {
    private int id;  //id of the member, can be a string instead of int
    private String name;

    //constructor
    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }


    //Getter method connected to the name field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //The toString method retuning a textual representation of the
    //object's values.
    @Override
    public String toString() {
        return String.format("%s, ID %d", name, id);
    }
}
