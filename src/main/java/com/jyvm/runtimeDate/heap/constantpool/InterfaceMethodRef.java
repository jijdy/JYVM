package com.jyvm.runtimeDate.heap.constantpool;

import com.jyvm.classfile.constantpool.implement.ConstantInterfaceMethod;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Method;
import com.jyvm.runtimeDate.heap.method.MethodLookup;

public class InterfaceMethodRef extends SymRef{

    String name;
    String desc;
    Method method;

    public InterfaceMethodRef(RuntimePool pool, ConstantInterfaceMethod interfaceMethod) {
        this.name = interfaceMethod.getNameAndDesc().get("name");
        this.desc = interfaceMethod.getNameAndDesc().get("desc");
        this.constantPool = pool;
    }

    //进行接口方法的加载，
    public Method resolvedInterfaceMethod() {
        if (this.method == null) {
            this.resolveInterfaceMethodRef();
        }
        return this.method;
    }

    private void resolveInterfaceMethodRef() {
        Class d = this.constantPool.getClazz();
        Class c = this.resolveClass();
        if (!c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }

        Method method = lookupInterfaceMethod(c, this.name, this.desc);
        if (null == method) {
            throw new NoSuchMethodError();
        }

        if (!method.isAccessibleTo(d)){
            throw new IllegalAccessError();
        }

        this.method = method;
    }

    private Method lookupInterfaceMethod(Class iface, String name, String descriptor) {
        for (Method method : iface.methods) {
            if (method.name().equals(name) && method.desc().equals(descriptor)) {
                return method;
            }
        }
        return MethodLookup.lookupMethodInInterfaces(iface.interfaces, name, descriptor);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
