package com.jyvm.instructions.references;

import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.FieldRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Field;
import com.jyvm.runtimeDate.heap.method.Method;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 给实例变量赋值，三个索引为常量池索引，变量值和操作数栈中的对象引用
* 从操作数栈中找到值并返回给到该实例属性
* */
public class Put_field extends Index16Instruction {

    public void execute(Frame frame) {
        Method method = frame.method();
        Class aclazz = method.clazz;
        RuntimePool pool = aclazz.runtimePool;
        //第一个索引从常量池中拿到字段引用
        FieldRef fieldRef = (FieldRef) pool.getConstant(this.index);
        Field field = fieldRef.getField();
        //若为静态则不为实例变量，若为final修饰，则只能在构造函数中进行该操作
        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        if (field.isFinal()) {
            if (aclazz != field.clazz || !aclazz.name.equals("<init>")){
                throw new IllegalAccessError();
            }
        }

        String desc = field.desc();
        int slotId = field.slotIndex;
        OperandStack stack = frame.getOperandStack();
        switch (desc.substring(0,1)) {
            case "Z":
            case "B":
            case "C":
            case "S":
            case "I":
                int intVal = stack.popInt();
                Object intRef = (Object) stack.popRef();
                if (intRef == null) {
                    throw new NullPointerException();
                }
                intRef.fields().setInt(intVal, slotId);
                break;
            case "F":
                float floatVal = stack.popFloat();
                Object floatRef = (Object) stack.popRef();
                if (floatRef == null) {
                    throw new NullPointerException();
                }
                floatRef.fields().setFloat(floatVal, slotId);
                break;
            case "J":
                long longVal = stack.popLong();
                Object longRef = (Object) stack.popRef();
                if (longRef == null) {
                    throw new NullPointerException();
                }
                longRef.fields().setLong(longVal, slotId);
                break;
            case "D":
                double doubleVal = stack.popDouble();
                Object doubleRef = (Object) stack.popRef();
                if (doubleRef == null) {
                    throw new NullPointerException();
                }
                doubleRef.fields().setDouble(doubleVal, slotId);
                break;
            case "L":
            case "[":
                Object val = (Object) stack.popRef();
                Object ref = (Object) stack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.fields().setRef(val, slotId);
                break;
            default:
                break;
        }
    }

}
