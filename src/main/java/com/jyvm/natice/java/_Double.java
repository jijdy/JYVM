package com.jyvm.natice.java;

import com.jyvm.natice.NativeMethod;
import com.jyvm.natice.Registry;
import com.jyvm.runtimeDate.Frame;

public class _Double {

    public _Double() {
        String jlDouble = "java/lang/Double";
        Registry.register(jlDouble, "doubleToRawLongBits", "(D)J", new NativeMethod(this, "doubleToRawLongBits"));
        Registry.register(jlDouble,"longBitsToDouble", "(J)D",new NativeMethod(this,"longBitsToDouble"));
        Registry.register(jlDouble,"registerNatives", "()V",new NativeMethod(this,"registerNatives"));
    }

    public void registerNatives(Frame frame) {
        // do nothing
    }

    public void doubleToRawLongBits(Frame frame) {
        double val = frame.getLocalVars().getDouble(0);
        frame.getOperandStack().pushLong((long) val);
    }

    public void longBitsToDouble(Frame frame) {
        long val = frame.getLocalVars().getLong(0);
        frame.getOperandStack().pushDouble(val);
    }
}
