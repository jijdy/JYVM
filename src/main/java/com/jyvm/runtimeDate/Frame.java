package com.jyvm.runtimeDate;

/*
* 存储栈帧需要的帧链表数据结构，局部变量表和操作数栈
* 即一个栈帧就有下一个栈帧的地址，一个局部变量表，一个操作数变量表
* */
public class Frame {
    Frame lower;
    LocalVars localVars;
    OperandStack operandStack;
    Thread thread; //以便查找pc值
    int nextPC;

    public LocalVars getLocalVars() {
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public Frame( int maxLocals, int maxStack) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }
    public Frame( Thread thread, int maxLocals, int maxStack) {
        this.thread = thread;
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }
//    public Frame(Thread thread, int maxLocals, int maxStack) {
//        this.thread = thread;
//        this.localVars = new LocalVars(maxLocals);
//        this.operandStack = new OperandStack(maxStack);
//    }

    public Thread thread() {
        return this.thread;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

    public int nextPC() {
        return this.nextPC;
    }
}
