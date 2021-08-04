package com.jyvm.runtimeDate;

import com.jyvm.runtimeDate.heap.method.Method;

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

    public Frame topFrame() {
        return this.stack.top();
    }

    public Frame frame(Method method) {
        return new Frame(this, method);
    }
}
