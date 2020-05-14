package pt.isel.ls.request;

import java.util.HashMap;

public class Header {
    private  HashMap<HeaderType, HeaderValue> pairs;
    private String header;
    private HeaderType name;

    public Header() {
        this.pairs = new HashMap<>();
    }

    public Header(String header) {
        this.header = header;
    }

    public void addPair(HeaderType name, HeaderValue value) {
        pairs.put(name, value);
    }

    public HeaderValue getValue(HeaderType name) {
        return pairs.get(name);
    }

    public Header setHeader(HeaderType name) {
        this.name = name;
        return this;
    }
}

