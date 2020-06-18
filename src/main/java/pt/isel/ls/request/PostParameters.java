package pt.isel.ls.request;

import java.util.LinkedList;
import java.util.List;

public class PostParameters {
    private final List<ParameterForm> parameterForms;

    public PostParameters(List<Parameter> parameters) {
        parameterForms = new LinkedList<>();
        for (Parameter p : parameters) {
            parameterForms.add(new ParameterForm(p, null));
        }
    }

    public boolean isValid() {
        for (ParameterForm pf : parameterForms) {
            if ((pf.getErrorMsg() != null)) {
                return false;
            }
        }
        return true;
    }

    public void addErrorMsg(String parameter, String msg) {
        for (ParameterForm pf : parameterForms) {
            if (pf.getParameter().getName().equals(parameter)) {
                pf.setErrorMsg(msg);
                return;
            }
        }
        parameterForms.add(new ParameterForm(new Parameter(parameter, ""), msg));
    }

    public String getErrorByParameterName(String parameterName) {
        for (ParameterForm pf : parameterForms) {
            if ((pf.getParameter().getName().equals(parameterName))) {
                return pf.getErrorMsg();
            }
        }
        return null;
    }


    public String getParameterValue(String parameterName) {
        for (ParameterForm pf : parameterForms) {
            if (pf.getParameter().getName().equals(parameterName)) {
                return pf.getParameter().getValue();
            }
        }
        return null;
    }

    public List<String> getLabels() {
        List<String> labels = new LinkedList<>();
        for (ParameterForm pf : parameterForms) {
            if (pf.getParameter().getName().equals("label")) {
                labels.add(pf.getParameter().getValue());
            }
        }
        return labels;
    }
}

