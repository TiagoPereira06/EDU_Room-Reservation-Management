package pt.isel.ls.utils.handlers;

import org.junit.Test;
import pt.isel.ls.App;

public class LabelsTest {
    String[] args = null;

    @Test
    public void getLabelsTest() {
        args = new String[]{"GET", "labels"};
        App.main(args);
    }

    @Test
    public void getLabelsByLidTest() {
        args = new String[]{"GET", "/labels/windows/rooms"};
        App.main(args);
    }

    @Test
    public void getLabelsByLidTest2() {
        args = new String[]{"GET", "/labels/microphone/rooms"};
        App.main(args);
    }

    @Test
    public void getLabelsByLidTest3() {
        args = new String[]{"GET", "/labels/no chairs/rooms"};
        App.main(args);
    }

}
