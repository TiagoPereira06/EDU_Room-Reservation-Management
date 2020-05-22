package pt.isel.ls.handler.label.post;

import pt.isel.ls.handler.ResultView;

public class PostLabelView implements ResultView {
    private final String model;

    public PostLabelView(String postedLabelName) {
        this.model = postedLabelName;
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
