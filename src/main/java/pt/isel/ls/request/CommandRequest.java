package pt.isel.ls.request;

import java.util.LinkedList;
import java.util.List;

public class CommandRequest {
    private Method method;
    private Path path;
    private List<Parameter> parameter;

    public CommandRequest(Method method, Path path, List<Parameter> parameter) {
        this.method = method;
        this.path = path;
        this.parameter = parameter;
    }

    public Method getMethod() {
        return method;
    }

    public Path getPath() {
        return path;
    }

    public List<Parameter> getParameter() {
        return parameter;
    }

    public void setParameter(List<Parameter> parameter) {
        this.parameter = parameter;
    }

    public List<Parameter> getParametersByName(String paramName) {
        List<Parameter> list = new LinkedList<>();
        for (Parameter p : parameter) {
            if (p.getName().equals(paramName)) {
                list.add(p);
            }
        }
        return list;
    }
}
