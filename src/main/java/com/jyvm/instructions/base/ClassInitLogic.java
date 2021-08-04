package com.jyvm.instructions.base;

import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.Thread;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Method;

/*
* 类的初始化加载逻辑实现，若进行了类的初始化，则将进行类的加载初始化操作
* */
public class ClassInitLogic {

    //对类进行初始化操作，
    public static void initClass(Thread thread, Class clazz) {
        clazz.startInit();
        scheduleClinit(thread, clazz);
        initSuperClass(thread, clazz);
    }

    //将clinit描述方法推入到该线程的栈的栈帧中
    private static void scheduleClinit(Thread thread, Class clazz) {
        Method clinit = clazz.getClinitMethod();
        if (null == clinit) return;
        Frame newFrame = thread.frame(clinit);
        thread.pushStack(newFrame);
    }

    private static void initSuperClass(Thread thread, Class clazz) {
        if (clazz.isInterface()) return;
        Class superClass = clazz.superClass;
        if (null != superClass && !superClass.initStarted()) {
            initClass(thread, superClass);
        }
    }

}
