package pt.isel.ls.request;

public class ParameterForm {
    private final Parameter parameter;
    private String errorMsg;

    public ParameterForm(Parameter parameter, String errorMsg) {
        this.parameter = parameter;
        this.errorMsg = errorMsg;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
