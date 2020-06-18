package pt.isel.ls.handler;


public class ResponseHeader {
    private final String name;
    private final String value;

    public ResponseHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
