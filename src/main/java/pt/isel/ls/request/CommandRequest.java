package pt.isel.ls.request;


public class CommandRequest {
    private Method method;
    private Path path;
    private Parameter parameter;

    public CommandRequest(Method method, Path path, Parameter parameter) {
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

    public Parameter getParameter() {
        return parameter;
    }
}
