import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class Test {

    public static void main(String[] args) throws IOException {

        System.out.println(Paths.get("").toAbsolutePath());
        System.out.println(Arrays.toString(main1()));

    }


    public static byte[] main1() throws IOException {
        String xjre = "C:\\Users\\25863\\.jdks\\adopt-openj9-1.8.0_292\\jre\\lib"+ File.separator + "jce.jar";
        return Files.readAllBytes(Paths.get(xjre));
    }

    public static void mains() {
        Stack<Integer> s = new Stack<>();
        List<Integer> list = new ArrayList<Integer>();
        list.add(232);
        list.add(43);
        list.add(435);
        for (int i = 0; i < 3; i ++) {
            s.push(list.get(i));
        }

        System.out.println(s.peek());
        System.out.println(s.peek());
        System.out.println(s.pop());
        System.out.println(s.pop());
    }
}
