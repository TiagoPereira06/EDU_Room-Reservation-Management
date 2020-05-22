package pt.isel.ls.handler.room.search;

import pt.isel.ls.handler.result.View;

import static pt.isel.ls.handler.result.html.Element.*;

public class SearchRoomView extends View {
    @Override
    public String name() {
        return "Rooms Search Engine";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        form(
                                div(
                                        label(text("Name")).addAttribute("for", "roomname"),
                                        input()
                                                .addAttribute("type", "text")
                                                .addAttribute("name", "name")
                                                .addAttribute("id", "Name")
                                ),
                                div(
                                        label(text("Capacity")).addAttribute("for", "capacity"),
                                        input()
                                                .addAttribute("type", "number")
                                                .addAttribute("name", "capacity")
                                                .addAttribute("id", "capacity")
                                ),
                                div(
                                        label(text("Begin")).addAttribute("for", "begin"),
                                        input()
                                                .addAttribute("type", "datetime-local")
                                                .addAttribute("name", "begin")
                                                .addAttribute("id", "beginTime")
                                ),
                                div(
                                        label(text("Duration")).addAttribute("for", "duration"),
                                        input()
                                                .addAttribute("type", "number")
                                                .addAttribute("name", "duration")
                                                .addAttribute("id", "Duration")
                                ),
                                input().addAttribute("type", "Submit Text")

                        ).addAttribute("method", "get")
                                .addAttribute("action", "/rooms")
                )

        ).build();
    }

    @Override
    public String plainOutput() {
        return null;
    }
}
