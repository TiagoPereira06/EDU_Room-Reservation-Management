package pt.isel.ls.print;

import java.io.IOException;
import java.io.OutputStream;

public interface PrintInterface {
    void printTo(OutputStream outputStream) throws IOException;
}
