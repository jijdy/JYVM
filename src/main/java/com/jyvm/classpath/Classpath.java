package com.jyvm.classpath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
*  用于解析运行环境所必须的启动类，扩展类和用户类的class文件
**/
public class Classpath {

    private Entry bootClasspath;
    private Entry extensionClasspath;
    private Entry userClasspath;

    public Classpath(String xjre, String classpath) {
        parseBootAndExtension(xjre);
        parseUser(classpath);
    }

    private void parseBootAndExtension(String xjre) {
        if(xjre == null) { //若没有手动传入jre包的位置，则在系统的环境变量中查找
            xjre = Paths.get(System.getenv("JAVA_HOME"),"jre").toString();
        }

        // jre\lib\*下的所有jar包进行查找
        bootClasspath = new WildcardEntry(Paths.get(xjre, "lib") + File.pathSeparator + "*");

        //对jre\lib\ext\*下的所有jar包进行查找
        extensionClasspath = new WildcardEntry(Paths.get(xjre, "lib", "ext") + File.pathSeparator + "*");
    }

    private void parseUser(String classpath) {
        if (classpath == null) {
            classpath = ".";
        }
        userClasspath = Entry.createEntry(classpath);
    }

    public byte[] readClass(String classpath) throws IOException {
        classpath = classpath + ".class";

        try {
            return bootClasspath.readClass(classpath);
        } catch (IOException e) {
            //
        }
        try {
            return extensionClasspath.readClass(classpath);
        } catch (IOException e) {
            //
        }
            return userClasspath.readClass(classpath);
    }


}

