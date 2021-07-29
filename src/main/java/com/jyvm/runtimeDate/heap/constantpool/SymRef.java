package com.jyvm.runtimeDate.heap.constantpool;

import com.jyvm.classfile.ConstantPool;
import com.jyvm.runtimeDate.heap.method.Class;

public class SymRef {

    RuntimePool constantPool;
    String className;
    Class clazz;

    //返回Class对象，若无则进行查找
    public Class resolveClass() {
        if (this.clazz == null) {
            Class c = constantPool.clazz;
            //根据常量池所在的类来调动相关类并返回
            this.clazz = c.loader.loadClass(this.className);
            return this.clazz;
        }
        return this.clazz;
    }
}
