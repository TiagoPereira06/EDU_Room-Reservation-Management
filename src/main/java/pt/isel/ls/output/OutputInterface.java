package pt.isel.ls.output;

import java.io.IOException;

public interface OutputInterface {

    void printToConsole();

    void printToFile(String filename) throws IOException;

}
