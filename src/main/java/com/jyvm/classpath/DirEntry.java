package com.jyvm.classpath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirEntry implements Entry{

    private final Path absolutePath ;

    DirEntry(String className) {
        this.absolutePath = Paths.get(className).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        //通过绝对路径加class文件的名称拿到文件位置并进行读取
        Path p = Paths.get(className).toAbsolutePath();

        return Files.readAllBytes(absolutePath.resolve(p));
    }

    @Override
    public String toString() {
        return "DirEntry{" +
                "absolutePath=" + absolutePath +
                '}';
    }
}
