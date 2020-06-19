package pt.isel.ls.handler.label.post;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.errors.command.ConflictArgumentException;
import pt.isel.ls.errors.command.MissingArgumentsException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.label.LabelHandler;
import pt.isel.ls.handler.label.post.getform.PostLabelFormResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.util.NoSuchElementException;

import static pt.isel.ls.utils.UtilMethods.checkValid;

public class PostLabel extends LabelHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute((connection) -> {

            final String labelName;
            String getRoomsQuery = "INSERT INTO labels(name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(getRoomsQuery);
            try {

                labelName = commandRequest.getParameterByName(nameParameter);
                checkValid(labelName, nameParameter);
                statement.setString(1, labelName);

            } catch (NoSuchElementException exception) {
                String tag = exception.getMessage();
                commandRequest.getPostParameters().addErrorMsg(tag, "Missing " + tag);
                throw new MissingArgumentsException(getPostLabelFormResult(commandRequest));
            }

            if (checkIfLabelAlreadyExists(labelName, connection)) {
                commandRequest.getPostParameters().addErrorMsg("name", "Duplicate Name Found");
                throw new ConflictArgumentException(getPostLabelFormResult(commandRequest));
            }
            statement.executeUpdate();
            return new PostLabelResult(labelName, "/labels/" + labelName);
        });
    }

    private PostLabelFormResult getPostLabelFormResult(CommandRequest commandRequest) {
        return new PostLabelFormResult(commandRequest.getPostParameters());
    }

    @Override
    public String description() {
        return "Creates new label";
    }
}
