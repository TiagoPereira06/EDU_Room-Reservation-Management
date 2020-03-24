package pt.isel.ls.model;

public class User {
    private String name;
    private String email;

    public User(String email, String name) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("USER INFO: Username -> %s ; Email -> %s", name, email);
    }
}
