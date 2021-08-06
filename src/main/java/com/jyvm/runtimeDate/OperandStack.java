package com.jyvm.runtimeDate;


import com.jyvm.runtimeDate.heap.method.Object;

/*
  操作数栈存储,size用于存储栈顶元素位置，
* */
public class OperandStack {
    int size = 0;
    Slot[] slots;

    public OperandStack(int maxStack) {
        if (maxStack > 0) {
            slots = new Slot[maxStack];
        }
        for (int i = 0; i < maxStack; i ++) {
            slots[i] = new Slot();
        }
    }

    public Slot[] getSlots() {
        return this.slots;
    }

    public void pushInt(int val) {
        this.slots[size++].num = val;
    }

    public int popInt() {
        return this.slots[--size].num;
    }

    public void pushFloat(float val) {
        this.slots[size].num = Float.floatToIntBits(val);
        size++;
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
        Object c = (Object) this.slots[--size].ref;
        this.slots[size].ref = null;
        return c;
    }

    public void pushSlot(Slot slot) {
        this.slots[size++] = slot;
    }

    public Slot popSlot() {
        return this.slots[--size];
    }

    public void pushBoolean(boolean val) {
        if (val) {
            this.pushInt(1);
        } else {
            this.pushInt(0);
        }
    }

    public Object getRefFromTop(int n) {
        return (Object) this.slots[this.size - 1 - n].ref;
    }
}
