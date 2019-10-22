// You should not change this file
package edu.uic.cs474.hw2;

import java.util.*;

public class ClassInfo implements Comparable<ClassInfo> {
    public String name;
    public Optional<ClassInfo> parent = Optional.empty();
    public TreeSet<ClassInfo> interfaces = new TreeSet<>();

    public TreeSet<FieldInfo> fields = new TreeSet<>();
    public TreeSet<MethodInfo> constructors = new TreeSet<>();
    public TreeSet<MethodInfo> methods = new TreeSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassInfo classInfo = (ClassInfo) o;
        return Objects.equals(name, classInfo.name) &&
                Objects.equals(parent, classInfo.parent) &&
                Objects.equals(interfaces, classInfo.interfaces) &&
                Objects.equals(fields, classInfo.fields) &&
                Objects.equals(constructors, classInfo.constructors) &&
                Objects.equals(methods, classInfo.methods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent, interfaces, fields, constructors, methods);
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "name='" + name + '\'' +
                ", parent=" + (parent.isPresent() ? parent : "N/A") +
                ", interfaces=" + interfaces +
                ", fields=" + fields +
                ", constructors=" + constructors +
                ", methods=" + methods +
                '}';
    }

    @Override
    public int compareTo(ClassInfo o) {
        int ret;

        ret = name.compareTo(o.name);
        if (ret != 0) return ret;

        if (parent.isPresent() && !o.parent.isPresent())
            return -1;
        else if (!parent.isPresent() && o.parent.isPresent())
            return 1;
        else if (parent.isPresent() && o.parent.isPresent()) {
            ret = parent.get().compareTo(o.parent.get());
            if (ret != 0) return ret;
        }

        ret = interfaces.toString().compareTo(o.interfaces.toString());
        if (ret != 0) return ret;

        ret = fields.toString().compareTo(o.fields.toString());
        if (ret != 0) return ret;

        ret = constructors.toString().compareTo(o.constructors.toString());
        if (ret != 0) return ret;

        return methods.toString().compareTo(o.methods.toString());
    }
}
