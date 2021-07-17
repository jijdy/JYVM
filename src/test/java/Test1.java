import com.jyvm.classfile.ClassReader;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

public class Test1 {

    @Test
    public void test1() {
        byte[] bytes = {0x1,0xd,3,4,5,6,6,75,7,3,2};
        ClassReader classReader = new ClassReader(bytes);
    }

    @Test
    public void test2() {
        byte[] bytes = {0x1,0x0,0x0,0x0,0x0,0x0,0x0,0x0};
        System.out.println(byteToBigInteger(bytes,0,8));
    }

    public static int getInt(byte[] arr, int index) {
        return  (0x000000ff & arr[index]) << 4 |
                (0x000000ff & arr[index+1]);
    }

    public int byteToInteger(byte[] bytes, int first, int last) {
        int re = 0;
        int index = last - first;
        if (index > 4) return 0;
        while(first < last) {
            re |=  (0x000000ff & bytes[first++]) << 4*(index--);
        }
        return re;
    }

    public long byteToBigInteger(byte[] bytes, int first, int last) {
        long re = 0;
        int index = last - first;
        if (index > 8) return 0;
        while(first < last) {
            re |= (long) (0x000000ff & bytes[first++]) << 4*(index--);
        }
        return re;
    }


}
