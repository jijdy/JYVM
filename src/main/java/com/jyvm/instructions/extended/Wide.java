package com.jyvm.instructions.extended;

import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.instructions.loads.aloads.Aload;
import com.jyvm.instructions.loads.dloads.Dload;
import com.jyvm.instructions.loads.floads.Fload;
import com.jyvm.instructions.loads.iloads.Iload;
import com.jyvm.instructions.loads.lloads.Lload;
import com.jyvm.instructions.math.iinc.Iinc;
import com.jyvm.instructions.stores.astore.Astore;
import com.jyvm.instructions.stores.dstore.Dstore;
import com.jyvm.instructions.stores.fstore.Fstore;
import com.jyvm.instructions.stores.istore.Istore;
import com.jyvm.instructions.stores.lstore.Lstore;
import com.jyvm.runtimeDate.Frame;

/*
* 从局部变量表中根据索引获取数据，以8bit，256位最大，若超过这个值，
* 则使用wide指令来进行扩展操作，仅仅改变索引范围，本质并无改变
* */
public class Wide implements Instruction {

    Instruction modifiedInstruction;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        int options = reader.readByte();
        switch (options) {
            case 0x15:
                Iload iload = new Iload();
                iload.index = reader.readShort();
                this.modifiedInstruction = iload;
            case 0x16:
                Lload lload = new Lload();
                lload.index = reader.readShort();
                this.modifiedInstruction = lload;
            case 0x17:
                Fload fload = new Fload();
                fload.index = reader.readShort();
                this.modifiedInstruction = fload;
            case 0x18:
                Dload dload = new Dload();
                dload.index = reader.readShort();
                this.modifiedInstruction = dload;
            case 0x19:
                Aload aload = new Aload();
                aload.index = reader.readShort();
                this.modifiedInstruction = aload;
            case 0x36:
                Istore istore = new Istore();
                istore.index = reader.readShort();
                this.modifiedInstruction = istore;
            case 0x37:
                Lstore lstore = new Lstore();
                lstore.index = reader.readShort();
                this.modifiedInstruction = lstore;
            case 0x38:
                Fstore fstore = new Fstore();
                fstore.index = reader.readShort();
                this.modifiedInstruction = fstore;
            case 0x39:
                Dstore dstore = new Dstore();
                dstore.index = reader.readShort();
                this.modifiedInstruction = dstore;
            case 0x3a:
                Astore astore = new Astore();
                astore.index = reader.readShort();
                this.modifiedInstruction = astore;
            case (byte) 0x84:
                Iinc iinc = new Iinc();
                iinc.index = reader.readShort();
                this.modifiedInstruction = iinc;
            case (byte) 0xa9: // ret
                throw new RuntimeException("Unsupported opcode: 0xa9!");
        }
    }

    //执行指令没有变化
    @Override
    public void execute(Frame frame) {
        this.modifiedInstruction.execute(frame);
    }
}
