package classfile;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Test1 {

    @Test
    public void test() throws IOException {
        String xjre = "C:\\Users\\25863\\HelloWorld.class";
        System.out.println(Arrays.toString(Files.readAllBytes(Paths.get(xjre))));
    }
}
