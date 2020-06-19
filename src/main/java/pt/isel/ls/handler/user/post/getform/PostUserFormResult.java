package pt.isel.ls.handler.user.post.getform;

import pt.isel.ls.handler.Result;
import pt.isel.ls.request.PostParameters;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class PostUserFormResult extends Result {
    private final PostParameters postParameters;

    public PostUserFormResult(PostParameters postParameters) {
        this.postParameters = postParameters;
    }

    @Override
    public String name() {
        return "User Creator";
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
                                div(addPostInput("Email", "text", postParameters)),
                                div(addPostInput("Name", "text", postParameters)),
                                input().addAttribute("type", "submit").addAttribute("value", "Create!")

                        ).addAttribute("method", "post")
                                .addAttribute("action", "/users/create")
                )

        ).build();
    }

    private Node setNavBar() {
        return homeButton();
    }


    @Override
    public String plainOutput() {
        return "Only Available on HTML Support";
    }
}

