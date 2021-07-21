package com.jyvm.cmd;

import java.util.List;

public class CmdParse {

    //版本号信息
    public static boolean help;
    public static boolean VMFlag;
    public static  boolean version ;
    public static  String Xjre;
    public static  String classpath;
    public static List<String> appArgs;


    public static void parse(String[] args) {
        try {
            for (int i = 0; i < args.length; i ++) {
                if (args[i].equals("-version")) {
                    version = true;
                    return;
                }
                if (args[i].equals("-help") || args[i].equals("-?")) {
                    help = true;
                    return;
                }
                if (args[i].equals("-Xjre")) {
                    i++;
                    Xjre = args[i];
                }
                if (args[i].equals("-cp") || args[i].equals("-classpath")) {
                    VMFlag = true;
                    i++;
                    classpath = args[i];
                }
//                if(VMFlag) {
//                    appArgs.add(args[i]);
//                }
            }
//            if (appArgs.size()>=1) VMFlag = true;
        } catch (Exception e) {
            System.out.println("输入的参数有误!");
        }

    }
}
