// You should not change this file
package edu.uic.cs474.hw2;

import java.util.*;

public interface Inspector {

    public static final Object NULL = new Object();
    public static final Object SUCCESS = new Object();

    public Optional<ClassInfo> findClass(String fullyQualifiedName, HowMuchData howMuch);

    public void fillInFields(ClassInfo info, HowMuchData howMuch);

    public void fillInMethodsAndConstructors(ClassInfo info, HowMuchData howMuch);

    public Optional<Object> readWriteField(FieldInfo field, Optional<Object> o, Optional<Object> newValue, boolean force);

    public Optional<Object> invokeMethodOrConstructor(MethodInfo method, Optional<Object> receiver, LinkedList<Object> arguments, boolean force) throws Throwable;
}
