package com.jyvm.runtimeDate;

import java.util.EmptyStackException;

public class Stack {
    int maxSize; //最大帧容量
    int size;    //当前栈大小，栈帧数量
    Frame top;   //栈顶的帧元素

    public Stack(int maxSize) {
        this.maxSize = maxSize;
    }

    //栈帧入栈
    public void push(Frame farme) {
        if (this.size >= maxSize) {
            throw new StackOverflowError("栈容量不足");
        }
        if (this.top != null) {
            farme.lower = this.top;
        }
        this.top = farme;
        this.size ++;
    }

    //使得栈帧出栈
    public Frame pop() {
        if (this.top == null) {
            throw new EmptyStackException();
        }
        Frame top = this.top;
        this.top = top.lower;
        top.lower = null;
        this.size --;
        return top;
    }

    public Frame top() {
        if (this.top == null) {
            throw new EmptyStackException();
        }
        return top;
    }

    //遍历的将栈中的栈帧进行返回
    public Frame[] getFrames() {
        Frame[] frames = new Frame[this.size];
        int i = 0;
        for (Frame frame = this.top; frame != null; frame = frame.lower) {
            frames[i] = frame;
        }
        return frames;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    //将栈中的所有栈帧都进行清理操作
    public void clear() {
        if (!this.isEmpty()) {
            this.pop();
        }
    }
}