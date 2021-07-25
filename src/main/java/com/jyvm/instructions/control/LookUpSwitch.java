package com.jyvm.instructions.control;

import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* case条件不为连续的，在cases字节码中存储为类似key-value类型数据
* 不过都在同一个数组中，所以整体乘2
* */
public class LookUpSwitch implements Instruction {

    int defaultOffset;
    int size;
    int[] cases;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        this.defaultOffset = reader.readInt();
        this.size = reader.readInt();
        this.cases = reader.readInts(size * 2);
    }

    @Override
    public void execute(Frame frame) {
        int key = frame.getOperandStack().popInt();
        for (int i = 0; i < size * 2; i += 2) {
            if (cases[i] == key) {
                Instruction.branch(frame, cases[i + 1]);
            }
        }
        Instruction.branch(frame, defaultOffset);
    }
}
