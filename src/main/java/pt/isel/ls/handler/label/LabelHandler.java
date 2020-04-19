package pt.isel.ls.handler.label;

import pt.isel.ls.handler.CommandHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class LabelHandler implements CommandHandler {
    final String idArgument = "{lid}";
    final String nameParameter = "name";


    public boolean checkIfLabelAlreadyExists(String label, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM labels WHERE name = ?");
        statement.setString(1, label.replace('+', ' '));
        return statement.executeQuery().next();

    }

}
