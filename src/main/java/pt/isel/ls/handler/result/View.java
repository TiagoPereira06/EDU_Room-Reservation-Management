package pt.isel.ls.handler.result;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.result.html.Element;

import static pt.isel.ls.handler.result.html.Element.*;

public abstract class View implements ResultView {

/*    public Element homeButton() {
        return form(
                input().addAttribute("type","submit").addAttribute("value","home")
        ).addAttribute("action","/");
    }

    public Element button(String value, String link) {
        return form(
                input().addAttribute("type","submit").addAttribute("value",value)
        ).addAttribute("action",link);
    }*/

    public Element homeButton() {
        return h3(anchor(text("Home")).addAttribute("href","/"));
    }

    public Element button(String value, String link) {
        return h3(anchor(text(value)).addAttribute("href",link));
    }

}
