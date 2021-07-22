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
}
