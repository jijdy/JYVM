package com.jyvm.interpretor;

import com.jyvm.classfile.attribute.Implements.CodeAttribute;
import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.Thread;
import com.jyvm.runtimeDate.heap.method.Method;

import java.util.Arrays;

/*
* 指令解释器，用于循环的对指令进行解析和运算
* */
public class Interpretor {

    public Interpretor(Method method) {
//        this.codeAttribute = info.getCodeAttribute();
//        int maxLocals = codeAttribute.getMaxLocals();
//        int maxStack = codeAttribute.getMaxStack();
//        byte[] codeByte = codeAttribute.getBytes();
        Thread thread = new Thread();
        Frame frame = thread.frame(method);
        thread.pushStack(frame);  //将栈帧推入虚拟机栈中
        loop(thread,method.code());
    }

    void loop(Thread thread, byte[] codeByte) {
        Frame frame = thread.popStack();  //拿到栈顶元素
        BytecodeReader reader = new BytecodeReader();

        while (true) { //通过读取nextPC的值给到pc值指向读取的值的位置，一点点的增加，pc值会请，nextPC不会，拿取并写入
            int pc = frame.nextPC();
            thread.setPC(pc);

            //获得指令
            reader.reset(codeByte, pc);
            byte option = reader.readByte(); //获得指令
            Instruction instruction = Instruction.newInstruction(option);

//            if (null == instruction) {
//                System.out.println("指令还未实现"+ byteToHexString(new byte[]{option}));
//                break;
//            }

            //进行操作
            instruction.fetchOperands(reader);
            frame.setNextPC(reader.pc());
            instruction.execute(frame);

            System.out.println("指令为：" + byteToHexString(new byte[]{option}) + "------>" +
                    instruction.getClass().getSimpleName() + "-----局部变量表为---->" +
                    Arrays.toString(frame.getLocalVars().getLocalVars()) + "----操作数栈----->"+
                    Arrays.toString(frame.getOperandStack().getSlots())
                    );
        }
    }

    private static String byteToHexString(byte[] codes) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        for (byte b : codes) {
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            sb.append(strHex);
        }
        return sb.toString();
    }
}
