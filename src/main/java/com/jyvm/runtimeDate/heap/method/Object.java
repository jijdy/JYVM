package com.jyvm.runtimeDate.heap.method;

public class Object {

    Class clazz;
    Slots fields;

    public Object(Class clazz) {
        this.clazz = clazz;
        this.fields = new Slots(clazz.instanceSlotCount);
    }

    public Class clazz() {
        return this.clazz;
    }

    public Slots fields() {
        return this.fields;
    }

    //是否存在类的相关关系
    public boolean isInstanceOf(Class clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }
}
