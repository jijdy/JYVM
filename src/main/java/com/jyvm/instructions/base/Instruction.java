package com.jyvm.instructions.base;

import com.jyvm.instructions.comparison.dcmp.Dcmpg;
import com.jyvm.instructions.comparison.dcmp.Dcmpl;
import com.jyvm.instructions.comparison.fcmp.Fcmpg;
import com.jyvm.instructions.comparison.fcmp.Fcmpl;
import com.jyvm.instructions.comparison.if_acmp.If_acmpeq;
import com.jyvm.instructions.comparison.if_acmp.If_acmpne;
import com.jyvm.instructions.comparison.if_icmp.*;
import com.jyvm.instructions.comparison.ifcond.*;
import com.jyvm.instructions.comparison.lcmp.Lcmp;
import com.jyvm.instructions.constants.*;
import com.jyvm.instructions.constants.consts.*;
import com.jyvm.instructions.control.Goto;
import com.jyvm.instructions.control.LookUpSwitch;
import com.jyvm.instructions.control.Tableswitch;
import com.jyvm.instructions.conversion.d2x.D2f;
import com.jyvm.instructions.conversion.d2x.D2i;
import com.jyvm.instructions.conversion.d2x.D2l;
import com.jyvm.instructions.conversion.f2x.F2d;
import com.jyvm.instructions.conversion.f2x.F2i;
import com.jyvm.instructions.conversion.f2x.F2l;
import com.jyvm.instructions.conversion.i2x.*;
import com.jyvm.instructions.conversion.l2x.L2d;
import com.jyvm.instructions.conversion.l2x.L2f;
import com.jyvm.instructions.conversion.l2x.L2i;
import com.jyvm.instructions.extended.Goto_w;
import com.jyvm.instructions.extended.Ifnonnull;
import com.jyvm.instructions.extended.Ifnull;
import com.jyvm.instructions.extended.Wide;
import com.jyvm.instructions.loads.aloads.*;
import com.jyvm.instructions.loads.dloads.*;
import com.jyvm.instructions.loads.floads.*;
import com.jyvm.instructions.loads.iloads.*;
import com.jyvm.instructions.loads.lloads.Lload_0;
import com.jyvm.instructions.loads.lloads.Lload_1;
import com.jyvm.instructions.loads.lloads.Lload_2;
import com.jyvm.instructions.loads.lloads.Lload_3;
import com.jyvm.instructions.math.add.Dadd;
import com.jyvm.instructions.math.add.Fadd;
import com.jyvm.instructions.math.add.Iadd;
import com.jyvm.instructions.math.add.Ladd;
import com.jyvm.instructions.math.bitbool.and.Iand;
import com.jyvm.instructions.math.bitbool.and.Land;
import com.jyvm.instructions.math.bitbool.or.Ior;
import com.jyvm.instructions.math.bitbool.or.Lor;
import com.jyvm.instructions.math.bitbool.xor.Ixor;
import com.jyvm.instructions.math.bitbool.xor.Lxor;
import com.jyvm.instructions.math.div.Ddiv;
import com.jyvm.instructions.math.div.Fdiv;
import com.jyvm.instructions.math.div.Idiv;
import com.jyvm.instructions.math.div.Ldiv;
import com.jyvm.instructions.math.iinc.Iinc;
import com.jyvm.instructions.math.mul.Dmul;
import com.jyvm.instructions.math.mul.Fmul;
import com.jyvm.instructions.math.mul.Imul;
import com.jyvm.instructions.math.mul.Lmul;
import com.jyvm.instructions.math.negate.Dneg;
import com.jyvm.instructions.math.negate.Fneg;
import com.jyvm.instructions.math.negate.Ineg;
import com.jyvm.instructions.math.negate.Lneg;
import com.jyvm.instructions.math.remainder.Drem;
import com.jyvm.instructions.math.remainder.Frem;
import com.jyvm.instructions.math.remainder.Irem;
import com.jyvm.instructions.math.remainder.Lrem;
import com.jyvm.instructions.math.shift.*;
import com.jyvm.instructions.math.sub.Dsub;
import com.jyvm.instructions.math.sub.Fsub;
import com.jyvm.instructions.math.sub.Isub;
import com.jyvm.instructions.math.sub.Lsub;
import com.jyvm.instructions.references.*;
import com.jyvm.instructions.stack.Swap;
import com.jyvm.instructions.stack.dup.*;
import com.jyvm.instructions.stack.pop.Pop;
import com.jyvm.instructions.stack.pop.Pop2;
import com.jyvm.instructions.stores.astore.*;
import com.jyvm.instructions.stores.dstore.*;
import com.jyvm.instructions.stores.fstore.*;
import com.jyvm.instructions.stores.istore.*;
import com.jyvm.instructions.stores.lstore.*;
import com.jyvm.runtimeDate.Frame;
import com.sun.org.apache.bcel.internal.generic.*;

public interface Instruction {

    void fetchOperands(BytecodeReader reader);

    void execute(Frame frame);

    static void branch(Frame frame, int offset) {
       int pc = frame.thread().getPC();
       int nextPC = pc + offset;
       frame.setNextPC(nextPC);
    }

     static Instruction newInstruction(byte option) {
        switch (option) {
            case 0x00:
                return new NoOperandsInstruction(); //什么都不做
            case 0x01:
                return new Aconst_null();  // 将null推入到栈顶
            case 0x02:
                return new Iconst_m1();   // 将int型-1推入栈顶
            case 0x03:
                return new Iconst_0();    // 将int型0推入栈顶
            case 0x04:
                return new Iconst_1();    // 将1推入栈顶
            case 0x05:
                return new Iconst_2();    // 将2如栈顶
            case 0x06:
                return new Iconst_3();
            case 0x07:
                return new Iconst_4();
            case 0x08:
                return new Iconst_5();
            case 0x09:
                return new Lconst_0();   // 将long类型0推入栈顶
            case 0x0a:
                return new Lconst_1();
            case 0x0b:
                return new Fconst_0();  // 将float类型0推入栈顶
            case 0x0c:
                return new Fconst_1();
            case 0x0d:
                return new Fconst_2();
            case 0x0e:
                return new Dconst_0();  // 将double型0推入栈顶
            case 0x0f:
                return new Dconst_1();
            case 0x10:
                return new BIpush();  // 将一个byte型数据转为int入栈
            case 0x11:
                return new SIpush();   // 将一个short型数据转为int入栈
            case 0x12:
                return new Ldc();   //将int，float或String从运行时常量池推到栈顶
            case 0x13:
                return new Ldc_w();  //同上，wide宽索引
            case 0x14:
                return new Ldc_2w();  // 将long或double从常量池推如栈顶
            case 0x15:
                return new Iload();  // 将指定的int型数据入栈
            case 0x16:
                return new Iload_0(); // 将局部变量表中指定位置的int型入栈
            case 0x17:
                return new Fload();
            case 0x18:
                return new Dload();
            case 0x19:
                return new Aload();  // 引用类型从常量池加载到栈顶
            case 0x1a:
                return new Iload_0();
            case 0x1b:
                return new Iload_1();
            case 0x1c:
                return new Iload_2();
            case 0x1d:
                return new Iload_3();
            case 0x1e:
                return new Lload_0();
            case 0x1f:
                return new Lload_1();
            case 0x20:
                return new Lload_2();
            case 0x21:
                return new Lload_3();
            case 0x22:
                return new Fload_0();
            case 0x23:
                return new Fload_1();
            case 0x24:
                return new Fload_2();
            case 0x25:
                return new Fload_3();
            case 0x26:
                return new Dload_0();
            case 0x27:
                return new Dload_1();
            case 0x28:
                return new Dload_2();
            case 0x29:
                return new Dload_3();
            case 0x2a:
                return new Aload_0();
            case 0x2b:
                return new Aload_1();
            case 0x2c:
                return new Aload_2();
            case 0x2d:
                return new Aload_3();
            // case 0x2e:
            // 	return iaload
            // case 0x2f:
            // 	return laload
            // case 0x30:
            // 	return faload
            // case 0x31:
            // 	return daload
            // case 0x32:
            // 	return aaload
            // case 0x33:
            // 	return baload
            // case 0x34:
            // 	return caload
            // case 0x35:
            // 	return saload
            case 0x36:
                return new Istore(); //将栈顶元素存储到局部变量表
            case 0x37:
                return new Lstore();
            case 0x38:
                return new Fstore();
            case 0x39:
                return new Dstore();
            case 0x3a:
                return new Astore();
            case 0x3b:
                return new Istore_0();
            case 0x3c:
                return new Istore_1();
            case 0x3d:
                return new Istore_2();
            case 0x3e:
                return new Istore_3();
            case 0x3f:
                return new Lstore_0();
            case 0x40:
                return new Lstore_1();
            case 0x41:
                return new Lstore_2();
            case 0x42:
                return new Lstore_3();
            case 0x43:
                return new Fstore_0();
            case 0x44:
                return new Fstore_1();
            case 0x45:
                return new Fstore_2();
            case 0x46:
                return new Fstore_3();
            case 0x47:
                return new Dstore_0();
            case 0x48:
                return new Dstore_1();
            case 0x49:
                return new Dstore_2();
            case 0x4a:
                return new Dstore_3();
            case 0x4b:
                return new Astore_0();
            case 0x4c:
                return new Astore_1();
            case 0x4d:
                return new Astore_2();
            case 0x4e:
                return new Astore_3();
            // case 0x4f:
            // 	return iastore
            // case 0x50:
            // 	return lastore
            // case 0x51:
            // 	return fastore
            // case 0x52:
            // 	return dastore
            // case 0x53:
            // 	return aastore
            // case 0x54:
            // 	return bastore
            // case 0x55:
            // 	return castore
            // case 0x56:
            // 	return sastore
            case 0x57:
                return new Pop(); //将栈顶元素弹出
            case 0x58:
                return new Pop2();  //将占有两个位置的long或double类型弹出
            case 0x59:
                return new Dup();  //复制栈顶匀速并推入栈顶
            case 0x5a:
                return new Dup_x1();
            case 0x5b:
                return new Dup_x2();
            case 0x5c:
                return new Dup2();
            case 0x5d:
                return new Dup2_x1();
            case 0x5e:
                return new Dup2_x2();
            case 0x5f:
                return new Swap(); //将栈顶的两个元素进行交换
            case 0x60:
                return new Iadd(); //将栈顶两个int相加并入栈
            case 0x61:
                return new Ladd();
            case 0x62:
                return new Fadd();
            case 0x63:
                return new Dadd();
            case 0x64:
                return new Isub(); //将栈顶两个int型相减并入栈
            case 0x65:
                return new Lsub();
            case 0x66:
                return new Fsub();
            case 0x67:
                return new Dsub();
            case 0x68:
                return new Imul();
            case 0x69:
                return new Lmul();
            case 0x6a:
                return new Fmul();
            case 0x6b:
                return new Dmul();
            case 0x6c:
                return new Idiv(); //将栈顶两个int型相除并入栈
            case 0x6d:
                return new Ldiv();
            case 0x6e:
                return new Fdiv();
            case 0x6f:
                return new Ddiv();
            case 0x70:
                return new Irem(); // 对栈顶两个int型取摸并入栈
            case 0x71:
                return new Lrem();
            case 0x72:
                return new Frem();
            case 0x73:
                return new Drem();
            case 0x74:
                return new Ineg(); //栈顶元素取负
            case 0x75:
                return new Lneg();
            case 0x76:
                return new Fneg();
            case 0x77:
                return new Dneg();
            case 0x78:
                return new Ishl(); //将int型左移指定位数并入栈
            case 0x79:
                return new Lshl();
            case 0x7a:
                return new Ishr();
            case 0x7b:
                return new Lshr();
            case 0x7c:
                return new Iushr();
            case 0x7d:
                return new Lushr();
            case 0x7e:
                return new Iand(); // 逻辑位与运算
            case 0x7f:
                return new Land();
            case (byte) 0x80:
                return new Ior();
            case (byte) 0x81:
                return new Lor();
            case (byte) 0x82:
                return new Ixor();
            case (byte) 0x83:
                return new Lxor();
            case (byte) 0x84:
                return new Iinc(); //将指定的int型变量增加指定值入栈
            case (byte) 0x85:
                return new I2l(); //强制转型
            case (byte) 0x86:
                return new I2f();
            case (byte) 0x87:
                return new I2d();
            case (byte) 0x88:
                return new L2i();
            case (byte) 0x89:
                return new L2f();
            case (byte) 0x8a:
                return new L2d();
            case (byte) 0x8b:
                return new F2i();
            case (byte) 0x8c:
                return new F2l();
            case (byte) 0x8d:
                return new F2d();
            case (byte) 0x8e:
                return new D2i();
            case (byte) 0x8f:
                return new D2l();
            case (byte) 0x90:
                return new D2f();
            case (byte) 0x91:
                return new I2b();
            case (byte) 0x92:
                return new I2c();
            case (byte) 0x93:
                return new I2s();
            case (byte) 0x94:
                return new Lcmp();  //比较栈顶两个long类型元素的大小并入栈
            case (byte) 0x95:
                return new Fcmpl();
            case (byte) 0x96:
                return new Fcmpg();
            case (byte) 0x97:
                return new Dcmpl();
            case (byte) 0x98:
                return new Dcmpg();
            case (byte) 0x99:
                return new Ifeq(); // 判断栈顶int型是否等于0
            case (byte) 0x9a:
                return new Ifne();
            case (byte) 0x9b:
                return new Iflt();
            case (byte) 0x9c:
                return new Ifge();
            case (byte) 0x9d:
                return new Ifgt();
            case (byte) 0x9e:
                return new Ifle();  //栈顶元素小与或等于0时跳转指令
            case (byte) 0x9f:
                return new If_acmpeq(); //比较栈顶两个元素是否相等，执行跳转指令
            case (byte) 0xa0:
                return new If_acmpne();
            case (byte) 0xa1:
                return new If_icmplt();
            case (byte) 0xa2:
                return new If_icmpge();
            case (byte) 0xa3:
                return new If_icmpgt();
            case (byte) 0xa4:
                return new If_icmple();
            case (byte) 0xa5:
                return new If_acmpeq(); //判断两个引用类型是否相等，执行跳转指令
            case (byte) 0xa6:
                return new If_acmpne();
            case (byte) 0xa7:
                return new Goto();
            // case 0xa8:
            // 	return &JSR{}
            // case 0xa9:
            // 	return &RET{}
            case (byte) 0xaa:
                return new Tableswitch(); //switch-case，case连续的情况
            case (byte) 0xab:
                return new LookUpSwitch();
            // case 0xac:
            // 	return ireturn
            // case 0xad:
            // 	return lreturn
            // case 0xae:
            // 	return freturn
            // case 0xaf:
            // 	return dreturn
            // case 0xb0:
            // 	return areturn
//             case (byte) 0xb1:
//             	return new Return;
            case (byte) 0xb2:
                return new Get_static(); //获取指定类的静态属性，并将其写入栈顶
             case (byte) 0xb3:
             	return new Put_static(); //从栈顶弹出元素给某个列的静态匀速复制，若为final则特殊考虑
             case (byte) 0xb4:
             	return new Get_field(); //获取指定类的字段属性，并推入到栈顶
             case (byte) 0xb5:
             	return new Put_field(); //从栈顶弹出元素赋给某个类的属性值
            case (byte) 0xb6:
                return new Invoke_virtual(); //调用实例方法
             case (byte) 0xb7:
             	return new Invoke_special(); //调用父类构造方法等
            // case 0xb8:
            // 	return &INVOKE_STATIC{}
            // case 0xb9:
            // 	return &INVOKE_INTERFACE{}
            // case 0xba:
            // 	return &INVOKE_DYNAMIC{}
             case (byte) 0xbb:
             	return new New();  //将类Class对象引用直接推入到栈顶
            // case 0xbc:
            // 	return &NEW_ARRAY{}
            // case 0xbd:
            // 	return &ANEW_ARRAY{}
            // case 0xbe:
            // 	return arraylength
            // case 0xbf:
            // 	return athrow
            // case 0xc0:
            // 	return &CHECK_CAST{}
             case (byte) 0xc1:
             	return new Instance_of();
            // case 0xc2:
            // 	return monitorenter
            // case 0xc3:
            // 	return monitorexit
            case (byte) 0xc4:
                return new Wide(); //对从局部变量中获取数据时，局部变量超过了256时的扩展
            // case 0xc5:
            // 	return &MULTI_ANEW_ARRAY{}
            case (byte) 0xc6:
                return new Ifnull(); //为null时进行跳转操作
            case (byte) 0xc7:
                return new Ifnonnull();
            case (byte) 0xc8:
                return new Goto_w();
            // case 0xc9:
            // 	return &JSR_W{}
            // case 0xca: breakpoint
            // case 0xfe: impdep1
            // case 0xff: impdep2
            default:
                throw new RuntimeException("该指令尚未实现：" + option);
        }
    }

}
