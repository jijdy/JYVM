package com.jyvm.runtimeDate.heap.constantpool;

import com.jyvm.classfile.constantpool.implement.ConstantClass;

public class ClassRef extends SymRef{

    public ClassRef(RuntimePool constantPool, ConstantClass info) {
        this.constantPool = constantPool;
        this.className = info.value();
    }
}
