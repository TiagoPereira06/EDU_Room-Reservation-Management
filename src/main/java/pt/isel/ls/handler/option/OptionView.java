package pt.isel.ls.handler.option;

import pt.isel.ls.handler.result.View;

import java.util.List;

class OptionView extends View {

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
