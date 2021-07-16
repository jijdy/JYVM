package com.jyvm.cmd;

import com.jyvm.classpath.Classpath;

import java.util.Arrays;

public class RunMain {

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
        System.out.println("虚拟机运行开始----");
        Classpath cp = new Classpath(CmdParse.Xjre,CmdParse.classpath);

        String className = CmdParse.classpath;
        System.out.println(className);
        try {
            byte[] classDate = cp.readClass(CmdParse.classpath);
//            System.out.println(Arrays.toString(classDate));
            for(byte b : classDate) {
                System.out.print(String.format("%02x", b & 0xff) + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
