package com.jyvm.runtimeDate;

import java.util.logging.Logger;

/*
  操作数栈存储,size用于存储栈顶元素位置，
* */
public class OperandStack {
    int size;
    Slot[] slots;

    public OperandStack(int maxStack) {
        if (maxStack > 0) {
            this.size = maxStack;
            slots = new Slot[size];
        }
        for (int i = 0; i < maxStack; i ++) {
            slots[i] = new Slot();
        }
    }

    public void pushInt(int val) {
        this.slots[size++].num = val;
    }

    public int popInt() {
        return this.slots[--size].num;
    }

    public void pushFloat(float val) {
        this.slots[size++].num = Float.floatToIntBits(val);
    }

    public float popFloat() {
        return Float.intBitsToFloat(this.slots[--size].num);
    }

    public void pushLong(long val) {
        this.slots[size++].num = (int) val;
        this.slots[size++].num = (int) (val >> 32);
    }

    public long popLong() {
        return ((long) this.slots[--size].num << 32) | this.slots[--size].num;
    }

    public void pushDouble(double val) {
        long temp = Double.doubleToLongBits(val);
        pushLong(temp);
    }

    public double popDouble() {
        return Double.longBitsToDouble(popLong());
    }

    public void pushRef(Object o) {
        this.slots[size++].ref = o;
    }

    public Object popRef() {
        try {
            return this.slots[--size].ref;
        } finally {
            this.slots[size].ref = null;
        }
    }

}
