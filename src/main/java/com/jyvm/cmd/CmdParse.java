package com.jyvm.cmd;

public class CmdParse {

    //版本号信息
    public static boolean help;
    public static boolean VMFlag;
    public static  boolean version ;
    public static  String Xjre;
    public static  String classpath;


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
                    VMFlag = true;
                    i++;
                    Xjre = args[i];
                }
                if (args[i].equals("-cp") || args[i].equals("-classpath")) {
                    VMFlag = true;
                    i++;
                    classpath = args[i].replace(".", "/");
                }
            }
        } catch (Exception e) {
            System.out.println("输入的参数有误!");
        }

    }
}
