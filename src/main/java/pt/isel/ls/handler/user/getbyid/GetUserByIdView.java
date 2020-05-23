package pt.isel.ls.handler.user.getbyid;

import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Node;
import pt.isel.ls.model.User;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.handler.result.html.Element.*;

public class GetUserByIdView extends View {
    private final User model;

    public GetUserByIdView(User userById) {
        this.model = userById;
    }

    @Override
    public String name() {
        return "user " + model.getName() + " information";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        dl(listFormat()),
                        button("All Users", "/users"),
                        homeButton(),
                        button("Bookings", String.format("/users/%s/bookings", model.getEmail()))
                )
        ).build();
    }

    private List<Node> listFormat() {
        LinkedList<Node> listItems = new LinkedList<>();
        listItems.add(li(bold((text("Name:")))));
        listItems.add(dd(text(model.getName())));
        listItems.add(li(bold((text("Email:")))));
        listItems.add(dd(text(model.getEmail())));
        return listItems;
    }

    @Override
    public String plainOutput() {
        return null;
    }
}
