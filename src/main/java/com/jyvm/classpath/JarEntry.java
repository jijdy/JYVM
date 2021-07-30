package com.jyvm.classpath;

import java.io.IOException;
import java.nio.file.*;

public class JarEntry implements Entry{

    private final Path absPath;

    JarEntry(String path) {
        absPath = Paths.get(path).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        try (FileSystem fs = FileSystems.newFileSystem(absPath,null);) {
            return Files.readAllBytes(fs.getPath(className));
        }
    }
}
