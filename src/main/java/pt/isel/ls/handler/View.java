package pt.isel.ls.handler;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Element;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.anchor;
import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.text;

public abstract class View implements ResultView {

    public Element homeButton() {
        return (anchor(text("Home")).addAttribute("href", "/"));
    }

    public Element button(String value, String link) {
        return (anchor(text(value)).addAttribute("href", link));
    }


}
