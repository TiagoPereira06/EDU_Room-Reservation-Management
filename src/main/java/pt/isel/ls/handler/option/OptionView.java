package pt.isel.ls.handler.option;

import pt.isel.ls.handler.ResultView;

import java.util.List;

class OptionView implements ResultView {

    private final List<List<String>> model;

    public OptionView(List<List<String>> routes) {
        this.model = routes;
    }

    @Override
    public String name() {
        return "GET All Routes";
    }

    @Override
    public String htmlOutput() {
        return null;
    }

    @Override
    public String plainOutput() {
        return null;
    }
}
