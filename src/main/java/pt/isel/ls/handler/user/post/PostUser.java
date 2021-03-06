package pt.isel.ls.handler.user.post;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.errors.command.ConflictArgumentException;
import pt.isel.ls.errors.command.MissingArgumentsException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.user.UserHandler;
import pt.isel.ls.handler.user.post.getform.PostUserFormResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.util.NoSuchElementException;

import static pt.isel.ls.utils.UtilMethods.checkValid;


public class PostUser extends UserHandler {

    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute((connection) -> {
            String email;
            String username;
            String postUserQuery = "INSERT INTO users(email, username) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(postUserQuery);
            try {
                email = commandRequest.getParameterByName(emailParameter);
                checkValid(email, emailParameter);
                statement.setString(1, email);

                username = commandRequest.getParameterByName(nameParameter);
                checkValid(email, nameParameter);
                statement.setString(2, username);

            } catch (NoSuchElementException exception) {
                String tag = exception.getMessage();
                commandRequest.getPostParameters().addErrorMsg(tag, "Missing " + tag);
                throw new MissingArgumentsException(getPostUserFormResult(commandRequest));
            }

            if (checksIfEmailAlreadyExists(email, connection)) {
                commandRequest.getPostParameters().addErrorMsg("email", "Duplicate Email Found");
                throw new ConflictArgumentException(getPostUserFormResult(commandRequest));
            }
            statement.executeUpdate();
            return new PostUserResult(email, "/users/" + email);
        });
    }

    private PostUserFormResult getPostUserFormResult(CommandRequest commandRequest) {
        return new PostUserFormResult(commandRequest.getPostParameters());
    }

    @Override
    public String description() {
        return "Creates new user";
    }


}
