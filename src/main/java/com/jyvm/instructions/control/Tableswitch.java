package com.jyvm.instructions.control;

import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;

import java.util.concurrent.TimeUnit;

/*
* switch-case的实现，case条件连续的情况
* */
public class Tableswitch implements Instruction {

    int defaultOffset;
    int low;
    int high;
    int[] offsets;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        this.defaultOffset = reader.readInt();
        this.low = reader.readInt();
        this.high = reader.readInt();
        int size = this.high - this.low + 1;
        offsets = reader.readInts(size);
    }

    @Override
    public void execute(Frame frame) {
        int icase = frame.getOperandStack().popInt();
        int offset;
        if (icase > this.low && icase < this.high) {
            offset = this.offsets[icase - this.low];
        } else {
            offset = this.defaultOffset;
        }
        Instruction.branch(frame, offset);
    }
}
