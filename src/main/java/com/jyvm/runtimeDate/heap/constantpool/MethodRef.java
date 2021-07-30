package com.jyvm.runtimeDate.heap.constantpool;

import com.jyvm.classfile.constantpool.implement.ConstantMethodRef;
import com.jyvm.runtimeDate.heap.method.Method;

public class MethodRef extends SymRef{

    String name;
    String desc;
    Method method;

    public MethodRef(RuntimePool runtimePool, ConstantMethodRef methodRef) {
        this.name = methodRef.getNameAndDesc().get("name");
        this.desc = methodRef.getNameAndDesc().get("desc");
        this.constantPool = runtimePool;
    }

    public Method getMethod() {
        if (null == method) {
            this.setMethodRef();
        }
        return this.method;
    }

    public void setMethodRef() {

    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
