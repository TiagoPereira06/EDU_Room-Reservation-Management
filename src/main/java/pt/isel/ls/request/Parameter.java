package pt.isel.ls.request;

public class Parameter {
    private String name;
    private String value;
    private String value2;

    public Parameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Parameter(String name, String value, String value2) {
        this.name = name;
        this.value = value;
        this.value2 = value2;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
