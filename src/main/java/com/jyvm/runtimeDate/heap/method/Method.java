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


    Method[] newMethods(Class clazz, FieldOrMethodInfo[] infos) {
        Method[] methods = new Method[infos.length];
        for (int i = 0; i < infos.length; i ++) {
            methods[i] = newMethod(clazz, infos[i]);
        }
        return methods;
    }

    //添加本地方法的执行判断
    private Method newMethod(Class clazz, FieldOrMethodInfo methodInfo) {
        Method method = new Method();
        method.clazz = clazz;
        method.copyInfo(methodInfo);
        method.copyAttribute(methodInfo);
        method.calcArgSlotCount();
        MethodDescriptor md = MethodDescriptorParser.parseMethodDescriptorParser(method.desc);
        if (method.isNative()) {
            method.injectCodeAttribute(md.returnType);
        }
        return method;
    }

    private void injectCodeAttribute(String returnType) {
        this.maxStack = 4; //todo
        this.maxLocals = this.argSlotCount;

        switch (returnType.getBytes()[0]) {
            case 'V':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xb1};
                break;
            case 'L':
            case '[':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xb0};
                break;
            case 'D':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xaf};
                break;
            case 'F':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xae};
                break;
            case 'J':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xad};
                break;
            default:
                this.code = new byte[]{(byte) 0xfe, (byte) 0xac};
                break;
        }
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

    private void calcArgSlotCount(List<String> parameterTypes) {
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
