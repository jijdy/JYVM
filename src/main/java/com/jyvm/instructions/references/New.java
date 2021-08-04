package com.jyvm.instructions.references;

import com.jyvm.instructions.base.ClassInitLogic;
import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.heap.constantpool.ClassRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 2字节的索引从运行时常量池中拿到类引用，并拿到类对象引用，并推入到栈顶
* initStarted来判断类是否已经进行了类的初始化，若无，则先进行类的初始化加载
* 并将指针进行回溯，中断当前指令，再进行一次当前指令
* */
public class New extends Index16Instruction {

    public void execute(Frame frame) {
        RuntimePool pool = frame.method().clazz.runtimePool;
        ClassRef classRef = (ClassRef) pool.getConstant(this.index);
        Class clazz = classRef.resolveClass();
        if (!clazz.initStarted) {
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.thread(),clazz);
            return;
        }
        if (clazz.isInterface() || clazz.isAbstract()) {
            throw new InstantiationError();
        }
        Object ref = clazz.object();
        frame.getOperandStack().pushRef(ref);
    }
}
