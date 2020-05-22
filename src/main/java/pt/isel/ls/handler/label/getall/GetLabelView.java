package pt.isel.ls.handler.label.getall;

import pt.isel.ls.handler.result.View;
import pt.isel.ls.model.Label;

import java.util.List;

public class GetLabelView extends View {
    private final List<Label> model;

    public GetLabelView(List<Label> allLabels) {
        this.model = allLabels;
    }

    @Override
    public String name() {
        return null;
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
