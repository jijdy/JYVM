package com.jyvm.runtimeDate.heap.method;

import com.jyvm.classfile.FieldOrMethodInfo;
import com.jyvm.classfile.attribute.Implements.CodeAttribute;
import com.jyvm.runtimeDate.heap.AccessFlag;
import com.sun.org.apache.bcel.internal.classfile.AccessFlags;

import java.util.List;

/*
* 和字段属性类型，将code属性字段中的信息存储到
* 该方法区中以便后续操作
* */
public class Method extends ClassMember{

    public int maxStack;
    public int maxLocals;
    byte[] code;
    public int argSlotCount;

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public boolean isNative() {
        return 0 != (this.accessFlags & AccessFlag.ACC_NATIVE);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags & AccessFlag.ACC_ABSTRACT);
    }

    public boolean isStrict() {
        return 0 != (this.accessFlags & AccessFlag.ACC_STRICT);
    }


    public static Method[] newMethod(Class clazz, FieldOrMethodInfo[] infos) {
        Method[] methods = new Method[infos.length];
        for (int i = 0; i < infos.length; i ++) {
            methods[i] = new Method();
            methods[i].clazz = clazz;
            methods[i].copyInfo(infos[i]);
            methods[i].copyAttribute(infos[i]);
            methods[i].calcArgSlotCount();
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

    private void calcArgSlotCount() {
        MethodDescriptor parsedDescriptor = MethodDescriptorParser.parseMethodDescriptorParser(this.desc);
        List<String> parameterTypes = parsedDescriptor.parameterTypes;
        for (String paramType : parameterTypes) {
            this.argSlotCount++;
            if ("J".equals(paramType) || "D".equals(paramType)) {
                this.argSlotCount++;
            }
        }
        if (!this.isStatic()) {
            this.argSlotCount++;
        }
    }

    public byte[] code() {
        return this.code;
    }
}
