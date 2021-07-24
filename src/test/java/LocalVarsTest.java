import com.jyvm.runtimeDate.LocalVars;
import org.junit.Test;

import javax.swing.*;

public class LocalVarsTest {

    @Test
    public void test() {
        long x = 548621648555L;
        LocalVars localVars = new LocalVars(6);
        System.out.println(localVars.getLong(0));
        double y = 1648555.343523;
        System.out.println(localVars.getDouble(2));
        float z = 23435.234f;
        System.out.println(localVars.getFloat(4));
    }

    @Test
    public void test3() {
        System.out.println(0xfff0 & 0x00f0);
    }

    @Test
    public void test1() {
        double x = 245342353.14512;
        long y = Double.doubleToRawLongBits(x);
        System.out.println(Double.longBitsToDouble(y));
        int z = 23;
        System.out.println(z--);
        System.out.println(z);
    }
}
