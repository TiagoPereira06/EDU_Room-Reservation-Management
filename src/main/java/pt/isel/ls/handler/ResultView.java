package pt.isel.ls.handler;

public interface ResultView {

    String name();

    String htmlOutput();

    String plainOutput();

    void setModel(Model resultModel);
}
