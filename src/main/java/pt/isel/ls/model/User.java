package pt.isel.ls.model;

import java.util.List;

public class User {
    private final String name;
    private final String email;

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

    public List<String> toList() {
        return List.of(name, email);
    }

    @Override
    public String toString() {
        return String.format("USER INFO: Username -> %s ; Email -> %s", name, email);
    }
}
