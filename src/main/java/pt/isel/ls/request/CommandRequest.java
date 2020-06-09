package pt.isel.ls.request;

import pt.isel.ls.userinterfaces.interfaces.LocalInterface;
import pt.isel.ls.userinterfaces.interfaces.OutputInterface;
import pt.isel.ls.utils.UtilMethods;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.request.Header.checkHeader;

public class CommandRequest {
    private final Method method;
    private final Path path;
    private final Header header;
    public TransactionManager transactionManager;
    private List<Parameter> parameter;

    public CommandRequest(Method method, Path path, List<Parameter> parameter, Header header) {
        this.method = method;
        this.path = path;
        this.header = header;
        this.parameter = parameter;
        transactionManager = new TransactionManager();
    }

    public static CommandRequest formatUserInput(String[] rawTask, OutputInterface outputInterface)
            throws NoSuchMethodException {
        final Method method;
        final Path path;
        Header header;
        List<Parameter> parameters;
        try {
            method = Method.valueOf(rawTask[0]);
        } catch (IllegalArgumentException e) {
            throw new NoSuchMethodException("Request Not Found");
        }
        path = new Path(rawTask[1]);
        //OS HEADERS SÓ APARECEM NA POS 2!
        try {
            header = checkHeader(rawTask, 2); //EXISTE HEADER
            parameters = UtilMethods.getParameters(rawTask, 3); //PARAM OU NÃO EXISTE OU NA POS3
        } catch (IllegalArgumentException e) {
            header = new Header();
            //NÃO EXISTE HEADER
            if (outputInterface instanceof LocalInterface) {
                header.addPair(HeaderType.ACCEPT, new HeaderValue("text/plain"));
            } else {
                header.addPair(HeaderType.ACCEPT, new HeaderValue("text/html"));
            }
            parameters = UtilMethods.getParameters(rawTask, 2); //PARAM OU NÃO EXISTE OU NA POS2
        }
        return new CommandRequest(method, path, parameters, header);
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
