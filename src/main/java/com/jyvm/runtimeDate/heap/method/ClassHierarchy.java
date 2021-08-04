package com.jyvm.runtimeDate.heap.method;

/**
 *
 */
public class ClassHierarchy {

    private Class clazz;

    public static ClassHierarchy util(Class clazz) {
        ClassHierarchy classHierarchy = new ClassHierarchy();
        classHierarchy.clazz = clazz;
        return classHierarchy;
    }

    public boolean isAssignableFrom(Class other) {
        Class s = this.clazz;

        if (s == other) {
            return true;
        }

        if (!s.isArray()) {
            if (!s.isInterface()) {
                if (!other.isInterface()) {
                    return s.isSubClassOf(other);
                } else {
                    return isImplements(other);
                }
            } else {
                if (!other.isInterface()) {
                    return other.isJlObject();
                } else {
                    return other.isSubInterfaceOf(s);
                }
            }
        } else {
            if (!other.isArray()) {

                if (!other.isInterface()) {
                    return other.isJlObject();
                } else {
                    return other.isJlCloneable() || other.isJioSerializable();
                }
            } else {
                Class sc = s.componentClass();
                Class tc = other.componentClass();
                return sc == tc || tc.isAssignableFrom(sc);
            }
        }
    }

    public boolean isImplements(Class iface) {
        for (Class c = this.clazz; c != null; c = c.superClass) {
            for (Class i : c.interfaces) {
                if (i == iface || i.isSubInterfaceOf(iface)) {
                    return true;
                }
            }
        }
        return false;
    }

}
