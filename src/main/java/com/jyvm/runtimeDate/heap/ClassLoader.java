package com.jyvm.runtimeDate.heap;

import com.jyvm.classfile.ClassFile;
import com.jyvm.classfile.ConstantPool;
import com.jyvm.classpath.Classpath;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Field;
import com.jyvm.runtimeDate.heap.method.Object;
import com.jyvm.runtimeDate.heap.method.Slots;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
类加载器，用于加载相关类，是运行时类加载器，将类名和类实例以键值对存储
若能够根据类名ClassName找到Class对象则返回，
若未找到否则进行classpath下的文件的类加载
class names:
    - primitive types: boolean, byte, int ...
    - primitive arrays: [Z, [B, [I ...
    - non-array classes: java/lang/Object ...
    - array classes: [Ljava/lang/Object; ...
*/
public class ClassLoader {

    Classpath classpath;
    Map<String, Class> classMap; //存储已经被加载过的类对象引用

    public ClassLoader(Classpath classpath) {
        this.classpath = classpath;
        this.classMap = new HashMap<>();
    }

    public Class loadClass(String className) {
        Class clazz = classMap.get(className);
        if (null != clazz) {
            return clazz;
        }
        //若为数组则进行数组类加载和创建
        if (className.getBytes()[0] == '[') {
            return this.loadArrayClass(className);
        }
        //普通类创建
        try {
            return this.loadNonClass(className);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Class loadNonClass(String className) throws IOException, ClassNotFoundException {
        byte[] date = this.classpath.readClass(className);
        if (null == date) {
            throw new ClassNotFoundException(className);
        }
        Class clazz = defineClass(date); //将class的相关类都进行加载处理
        link(clazz);
        return clazz;
    }


    private Class loadArrayClass(String className) {
        Class clazz = new Class(AccessFlag.ACC_PUBLIC,
                className,
                this,
                true,
                this.loadClass("java/lang/Object"),
                new Class[]{this.loadClass("java/lang/Cloneable"), this.loadClass("java/io/Serializable")});
        this.classMap.put(className, clazz);
        return clazz;
    }

    public void link(Class clazz) {
        verify(clazz);
        prepare(clazz);
    }

    public void verify(Class clazz) {
        //在代码执行前对类进行严格的验证操作
    }

    public void prepare(Class clazz) {
        calcInstanceFieldSlotIds(clazz); //计算出实例字段的个数，属于实例的字段
        calcStaticField(clazz); //计算静态字段的数量，专属于类对象的字段
        allocAndInitStaticVars(clazz); //为类对象和字段分配空间，
    }

    public Class defineClass(byte[] data) {
        Class clazz = parseClass(data);
        clazz.loader = this;
        resolveSuperClass(clazz); //加载其父类
        resolveInterfaces(clazz); //将改类的所有接口都加载到Class类中存储
        this.classMap.put(clazz.name, clazz);
        return clazz;
    }

    public Class parseClass(byte[] data) {
        ClassFile cf = new ClassFile(data);
        return new Class(cf);
    }

    public void resolveInterfaces(Class clazz) {
        int size = clazz.interfaceNames.length;
        if (size > 0) {
            clazz.interfaces = new Class[size];
            for (int i = 0; i < size; i ++) {
                clazz.interfaces[i] = clazz.loader.loadClass(clazz.interfaceNames[i]);
            }
        }
    }

    public void resolveSuperClass(Class clazz) {
        if (!clazz.name.equals("java/lang/Object")) {
            clazz.superClass = clazz.loader.loadClass(clazz.fatherClassName);
        }
    }

    public void calcInstanceFieldSlotIds(Class clazz) {
        int slotId = 0;
        if (clazz.superClass != null) {
            slotId = clazz.superClass.instanceSlotCount;
        }
        for (Field field : clazz.fields) {
            if (!field.isStatic()) {
                field.slotIndex = slotId;
                slotId ++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.instanceSlotCount = slotId;
    }

    public void calcStaticField(Class clazz) {
        int slotId = 0;
        for (Field field : clazz.fields) {
            if (field.isStatic()) {
                slotId ++;
                if (field.isLongOrDouble()) {
                    slotId ++;
                }
            }
        }
        clazz.staticSlotCount = slotId;
    }

    public void allocAndInitStaticVars(Class clazz) {
        clazz.staticVars = new Slots(clazz.staticSlotCount);
        for (Field field : clazz.fields) {
            if (field.isStatic() && field.isFinal()) {
                initStaticFinalVar(clazz,field); //static final修饰的属性
            }
        }
    }

    public void initStaticFinalVar(Class clazz, Field field) {
        Slots staticVars = clazz.staticVars;
        RuntimePool pool = clazz.runtimePool;
        int cpIdx = field.poolIndex;
        int slotId = field.slotIndex;

        if (cpIdx > 0) {
            switch (field.desc()) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    java.lang.Object val = pool.getConstant(cpIdx);
                    staticVars.setInt((Integer) val, slotId );
                    break;
                case "J":
                    staticVars.setLong((Long)pool.getConstant(cpIdx),slotId);
                    break;
                case "F":
                    staticVars.setFloat((Float) pool.getConstant(cpIdx), slotId);
                    break;
                case "D":
                    staticVars.setDouble((Double) pool.getConstant(cpIdx), slotId);
                    break;
                case "Ljava/lang/String;":
                    String getStr = (String) pool.getConstant(slotId);
                    Object reStr = StringPool.jString(clazz.loader, getStr);
                    staticVars.setRef(reStr, slotId);
                    break;
            }
        }
    }


}
