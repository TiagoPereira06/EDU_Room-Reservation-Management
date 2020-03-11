package pt.isel.ls.request;


public class Request {
    private Method method;
    private Path path;
    private Parameters parameters;

    public Request(Method method, Path path, Parameters parameters) {
        this.method = method;
        this.path = path;
        this.parameters = parameters;
    }

    public Method getMethod() {
        return method;
    }

    public Path getPath() {
        return path;
    }

    public Parameters getParameters() {
        return parameters;
    }
}
