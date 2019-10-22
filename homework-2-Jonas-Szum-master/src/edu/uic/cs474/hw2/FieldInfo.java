// You should not change this file
package edu.uic.cs474.hw2;

import java.util.Objects;

public class FieldInfo implements Comparable<FieldInfo> {
    public String declarerClassName;
    public String name;
    public String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldInfo fieldInfo = (FieldInfo) o;
        return Objects.equals(declarerClassName, fieldInfo.declarerClassName) &&
                Objects.equals(name, fieldInfo.name) &&
                Objects.equals(type, fieldInfo.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declarerClassName, name, type);
    }

    @Override
    public String toString() {
        return "FieldInfo{" +
                "declarerClassName='" + declarerClassName + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int compareTo(FieldInfo o) {
        int ret;
        ret = declarerClassName.compareTo(o.declarerClassName);
        if (ret != 0) return ret;
        ret = name.compareTo(o.name);
        if (ret != 0) return ret;
        return type.compareTo(o.type);
    }
}
