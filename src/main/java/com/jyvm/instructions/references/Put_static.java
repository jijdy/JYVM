package com.jyvm.instructions.references;

import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.FieldRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Field;
import com.jyvm.runtimeDate.heap.method.Method;
import com.jyvm.runtimeDate.heap.method.Slots;

/*
* 给类的某个静态变量赋值，通过索引找到该字段，
* 并从栈顶弹出需要赋给静态变量的值，在根据描述来对Class的静态值改变
* */
public class Put_static extends Index16Instruction {

    public void execute(Frame frame) {
        Method method = frame.method();
        Class aclazz = method.clazz;
        RuntimePool pool = aclazz.runtimePool;
        FieldRef fieldRef = (FieldRef) pool.getConstant(this.index);
        Field field = fieldRef.getField();
        Class clazz = field.clazz;
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
                slots.setInt(stack.popInt(),slotId);
                break;
            case "F":
                slots.setFloat(stack.popFloat(),slotId);
                break;
            case "J":
                slots.setLong(stack.popLong(),slotId);
                break;
            case "D":
                slots.setDouble(stack.popDouble(),slotId);
                break;
            case "L":
            case "[":
                slots.setRef((com.jyvm.runtimeDate.heap.method.Object) stack.popRef(),slotId);
                break;
            default:
                break;
        }
    }

}
