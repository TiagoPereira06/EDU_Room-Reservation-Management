package pt.isel.ls.userinterfaces.format;

import java.io.IOException;
import java.io.OutputStream;

public interface PrintInterface {
    void printTo(OutputStream outputStream) throws IOException;
}
