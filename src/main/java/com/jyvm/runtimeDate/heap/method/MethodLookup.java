package com.jyvm.runtimeDate.heap.method;

/**
 * 在该类的继承结构中寻找该方法，
 * 接口中去寻找相关方法
 */
public class MethodLookup {

    static public Method lookupMethodInClass(Class clazz, String name, String desc) {
        for (Class c = clazz; c != null; c = c.superClass) {
            for (Method method : c.methods) {
                if (method.name.equals(name) && method.desc.equals(desc)) {
                    return method;
                }
            }
        }
        return null;
    }

    static public Method lookupMethodInInterfaces(Class[] ifaces, String name, String desc) {
        for (Class inface : ifaces) {
            for (Method method : inface.methods) {
                if (method.name.equals(name) && method.desc.equals(desc)) {
                    return method;
                }
            }
        }
        return null;
    }

}
