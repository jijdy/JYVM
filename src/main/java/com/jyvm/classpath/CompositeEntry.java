package com.jyvm.classpath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompositeEntry implements Entry{

    public static List<Entry> entryList = new ArrayList<>();

    CompositeEntry(String pathList) {
        String[] classes = pathList.split(File.pathSeparator);
        for (String clazz : classes) {
            entryList.add(Entry.createEntry(clazz));
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        //逐个读取在命令行中写入的class文件地址
        for (Entry entry : entryList) {
            try {
                return entry.readClass(className);
            } catch (IOException e) {
                //忽略异常
            }
        }
        throw new IOException("class not find : " + className);
    }


}
