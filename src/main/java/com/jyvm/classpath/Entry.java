package com.jyvm.classpath;

import java.io.File;
import java.io.IOException;

/*
* 类路径接口，用于类查找的入口
* */
public interface Entry {

    //读取class文件的字节数据
    byte[] readClass(String className) throws IOException;

    //工厂方法进行对类的查找
    static Entry createEntry(String path) {
        if (path.contains(File.pathSeparator)) {
            return new CompositeEntry(path);
        }
        if (path.endsWith(".jar") || path.endsWith(".zip")
                || path.endsWith(".JAR") || path.endsWith(".ZIP")) {
            return new JarEntry(path);
        }
        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }
        return new DirEntry(path);
    }
}
