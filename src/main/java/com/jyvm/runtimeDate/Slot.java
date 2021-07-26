package com.jyvm.runtimeDate;

/*
* 数据槽
* */
public class Slot {

    int num;
    Object ref;

    @Override
    public String toString() {
        return "{" +
                "num=" + num +
                ", ref=" + ref +
                '}';
    }
}
