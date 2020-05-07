package pt.isel.ls.handler.label.result;

import pt.isel.ls.handler.ResultInterface;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LabelResult implements ResultInterface {
    public HashMap<String, List<String>> value;
    private final String type;

    public LabelResult(HashMap<String, List<String>> value, String type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public List<String> columns() {
        return List.of("LabelName","RoomName");
    }

    @Override
    public List<List<String>> values() {
        Set<String> labels = value.keySet();
        for (String l: labels) {
            List<String> la = new java.util.ArrayList<>(List.of(l));
            la.addAll(value.get(l));
        }
        return new LinkedList<>();
    }

    @Override
    public String description() {
        return type.concat("Label");
    }
}
