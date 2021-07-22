package com.jyvm.utils;

public  class ByteUtil {

//    public ByteUtil(byte[] read, int first, int last) {
//        if ((last - first) > 4) return byteToBigInteger(read,first,last);
//        else return
//    }

    /*
    * 将字节数组转换为整形或长整形的工具方法
    * */
    public static int byteToInteger(byte[] bytes, int first, int last) {
        int re = 0;
        int index = last - first;
        if (index > 8) return 0;
        while(first < last) {
            re +=  (0x000000ff & bytes[first++]) << (8*(index--) - 8);
        }
        return re;
    }

    public static long byteToBigInteger(byte[] bytes, int first, int last) {
        long re = 0;
        int index = last - first;
        if (index > 16) return 0;
        while(first < last) {
            re += (long) (0x000000ff & bytes[first++]) << (8*(index--) - 8);
        }
        return re;
    }
}
