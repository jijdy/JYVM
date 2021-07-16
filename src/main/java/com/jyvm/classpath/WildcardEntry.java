package com.jyvm.classpath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class WildcardEntry extends CompositeEntry{

    WildcardEntry(String path) {
        super(toPathList(path));
    }

    public static String toPathList(String Wildcard) {
        //提取出该文件所在的目录
        String baseDir = Wildcard.replace("*","");

        //将改目录下的所有.jar文件按照名称以间隔符组成一个String字符串
        try {
            return Files.walk(Paths.get(baseDir))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(path -> path.endsWith(".jar") || path.endsWith(".JAR"))
                    .collect(Collectors.joining(File.pathSeparator));
        } catch (IOException e) {
            //忽略。。
            return "Wild--";
        }
    }
}
