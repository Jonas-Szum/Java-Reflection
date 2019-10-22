package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;
import java.util.Optional;
import java.util.LinkedList;

public class Test12_FillAllMethods 
{
    @Test
    public void test() 
    {
        InspectorImplementation inspector = new InspectorImplementation();

        ClassInfo expected = new ClassInfo();
        expected.name = "edu.uic.cs474.hw2.Test12_FillAllMethods$ClassOne";

        addJavaLangObjectMethods(expected);

        MethodInfo constructor = new MethodInfo();
        constructor.name = Optional.empty();
        constructor.returnType = Optional.empty();
        constructor.argumentTypes = new LinkedList<>();
        constructor.declarerClassName = expected.name;
        expected.constructors.add(constructor);

        ClassInfo actual = new ClassInfo();
        actual.name = expected.name;

        inspector.fillInMethodsAndConstructors(actual, HowMuchData.ALL);
        Assert.assertEquals(expected, actual);
    }

    public static class ClassOne { }

    public static void addJavaLangObjectMethods(ClassInfo info) {
        String javaLangObject = "java.lang.Object";

        //  Includes all the methods in java.lang.Object

        MethodInfo clone = new MethodInfo();
        clone.name = Optional.of("clone");
        clone.returnType = Optional.of(javaLangObject);
        clone.declarerClassName = javaLangObject;
        info.methods.add(clone);

        MethodInfo equals = new MethodInfo();
        equals.name = Optional.of("equals");
        equals.returnType = Optional.of("boolean");
        equals.declarerClassName = javaLangObject;
        equals.argumentTypes.add(javaLangObject);
        info.methods.add(equals);

        MethodInfo finalize = new MethodInfo();
        finalize.name = Optional.of("finalize");
        finalize.returnType = Optional.of("void");
        finalize.declarerClassName = javaLangObject;
        info.methods.add(finalize);

        MethodInfo getClassMethod = new MethodInfo();
        getClassMethod.name = Optional.of("getClass");
        getClassMethod.returnType = Optional.of("java.lang.Class");
        getClassMethod.declarerClassName = javaLangObject;
        info.methods.add(getClassMethod);

        MethodInfo hashcode = new MethodInfo();
        hashcode.name = Optional.of("hashCode");
        hashcode.returnType = Optional.of("int");
        hashcode.declarerClassName = javaLangObject;
        info.methods.add(hashcode);

        MethodInfo notify = new MethodInfo();
        notify.name = Optional.of("notify");
        notify.returnType = Optional.of("void");
        notify.declarerClassName = javaLangObject;
        info.methods.add(notify);

        MethodInfo notifyAll = new MethodInfo();
        notifyAll.name = Optional.of("notifyAll");
        notifyAll.returnType = Optional.of("void");
        notifyAll.declarerClassName = javaLangObject;
        info.methods.add(notifyAll);

        MethodInfo toString = new MethodInfo();
        toString.name = Optional.of("toString");
        toString.returnType = Optional.of("java.lang.String");
        toString.declarerClassName = javaLangObject;
        info.methods.add(toString);

        MethodInfo wait = new MethodInfo();
        wait.name = Optional.of("wait");
        wait.returnType = Optional.of("void");
        wait.declarerClassName = javaLangObject;
        info.methods.add(wait);

        MethodInfo waitWithOneParameter = new MethodInfo();
        waitWithOneParameter.name = Optional.of("wait");
        waitWithOneParameter.returnType = Optional.of("void");
        waitWithOneParameter.declarerClassName = javaLangObject;
        waitWithOneParameter.argumentTypes.add("long");
        info.methods.add(waitWithOneParameter);

        MethodInfo waitWithTwoParameters = new MethodInfo();
        waitWithTwoParameters.name = Optional.of("wait");
        waitWithTwoParameters.returnType = Optional.of("void");
        waitWithTwoParameters.declarerClassName = javaLangObject;
        waitWithTwoParameters.argumentTypes.add("long");
        waitWithTwoParameters.argumentTypes.add("int");
        info.methods.add(waitWithTwoParameters);

        MethodInfo registerNatives = new MethodInfo();
        registerNatives.name = Optional.of("registerNatives");
        registerNatives.returnType = Optional.of("void");
        registerNatives.declarerClassName = javaLangObject;
        info.methods.add(registerNatives);
    }
}
