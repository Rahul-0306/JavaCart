package models;

public class Admin extends User {
    public Admin(String userId, String name) {
        super(userId, name, "Admin");
    }
}
