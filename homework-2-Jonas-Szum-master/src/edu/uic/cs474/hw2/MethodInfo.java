// You should not change this file
package edu.uic.cs474.hw2;

import java.util.*;

public class MethodInfo implements Comparable<MethodInfo> {
    public String declarerClassName;
    public Optional<String> name = Optional.empty();
    public Optional<String> returnType = Optional.empty();
    public LinkedList<String> argumentTypes = new LinkedList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodInfo that = (MethodInfo) o;
        return Objects.equals(declarerClassName, that.declarerClassName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(returnType, that.returnType) &&
                Objects.equals(argumentTypes, that.argumentTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declarerClassName, name, returnType, argumentTypes);
    }

    @Override
    public String toString() {
        return "MethodInfo{" +
                "declarerClassName='" + declarerClassName + '\'' +
                ", name=" + name +
                ", returnType='" + returnType + '\'' +
                ", argumentTypes=" + argumentTypes +
                '}';
    }

    @Override
    public int compareTo(MethodInfo o) {
        int ret;
        ret = declarerClassName.compareTo(o.declarerClassName);
        if (ret != 0) return ret;

        ret = name.orElse("N/A").compareTo(o.name.orElse("N/A"));
        if (ret != 0) return ret;

        ret = returnType.orElse("N/A").compareTo(o.returnType.orElse("N/A"));
        if (ret != 0) return ret;

        return argumentTypes.toString().compareTo(o.argumentTypes.toString());
    }
}
