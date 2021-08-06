package com.jyvm.natice.java;

import com.jyvm.natice.NativeMethod;
import com.jyvm.natice.Registry;
import com.jyvm.runtimeDate.Frame;

public class _Float {

    public _Float() {
        String jlFloat = "java/lang/Float";
        Registry.register(jlFloat, "floatToRawIntBits", "(F)I", new NativeMethod(this, "floatToRawIntBits"));
        Registry.register(jlFloat,"intBitsToFloat", "(I)F",new NativeMethod(this,"intBitsToFloat"));
        Registry.register(jlFloat,"registerNatives", "()V",new NativeMethod(this,"registerNatives"));
    }

    public void registerNatives(Frame frame) {
        // do nothing
    }

    public void floatToRawIntBits(Frame frame){
        float val = frame.getLocalVars().getFloat(0);
        frame.getOperandStack().pushInt((int) val);
    }

    public void intBitsToFloat(Frame frame){
        int val = frame.getLocalVars().getInt(0);
        frame.getOperandStack().pushFloat(val);
    }
}
