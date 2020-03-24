package pt.isel.ls.handler.label;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostLabel implements CommandHandler {
    private final int labelNamePosition = 0;

    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        CommandResult commandResult = new CommandResult();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        Connection connection = null;
        String labelName = "";
        dataSource.setUrl(url);
        try {
            connection = dataSource.getConnection();
            String getRoomsQuery = "INSERT INTO labels(name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(getRoomsQuery);
            labelName = commandRequest.getParameter().get(labelNamePosition).getValue().replace('+', ' ');
            statement.setString(1, labelName);
            statement.executeQuery();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        commandResult.getResult().add("LABEL INSERTED: LABEL INFO -> ".concat(labelName));
        return commandResult;
    }

    @Override
    public String description() {
        return "Creates new label";
    }
}
