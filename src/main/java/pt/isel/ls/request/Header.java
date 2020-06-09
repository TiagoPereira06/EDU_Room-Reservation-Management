package pt.isel.ls.request;

import pt.isel.ls.userinterfaces.format.TextType;

import java.util.HashMap;

public class Header {
    private final HashMap<HeaderType, HeaderValue> pairs;

    public Header() {
        this.pairs = new HashMap<>();
    }

    public static Header checkHeader(String[] rawInput, int index) throws IllegalArgumentException {
        if (index >= rawInput.length) {
            throw new IllegalArgumentException();
        }
        final Header header = new Header();
        String s = rawInput[index];
        String[] parts = s.split("\\|");
        for (String str : parts) {
            String[] pair = str.split(":");
            HeaderType type = HeaderType.valueOf(pair[0].toUpperCase().replace("-", ""));
            HeaderValue value = new HeaderValue(pair[1]);
            header.addPair(type, value);
        }
        return header;
    }

    public static String getFilename(Header header) {
        String filename = "";
        HeaderValue hv = header.getValue(HeaderType.FILENAME);
        if (hv != null) {
            filename = hv.getValue();
        }
        return filename;
    }

    public static TextType getOutputType(Header header) {
        return TextType.valueOf(
                header.getValue(HeaderType.ACCEPT).getValue()
                        .split("/")[1].toUpperCase());
    }

    public void addPair(HeaderType name, HeaderValue value) {
        pairs.put(name, value);
    }

    public HeaderValue getValue(HeaderType name) {
        return pairs.get(name);
    }

}
