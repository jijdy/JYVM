package com.jyvm.runtimeDate.heap.constantpool;

import com.jyvm.classfile.ConstantPool;
import com.jyvm.classfile.constantpool.ConstantInfo;
import com.jyvm.classfile.constantpool.implement.*;
import com.jyvm.runtimeDate.heap.method.Class;

/*
* 运行时常量池,将其存储的字节数据直接转为Object[]数组的
* 方式存储，方便直接访问
* */
public class RuntimePool {
    Class clazz;
    Object[] constants;

    public RuntimePool(Class clazz, ConstantPool constantPool) {
        int CPCount = constantPool.getConstantInfos().length;
        this.clazz = clazz;
        this.constants = new Object[CPCount];

        ConstantInfo[] constantInfos = constantPool.getConstantInfos();
        for (int i = 0; i < CPCount; i ++) {
            ConstantInfo constantInfo = constantInfos[i];
            switch (constantInfo.tag()) {
                case ConstantInfo.CONSTANT_INTEGER:
                    ConstantInteger integerInfo = (ConstantInteger) constantInfo;
                    this.constants[i] = integerInfo.value();
                    break;
                case ConstantInfo.CONSTANT_FLOAT:
                    ConstantFloat floatInfo = (ConstantFloat) constantInfo;
                    this.constants[i] = floatInfo.value();
                    break;
                case ConstantInfo.CONSTANT_LONG:
                    ConstantLong longInfo = (ConstantLong) constantInfo;
                    this.constants[i] = longInfo.value();
                    i++;
                    break;
                case ConstantInfo.CONSTANT_DOUBLE:
                    ConstantDouble doubleInfo = (ConstantDouble) constantInfo;
                    this.constants[i] = doubleInfo.value();
                    i++;
                    break;
                case ConstantInfo.CONSTANT_STRING:
                    ConstantString stringInfo = (ConstantString) constantInfo;
                    this.constants[i] = stringInfo.toString();
                    break;
                case ConstantInfo.CONSTANT_CLASS:
                    ConstantClass classInfo = (ConstantClass) constantInfo;
                    this.constants[i] = new ClassRef(this, classInfo);
                    break;
                case ConstantInfo.CONSTANT_FIELDREF:
                    this.constants[i] = new FieldRef(this, (ConstantFieldRef) constantInfo);
                    break;
                case ConstantInfo.CONSTANT_INTERFACE:
                    this.constants[i] = new InterfaceMethodRef(this, (ConstantInterfaceMethod) constantInfo);
                    break;
                case ConstantInfo.CONSTANT_METHODREF:
                    this.constants[i] = new MethodRef(this, (ConstantMethodRef) constantInfo);
                    break;
                default:
            }
        }
    }

    public Class getClazz() {
        return this.clazz;
    }

    public Object getConstant(int index) {
        return this.constants[index];
    }

}
