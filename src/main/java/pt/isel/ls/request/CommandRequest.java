package pt.isel.ls.request;

import java.util.LinkedList;
import java.util.List;

public class CommandRequest {
    private final Method method;
    private final Path path;
    private final Header header;
    private List<Parameter> parameter;

    public CommandRequest(Method method, Path path, List<Parameter> parameter, Header header) {
        this.method = method;
        this.path = path;
        this.header = header;
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

    public List<String> getParametersByName(String paramName) {
        List<String> list = new LinkedList<>();
        for (Parameter p : parameter) {
            if (p.getName().equals(paramName)) {
                list.add(p.getValue().replace("+", " "));
            }
        }
        return list;
    }

    public Header getHeader() {
        return header;
    }

}
