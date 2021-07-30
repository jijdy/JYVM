package com.jyvm.runtimeDate.heap.method;

import com.jyvm.classfile.FieldOrMethodInfo;
import com.jyvm.classfile.attribute.Implements.CodeAttribute;

/*
* 和字段属性类型，将code属性字段中的信息存储到
* 该方法区中以便后续操作
* */
public class Method extends ClassMember{

    int maxStack;
    int maxLocals;

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    byte[] code;

    public static Method[] newMethod(Class clazz, FieldOrMethodInfo[] infos) {
        Method[] methods = new Method[infos.length];
        for (int i = 0; i < infos.length; i ++) {
            methods[i] = new Method();
            methods[i].clazz = clazz;
            methods[i].copyInfo(infos[i]);
            methods[i].copyAttribute(infos[i]);
        }
        return methods;
    }

    public void copyAttribute(FieldOrMethodInfo info) {
        CodeAttribute codeAttr = info.getCodeAttribute();
        if (codeAttr != null) {
            this.maxStack = codeAttr.getMaxStack();
            this.maxLocals = codeAttr.getMaxLocals();
            this.code = codeAttr.getBytes();
        }
    }

    public byte[] code() {
        return this.code;
    }
}
