package com.jyvm.runtimeDate.heap.method;

import com.jyvm.runtimeDate.Slot;

public class Slots {

    Slot[] slots;

    public Slots(int size) {
        if (size > 0) {
            slots = new Slot[size];
            for (int i = 0; i < size; i ++ ) {
                slots[i] = new Slot();
            }
        }
    }
    
    public Slot[] getLocalVars() {
        return this.slots;
    }

    public void setInt( int val, int index) {
        this.slots[index].num = val;
    }

    public int getInt(int index) {
        return this.slots[index].num;
    }

    public void setFloat(float val, int index) {
        int Int = Float.floatToIntBits(val);
        setInt(index, Int);
    }

    public float getFloat(int index) {
        return Float.intBitsToFloat(getInt(index));
    }

    public void setLong( long val, int index) {
//        setInt(index, (int) (val << 32));
        setInt(index, (int) val);  //32位操作系统中int为32位，
        setInt(index + 1, (int) (val >> 32));
    }

    public long getLong(int index) {
        //将低位无关项置0，以防止精度丢失
        return ((long) getInt(index + 1) << 32) | (getInt(index) & 0x0ffffffffL);
    }

    public void setDouble(double val, int index) {
        setLong(Double.doubleToLongBits(val), index);
    }

    public double getDouble(int index) {
        return  Double.longBitsToDouble(getLong(index));
    }

    public void setRef(Object o, int index) {
        slots[index].ref = o;
    }

    public java.lang.Object getRef(int index) {
        return slots[index].ref;
    }
}
