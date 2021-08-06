package com.jyvm.instructions.references;

import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.ClassRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 用于创建多维数组的字节码指令，
* 创建完毕推入到操作数栈中
* 创建方法和一维数据差不多，但是使用了多维数组将创建的数组中再次创建新的数组
* */
public class Multi_Anew_array implements Instruction {

    private short index;
    private byte dimensions; // 表示数组的维度

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readShort();
        this.dimensions = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {
        RuntimePool runtimePool = frame.method().clazz.runtimePool;;
        ClassRef classRef = (ClassRef) runtimePool.getConstant(this.index);
        //数组的类型
        Class arrClass = classRef.resolveClass();

        OperandStack stack = frame.getOperandStack();
        //用于记录每一个维度的数组长度
        int[] counts = popAndCheckCounts(stack, this.dimensions);
        Object arr = newMultiDimensionalArray(counts, arrClass);
        stack.pushRef(arr);
    }

    //
    private int[] popAndCheckCounts(OperandStack stack, int dimensions) {
        int[] counts = new int[dimensions];
        for (int i = dimensions - 1; i >= 0; i--) {
            counts[i] = stack.popInt();
            if (counts[i] < 0) {
                throw new NegativeArraySizeException();
            }
        }

        return counts;
    }

    //通过递归的形式将多维数组进行创建，
    private Object newMultiDimensionalArray(int[] counts, Class arrClass) {
        int count = counts[0];
        Object arr = arrClass.newArray(count);
        if (counts.length > 1) {
            Object[] refs = arr.refs();
            for (int i = 0; i < refs.length; i++) {
                int[] copyCount = new int[counts.length - 1];
                System.arraycopy(counts, 1, copyCount, 0, counts.length - 1);
                refs[i] = newMultiDimensionalArray(copyCount, arrClass.componentClass());
            }
        }

        return arr;
    }

}
