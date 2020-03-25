package pt.isel.ls.handler;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Queries {

    public static boolean checksIfRoomAlreadyExists(String roomName, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM rooms  "
                + "WHERE name = ?");
        statement.setString(1, roomName);
        return statement.executeQuery().next();

    }

    public static boolean checksIfEmailAlreadyExists(String email, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users  "
                + "WHERE email = ?");
        statement.setString(1, email);
        return statement.executeQuery().next();
    }

    public static boolean checkIfLabelAlreadyExists(String label, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM labels"
                + "WHERE name = ?");
        statement.setString(1, label);
        return statement.executeQuery().next();

    }

}
