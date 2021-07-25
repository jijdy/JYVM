package com.jyvm.runtimeDate;

public class Thread {
    int PC;
    Stack stack;

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public Thread() {
        this.stack = new Stack(1024);
    }

    public Frame popStack() {
        return this.stack.pop();
    }

    public void pushStack(Frame frame) {
        this.stack.push(frame);
    }

    public Frame frame(int maxLocals, int maxStack) {
        return new Frame(this, maxStack, maxStack);
    }
}
