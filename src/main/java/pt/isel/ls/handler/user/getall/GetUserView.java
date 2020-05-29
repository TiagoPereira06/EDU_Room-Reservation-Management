package pt.isel.ls.handler.user.getall;

import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Element;
import pt.isel.ls.handler.result.html.Node;
import pt.isel.ls.model.User;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.handler.result.html.Element.*;

public class GetUserView extends View {
    private final List<User> model;

    public GetUserView(List<User> allUsers) {
        this.model = allUsers;
    }

    @Override
    public String name() {
        return "All Users";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name())),
                        nav(setNavBar())
                ),
                body(
                        h1(text(name())),
                        setTable())
        ).build();
    }

    private Node setNavBar() {
        return homeButton();
    }


    private Element setTable() {
        return Element.table(
                tr(
                        th(text("Name")),
                        th(text("Email"))
                ),
                tableData()
        ).addAttribute("border", "1");

    }

    private LinkedList<Node> tableData() {
        LinkedList<Node> list = new LinkedList<>();
        for (User user : model) {
            String email = user.getEmail();
            String name = user.getName();
            list.add(
                    tr(
                            td(text(name)),
                            td(anchor(text(email)).addAttribute("href", String.format("/users/%s", email)))
                    )
            );
        }
        return list;
    }

    @Override
    public String plainOutput() {
        return model.toString();
    }
}
