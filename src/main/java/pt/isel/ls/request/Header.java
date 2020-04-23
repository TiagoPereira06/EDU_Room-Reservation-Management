package pt.isel.ls.request;

import java.util.HashMap;
import java.util.Objects;

public class Header {
    private final HashMap<HeaderType, HeaderValue> pairs;

    public Header() {
        this.pairs = new HashMap<>();
    }

    public void addPair(HeaderType name, HeaderValue value) {
        pairs.put(name, value);
    }

    public HeaderValue getValue(HeaderType name) {
        return pairs.get(name);
    }

    public HashMap<HeaderType, HeaderValue> getMap() {
        return pairs;
    }
}

