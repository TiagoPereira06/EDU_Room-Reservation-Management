package pt.isel.ls.handler.result;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.result.html.Element;

import static pt.isel.ls.handler.result.html.Element.anchor;
import static pt.isel.ls.handler.result.html.Element.text;

public abstract class View implements ResultView {

    public Element homeButton() {
        return (anchor(text("Home")).addAttribute("href", "/"));
    }

    public Element button(String value, String link) {
        return (anchor(text(value)).addAttribute("href", link));
    }


}
