package com.jyvm.runtimeDate.heap.method;

import com.jyvm.classfile.ClassFile;
import com.jyvm.classfile.attribute.Implements.SourceFileAttribute;
import com.jyvm.runtimeDate.Slot;
import com.jyvm.runtimeDate.heap.AccessFlag;
import com.jyvm.runtimeDate.heap.ClassLoader;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;

public class Class {

    public int accessFlags;
    public String name;   //完全限定名，为java/lang/String这样的形式
    public String fatherClassName;
    public String[] interfaceNames;
    public RuntimePool runtimePool;
    public Field[] fields;
    public Method[] methods;
    public ClassLoader loader;
    public String sourceFile;
    public Class superClass;
    public Class[] interfaces;
    public int instanceSlotCount;
    public int staticSlotCount;
    public Slots staticVars;
    public boolean initStarted;
    public Object jClass;

    public Class(ClassFile classFile) {
        this.accessFlags = classFile.accessFlag();
        this.name = classFile.className();
        this.fatherClassName = classFile.fatherName();
        this.interfaceNames = classFile.getInterfaceName();
        this.runtimePool = new RuntimePool(this,classFile.constantPool());
        this.fields = Field.fields(this, classFile.fields());
        this.methods = new Method().newMethods(this, classFile.methods());
        this.sourceFile = getSourceFile(classFile);
    }

    //寻找源文件的文件名称
    public String getSourceFile(ClassFile classFile) {
        SourceFileAttribute sourceFileAttribute = classFile.sourceFileAttribute();
        if (null == sourceFileAttribute) return "Unknown";
        return sourceFileAttribute.sourceName();
    }

    //用于加载数组对象的构造函数
    public Class(int accessFlags, String name, ClassLoader loader, boolean initStarted, Class superClass, Class[] interfaces) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.loader = loader;
        this.initStarted = initStarted;
        this.superClass = superClass;
        this.interfaces = interfaces;
    }

        public boolean isPublic() {
        return  0 != (this.accessFlags & AccessFlag.ACC_PUBLIC);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags & AccessFlag.ACC_FINAL);
    }

    public boolean isSuper() {
        return 0 != (this.accessFlags & AccessFlag.ACC_SUPER);
    }

    public boolean isInterface() {
        return 0 != (this.accessFlags & AccessFlag.ACC_INTERFACE);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags & AccessFlag.ACC_SYNTHETIC);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags & AccessFlag.ACC_ABSTRACT);
    }

    public boolean isAnnotation() {
        return 0 != (this.accessFlags & AccessFlag.ACC_ANNOTATION);
    }

    public boolean isEnum() {
        return 0 != (this.accessFlags & AccessFlag.ACC_ENUM);
    }

    //判断是否为可访问的，为public或在同一个包下
    public boolean isAccessibleTo(Class clazz) {
        return this.isPublic() || this.getPackageName().equals(clazz.getPackageName());
    }

    public boolean isAssignableFrom(Class clazz) {
        if (this == clazz) {
            return true;
        }
        if (!clazz.isInterface()) {
            return this.isSubClassOf(clazz);
        } else {
            return this.isImplements(clazz);
        }
    }

    public boolean isSubClassOf(Class clazz) {
        for (Class c = this.superClass; c != null; c = c.superClass) {
            if (c == clazz) {
                return true;
            }
        }
        return false;
    }

    //查找所有的接口，包括父类接口
    public boolean isImplements(Class clazz) {
        for (Class c = this; c != null; c = c.superClass) {
            for (Class cl : c.interfaces) {
                if (cl == clazz || clazz.isSubInterfaceOf(clazz)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSubInterfaceOf(Class clazz) {
        for (Class superInterface : this.interfaces) {
            if (superInterface == clazz || superInterface.isSubInterfaceOf(clazz)) {
                return true;
            }
        }
        return false;
    }

    public boolean initStarted() {
        return this.initStarted;
    }

    public void startInit() {
        this.initStarted = true;
    }


    public String getPackageName() {
        int i = this.name.lastIndexOf("/");
        if (i > 0) {
            return this.name;
        }
        return "";
    }

    public Method getMainMethod() {
        return this.getStaticMethod("main","([Ljava/lang/String;)V", true);
    }

    public Method getStaticMethod(String name, String desc, boolean isStatic) {
        for (Class c = this; c != null; c = c.superClass) {
            if (null == c.methods) continue;
            for (Method method : c.methods) {
                if (method.isStatic() == isStatic && method.name.equals(name) && method.desc.equals(desc)) {
                    return method;
                }
            }
        }
        return null;
    }

    public Method getClinitMethod() {
        return this.getStaticMethod("<clinit>","()V",true);
    }

    public Object object() {
        return new Object(this);
    }

    public Field getField(String name, String descriptor, boolean isStatic) {
        for (Class c = this; c != null; c = c.superClass) {
            for (Field field : c.fields) {
                if (field.isStatic() == isStatic &&
                        field.name.equals(name) &&
                        field.desc.equals(descriptor)) {
                    return field;
                }
            }
        }
        return null;
    }

    public boolean isJlObject() {
        return this.name.equals("java/lang/Object");
    }

    public boolean isJlCloneable() {
        return this.name.equals("java/lang/Cloneable");
    }

    public boolean isJioSerializable() {
        return this.name.endsWith("java/io/Serializable");
    }

    public Object newObject() {
        return new Object(this);
    }

    public Class arrayClass() {
        String arrayClassName = ClassNameHelper.getArrayClassName(this.name);
        return this.loader.loadClass(arrayClassName);
    }

    public boolean isArray() {
        return this.name.getBytes()[0] == '[';
    }

    //拿到数组类名，再进行类加载找到该类
    public Class componentClass() {
        String componentClassName = ClassNameHelper.getComponentClassName(this.name);
        return this.loader.loadClass(componentClassName);
    }

    public Object newArray(int count) {
        if (!this.isArray()) {
            throw new RuntimeException("Not array class " + this.name);
        }
        switch (this.name) {
            case "[Z":
                return new Object(this, new byte[count]);
            case "[B":
                return new Object(this, new byte[count]);
            case "[C":
                return new Object(this, new char[count]);
            case "[S":
                return new Object(this, new short[count]);
            case "[I":
                return new Object(this, new int[count]);
            case "[J":
                return new Object(this, new long[count]);
            case "[F":
                return new Object(this, new float[count]);
            case "[D":
                return new Object(this, new double[count]);
            default:
                return new Object(this, new Object[count]);
        }
    }
    //本地方法在首位获取到类名
    public String javaName() {
        return this.name.charAt(0) + this.name.substring(1).replace("/", ".");

    }

    public boolean isPrimitive() {
        return null != ClassNameHelper.primitiveTypes.get(this.name);
    }

    public Object getRefVar(String fieldName, String fieldDesc) {
        Field field = this.getField(fieldName, fieldDesc, true);
        return (Object) this.staticVars.getRef(field.slotIndex);
    }

    public Method getInstanceMethod(String name, String desc) {
        return this.getStaticMethod(name, desc, false);
    }

    public String sourceFile() {
        return this.sourceFile;
    }
}
