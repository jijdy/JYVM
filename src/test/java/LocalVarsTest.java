import com.jyvm.runtimeDate.LocalVars;
import org.junit.Test;

public class LocalVarsTest {

    @Test
    public void test() {
        long x = 548621648555L;
        LocalVars localVars = new LocalVars(6);
        localVars.setLong(0,x);
        System.out.println(localVars.getLong(0));
        double y = 1648555.343523;
        localVars.setDouble(2, y);
        System.out.println(localVars.getDouble(2));
        float z = 23435.234f;
        localVars.setFloat(4,z);
        System.out.println(localVars.getFloat(4));
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
