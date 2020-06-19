package pt.isel.ls.request;

import pt.isel.ls.errors.router.RouterException;
import pt.isel.ls.userinterfaces.interfaces.LocalInterface;
import pt.isel.ls.userinterfaces.interfaces.OutputInterface;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.request.Header.checkHeader;

public class CommandRequest {
    public final TransactionManager transactionManager;
    private final Method method;
    private final Path path;
    private final Header header;
    private final PostParameters postParameters;
    private List<Parameter> parameter;

    public CommandRequest(Method method, Path path, List<Parameter> parameter, Header header) {
        this.method = method;
        this.path = path;
        this.header = header;
        this.parameter = parameter;
        transactionManager = new TransactionManager();
        if (method == Method.POST) {
            postParameters = new PostParameters(parameter);
        } else {
            postParameters = new PostParameters(new LinkedList<>());
        }
    }

    public static CommandRequest formatUserInput(String[] rawTask, OutputInterface outputInterface)
            throws RouterException {
        final Method method;
        final Path path;
        Header header;
        List<Parameter> parameters;
        try {
            method = Method.valueOf(rawTask[0]);
        } catch (IllegalArgumentException e) {
            throw new RouterException();
        }
        path = new Path(rawTask[1]);
        //OS HEADERS SÓ APARECEM NA POS 2!
        try {
            header = checkHeader(rawTask, 2); //EXISTE HEADER
            parameters = getParameters(rawTask, 3); //PARAM OU NÃO EXISTE OU NA POS3
        } catch (IllegalArgumentException e) {
            header = new Header();
            //NÃO EXISTE HEADER
            if (outputInterface instanceof LocalInterface) {
                header.addPair(HeaderType.ACCEPT, new HeaderValue("text/plain"));
            } else {
                header.addPair(HeaderType.ACCEPT, new HeaderValue("text/html"));
            }
            parameters = getParameters(rawTask, 2); //PARAM OU NÃO EXISTE OU NA POS2
        }
        return new CommandRequest(method, path, parameters, header);
    }

    private static List<Parameter> getParameters(String[] rawInput, int index) {
        List<Parameter> list = new LinkedList<>();
        if (index >= rawInput.length) {
            return list;
        }
        String rawString = rawInput[index];
        String[] params = rawString.split("&");
        for (String st : params) {
            String[] parts = st.split("=");
            if (parts.length > 1) {
                list.add(new Parameter(parts[0], parts[1].replace("+", " ")));
            }
        }
        return list;
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

    public String getParameterByName(String paramName) {
        for (Parameter p : parameter) {
            if (p.getName().equals(paramName)) {
                return p.getValue();
            }
        }
        return null;
    }

    public Header getHeader() {
        return header;
    }

    public PostParameters getPostParameters() {
        return postParameters;
    }
}
