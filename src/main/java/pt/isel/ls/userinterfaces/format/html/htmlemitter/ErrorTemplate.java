package pt.isel.ls.userinterfaces.format.html.htmlemitter;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class ErrorTemplate {
    public static String errorTemplate(String msg) {
        return html(
                head(
                        nav((anchor(text("Home")).addAttribute("href", "/"))),
                        title(text("Error"))
                ),
                body(
                        h1(text("Something Went Wrong !")),
                        text("Reason : " + msg))
        ).build();
    }
}
