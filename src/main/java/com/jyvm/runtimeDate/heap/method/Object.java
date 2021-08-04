package com.jyvm.runtimeDate.heap.method;

/*
用于数据存储，同时也用于数组的创建和数据的读取等操作
* */
public class Object {

    Class clazz;
    java.lang.Object fields;

    public Object(Class clazz) {
        this.clazz = clazz;
        this.fields = new Slots(clazz.instanceSlotCount);
    }

    public Object(Class clazz, java.lang.Object o) {
        this.clazz = clazz;
        this.fields = o;
    }
    
    public Class clazz() {
        return this.clazz;
    }

    public Slots fields() {
        return (Slots) this.fields;
    }

    //是否存在类的相关关系
    public boolean isInstanceOf(Class clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }

    public Object getRefVar(String name, String descriptor) {
        Field field = this.clazz.getField(name, descriptor, false);
        Slots slots = (Slots) this.fields;
        return (Object) slots.getRef(field.slotIndex);
    }

    public void setRefVal(String name, String descriptor, Object ref) {
        Field field = this.clazz.getField(name, descriptor, false);
        Slots slots = (Slots) this.fields;
        slots.setRef(ref, field.slotIndex);
    }

    public byte[] bytes() {
        return (byte[]) this.fields;
    }

    public short[] shorts() {
        return (short[]) this.fields;
    }

    public int[] ints() {
        return (int[]) this.fields;
    }

    public long[] longs() {
        return (long[]) this.fields;
    }

    public char[] chars() {
        return (char[]) this.fields;
    }

    public float[] floats() {
        return (float[]) this.fields;
    }

    public double[] doubles() {
        return (double[]) this.fields;
    }

    public Object[] refs() {
        return (Object[]) this.fields;
    }

    public int arrayLength() {

        if (this.fields instanceof byte[]) {
            return ((byte[]) this.fields).length;
        }

        if (this.fields instanceof short[]) {
            return ((short[]) this.fields).length;
        }

        if (this.fields instanceof int[]) {
            return ((int[]) this.fields).length;
        }

        if (this.fields instanceof long[]) {
            return ((long[]) this.fields).length;
        }

        if (this.fields instanceof char[]) {
            return ((char[]) this.fields).length;
        }

        if (this.fields instanceof float[]) {
            return ((float[]) this.fields).length;
        }

        if (this.fields instanceof double[]) {
            return ((double[]) this.fields).length;
        }

        if (this.fields instanceof Object[]) {
            return ((Object[]) this.fields).length;
        }

        throw new RuntimeException("Not array");

    }
}
