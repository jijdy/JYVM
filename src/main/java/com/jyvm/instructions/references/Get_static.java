package com.jyvm.instructions.references;

import com.jyvm.instructions.base.ClassInitLogic;
import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.ClassRef;
import com.jyvm.runtimeDate.heap.constantpool.FieldRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Field;
import com.jyvm.runtimeDate.heap.method.Method;
import com.jyvm.runtimeDate.heap.method.Slots;

/*
* 拿到某个类的静态变量值，并直接推入到栈顶中
* 和put_static类型，只是出入方向不同
* */
public class Get_static extends Index16Instruction {

    public void execute(Frame frame) {
        Method method = frame.method();
        Class aclazz = method.clazz;
        RuntimePool pool = aclazz.runtimePool;
        FieldRef fieldRef = (FieldRef) pool.getConstant(this.index);
        Field field = fieldRef.getField();
        //非静态变量则报错
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        Class clazz = field.clazz;
        if (!clazz.initStarted) {
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.thread(), clazz);
            return;
        }
        String desc = field.desc();
        int slotId = field.slotIndex;
        Slots slots = clazz.staticVars;
        OperandStack stack = frame.getOperandStack();
        switch (desc.substring(0,1)) {
            case "Z":
            case "B":
            case "C":
            case "S":
            case "I":
                stack.pushInt(slots.getInt(slotId));
                break;
            case "F":
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case "J":
                stack.pushLong(slots.getLong(slotId));
                break;
            case "D":
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case "L":
            case "[":
                stack.pushRef((com.jyvm.runtimeDate.heap.method.Object) slots.getRef(slotId));
                break;
            default:
                break;
        }
    }
}
