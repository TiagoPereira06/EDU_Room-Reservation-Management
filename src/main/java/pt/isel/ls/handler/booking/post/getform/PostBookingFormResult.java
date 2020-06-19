package pt.isel.ls.handler.booking.post.getform;

import pt.isel.ls.handler.Result;
import pt.isel.ls.model.User;
import pt.isel.ls.request.PostParameters;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Element;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.ArrayList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class PostBookingFormResult extends Result {
    private final List<User> userList;
    private final PostParameters postParameters;
    private final String roomName;

    public PostBookingFormResult(List<User> usersList, PostParameters postParameters, String roomName) {
        this.userList = usersList;
        this.postParameters = postParameters;
        this.roomName = roomName;
    }

    @Override
    public String name() {
        return roomName + " Booking Creator";
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
                        form(
                                div(addRoomName()),
                                div(getUsersSelector()),
                                div(addPostInput("Begin", "datetime-local", postParameters)),
                                div(addPostInput("Duration", "number", postParameters)),
                                input().addAttribute("type", "submit").addAttribute("value", "Create!")

                        ).addAttribute("method", "post")
                                .addAttribute("action", String.format("/rooms/%s/bookings/create", roomName))
                )

        ).build();
    }

    private Node[] addRoomName() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(label(text("Room Name ")).addAttribute("for", "rid"));
        nodes.add(
                input().addAttribute("type", "text")
                        .addAttribute("name", "rid")
                        .addAttribute("id", "rid")
                        .addAttribute("readonly", "readonly")
                        .addAttribute("value", roomName)
        );
        nodes.add(br());
        return nodes.toArray(new Node[0]);
    }

    private Node[] getUsersSelector() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(label(text("Available Users ")).addAttribute("for", "uid"));

        if (!userList.isEmpty()) {
            Element select = select().addAttribute("name", "uid")
                    .addAttribute("id", "uid");

            for (User l : userList) {
                String text = l.getName() + " - " + l.getEmail();
                Element option = option(text(text)).addAttribute("value", l.getEmail());
                if (!postParameters.isValid()) {
                    if (postParameters.getParameterValue("uid").equals(l.getEmail())) {
                        option.addAttribute("", "selected");
                    }
                }
                select.addNode(option);
            }
            nodes.add(select);
        } else {
            nodes.add(text("No Users !"));
        }
        nodes.add(br());
        return nodes.toArray(new Node[0]);
    }

    private Node[] setNavBar() {
        List<Node> navItems = new ArrayList<>();
        navItems.add(homeButton());
        navItems.add(text(" | "));
        navItems.add(button(String.format("%s's Bookings", roomName),
                String.format("/rooms/%s/bookings", roomName)));
        return navItems.toArray(new Node[0]);
    }


    @Override
    public String plainOutput() {
        return "Only Available on HTML Support";
    }
}
