package pt.isel.ls.handler;

import java.util.List;

public interface ResultInterface {
    List<String> columns();

    List<List<String>> values();

    String description();
}
