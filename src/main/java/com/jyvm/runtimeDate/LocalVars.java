package com.jyvm.runtimeDate;

/*
* 局部变量信息，按索引访问，以槽数组的形式存储数据
* 基本类型都依靠int数据进行存储，引用类型依靠Object来进行引用
* */
public class LocalVars {
    Slot[] localVars;

    public LocalVars(int maxLocals) {
        if(maxLocals > 0) {
            this.localVars = new Slot[maxLocals];
        }
        for(int i = 0; i < maxLocals; i ++) {
            localVars[i] = new Slot();
        }
    }

    public Slot[] getLocalVars() {
        return this.localVars;
    }

    public void setInt( int val, int index) {
        this.localVars[index].num = val;
    }

    public int getInt(int index) {
        return this.localVars[index].num;
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
        localVars[index].ref = o;
    }

    public Object getRef(int index) {
        return localVars[index].ref;
    }

    public void setSlot(int index, Slot slot) {
        this.localVars[index] = slot;
    }
}
