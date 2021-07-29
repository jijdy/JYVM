package com.jyvm.instructions.references;

import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.FieldRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Field;
import com.jyvm.runtimeDate.heap.method.Object;
import com.jyvm.runtimeDate.heap.method.Slots;

/*
* 将实例变量的值推入到操作数栈顶
* */
public class Get_field extends Index16Instruction {

    public void execute(Frame frame) {
        RuntimePool pool = frame.method().clazz.runtimePool;
        FieldRef fieldRef = (FieldRef) pool.getConstant(this.index);
        Field field = fieldRef.getField();
        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        OperandStack stack = frame.getOperandStack();
        Object ref = (Object) stack.popRef();
        if (ref == null) {
            throw new NullPointerException();
        }
        String desc = field.desc();
        int slotId = field.slotIndex;
        Slots slots = ref.fields();
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
                stack.pushRef(slots.getRef(slotId));
                break;
            default:
                break;
        }
    }
}
