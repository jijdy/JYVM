import com.jyvm.classfile.ClassFile;
import com.jyvm.classfile.FieldOrMethodInfo;
import com.jyvm.classpath.Classpath;
import com.jyvm.cmd.CmdParse;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.LocalVars;
import com.jyvm.runtimeDate.OperandStack;

import java.util.Arrays;

public class Main {

    //程序主入口，即进入虚拟机的入口
    public static void main(String[] args) {
        CmdParse.parse(args);
        if (CmdParse.help) {
            System.out.println("-----这里是帮助信息----");
            return;
        }
        if (CmdParse.version) {
            System.out.println("version: 0.0.1");
            return;
        }
        if (CmdParse.VMFlag) {
            startVM();
        }
    }

    private static void startVM() {
        Frame frame = new Frame(100,100);
        test_localVars(frame.getLocalVars());
        test_operandStack(frame.getOperandStack());
    }

    private static void test_localVars(LocalVars vars){
        vars.setInt(0,100);
        vars.setInt(1,-100);
        System.out.println(vars.getInt(0));
        System.out.println(vars.getInt(1));
    }

    private static void test_operandStack(OperandStack ops){
        ops.pushInt(100);
        ops.pushInt(-100);
        ops.pushRef(null);
        System.out.println(ops.popRef());
        System.out.println(ops.popInt());
    }
    private static void startVM1() {
        System.out.println("虚拟机运行开始----");
        Classpath cp = new Classpath(CmdParse.Xjre,CmdParse.classpath);

        String className = CmdParse.classpath;
        try {
            byte[] classDate = cp.readClass(className);
            ClassFile classFile = new ClassFile(classDate);
            printInfo(classFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void printInfo(ClassFile cf) {
        System.out.println("version: " + cf.getMajorVersion() + "." + cf.getMinorVersion());
        System.out.println("constants count：" + cf.constantPool().getSize());
        System.out.format("access flags：0x%x\n", cf.accessFlag());
        System.out.println("this class：" + cf.className());
        System.out.println("super class：" + cf.fatherName());
        System.out.println("interfaces：" + Arrays.toString(cf.getInterfaceName()));
        System.out.println("fields count：" + cf.fields().length);
        for (FieldOrMethodInfo memberInfo : cf.fields()) {
            System.out.format("%s \t\t %s\n", memberInfo.getName(), memberInfo.getDesc());
        }

        System.out.println("methods count: " + cf.methods().length);
        for (FieldOrMethodInfo memberInfo : cf.methods()) {
            System.out.format("%s \t\t %s\n", memberInfo.getName(), memberInfo.getDesc());
        }
    }





//        private static void startVM() {
//        System.out.println("虚拟机运行开始----");
//        Classpath cp = new Classpath(CmdParse.Xjre,CmdParse.classpath);
//
//        String className = CmdParse.classpath;
//        try {
//            byte[] classDate = cp.readClass(CmdParse.classpath);
//            for(byte b : classDate) {
//                System.out.print(String.format("%02x", b & 0xff) + " ");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


}
