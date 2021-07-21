package com.jyvm.classfile;

import utils.ByteUtil;

import java.math.BigInteger;

//使用byte[]字节流来读取class文件中的数据并进行解析
public class ClassReader {

    private final byte[] bytes;
    public int index;

    public ClassReader(byte[] bytes) {
        this.bytes = bytes;
        index = 0;
    }

    public int readNextU1() {
        return ByteUtil.byteToInteger(read(1),0,1);
    }

    public int readNextU2Int() {
        return ByteUtil.byteToInteger(read(2),0,2);
    }

    public int[] readU2Ints() {
        int length = this.readNextU2Int();
        int[] U2s = new int[length];
        for (int i = 0; i < length; i ++) {
            U2s[i] = this.readNextU2Int();
        }
        return U2s;
    }

    public float raedNextU4Float() {
        return Float.intBitsToFloat((int) readNextU4Long());
    }

    public double readNextU4Double() {
        return Double.longBitsToDouble(readNextLong());
    }

    public long readNextU4Long() {
        return ByteUtil.byteToBigInteger(read(4),0,4);
    }

    public long readNextLong() {
        return ByteUtil.byteToBigInteger(read(8),0,8);

    }

    //值定读取出长度的字节大小
    public byte[] read(int length) {
        byte[] bytes1 = new byte[length];
        System.arraycopy(bytes, index, bytes1, 0, length);
        index += length;
        return bytes1;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public int getIndex() {
        return index;
    }
}
