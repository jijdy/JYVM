package com.jyvm.runtimeDate.heap.constantpool;

import com.jyvm.classfile.constantpool.implement.ConstantMethodRef;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Method;
import com.jyvm.runtimeDate.heap.method.MethodLookup;

public class MethodRef extends SymRef{

    String name;
    String desc;
    Method method;

    public MethodRef(RuntimePool runtimePool, ConstantMethodRef methodRef) {
        this.name = methodRef.getNameAndDesc().get("name");
        this.desc = methodRef.getNameAndDesc().get("desc");
        this.constantPool = runtimePool;
    }

    //解析寻找class中的方法字段，非接口方法
    private void resolveMethodRef() {
        Class d = this.constantPool.getClazz();
        Class c = this.resolveClass();
        if (c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }
        Method method = lookupMethod(c, this.name, this.desc);
        if (null == method){
            throw new NoSuchMethodError();
        }

        if (!method.isAccessibleTo(d)){
            throw new IllegalAccessError();
        }
        this.method = method;
    }

    //若在继承的类结构中找不到，则取接口中去找，拿到方法属性之后再进行相关操作
    public Method lookupMethod(Class clazz, String name, String desc) {
        Method method = MethodLookup.lookupMethodInClass(clazz, name, desc);
        if (null == method) {
            method = MethodLookup.lookupMethodInInterfaces(clazz.interfaces, name, desc);
        }
        return method;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
