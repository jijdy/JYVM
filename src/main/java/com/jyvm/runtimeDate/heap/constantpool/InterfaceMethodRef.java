package com.jyvm.runtimeDate.heap.constantpool;

import com.jyvm.classfile.constantpool.implement.ConstantInterfaceMethod;
import com.jyvm.runtimeDate.heap.method.Method;

public class InterfaceMethodRef extends SymRef{

    String name;
    String desc;
    Method method;

    public InterfaceMethodRef(RuntimePool pool, ConstantInterfaceMethod interfaceMethod) {
        this.name = interfaceMethod.getNameAndDesc().get("name");
        this.desc = interfaceMethod.getNameAndDesc().get("desc");
        this.constantPool = pool;
    }

    public Method getMethod() {
        if (null == this.method) {
            this.setMethod();
        }
        return this.method;
    }

    public void setMethod() {

    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
