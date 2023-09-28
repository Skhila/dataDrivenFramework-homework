package POM.Data.Model;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public User(int id, String firstname,String lastName, String phoneNumber)
    {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFullInfo(){
        return this.getId() + this.getFirstName() + this.getLastName() + this.getPhoneNumber();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
