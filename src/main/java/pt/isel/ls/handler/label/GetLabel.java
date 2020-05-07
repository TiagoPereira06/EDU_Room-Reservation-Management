package pt.isel.ls.handler.label;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.label.result.LabelResult;
import pt.isel.ls.model.Label;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class GetLabel extends LabelHandler {
    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String getLabelsQuery = "SELECT * FROM roomlabels";
        PreparedStatement statement = connection.prepareStatement(getLabelsQuery);
        ResultSet resultSet = statement.executeQuery();
        HashMap<String, List<String>> labelsResult = new HashMap<>();

        while (resultSet.next()) {
            String labelName = resultSet.getString("label");
            String roomName = resultSet.getString("roomname");
            if(labelsResult.containsKey(labelName)) { //Ja CONTEM A LABEL - ADICIONA À LISTA
                //labelsResult.add(new Label(resultSet.getString("name")).parsePropertiesList());
                labelsResult.get(labelName).add(roomName);
            }else { //ADICIONA A LABEL E ADIOCIONA A SALA La
                List<String> rooms = new LinkedList<>();
                rooms.add(roomName);
                labelsResult.put(labelName, rooms);
            }
        }
        return new LabelResult(labelsResult, "GET");
    }

    @Override
    public String description() {
        return "Show all labels";
    }
}
