package com.jyvm.instructions.base;

/*
* 读取字节数据
* */
public class BytecodeReader {
    byte[] codes;
    int pc;

    //将code值和pc值进行重设定，将使用后的数据进行清除
    public void reset(byte[] codes, int pc) {
        this.codes = codes;
        this.pc = pc;
    }

    public int pc() {
        return this.pc;
    }

    public byte readByte() {
        byte code = this.codes[this.pc];
        this.pc++;
        return code;
    }

    public short readShort() {
        return (short) (readByte() << 8 | readByte());
    }

    public int readInt() {
        return readShort() << 16 | readShort();
    }

    public int[] readInts(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i ++) {
            ints[i] = readInt();
        }
        return ints;
    }

    //用于lookuswitch和tableswitch,保证跳过字节使得pc指针为4的倍数
    public void skipPadding() {
        while (this.pc % 4 != 0) {
            this.readByte();
        }
    }
}
