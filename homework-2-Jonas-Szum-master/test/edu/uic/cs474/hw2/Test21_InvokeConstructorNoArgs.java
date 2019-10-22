package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Optional;

public class Test21_InvokeConstructorNoArgs {

    @Test
    public void test() throws Throwable {
        InspectorImplementation inspector = new InspectorImplementation();

        //  Default constructor
        MethodInfo defaultConstructor = new MethodInfo();
        defaultConstructor.name = Optional.empty();
        defaultConstructor.returnType = Optional.empty();
        defaultConstructor.declarerClassName = "edu.uic.cs474.hw2.Test21_InvokeConstructorNoArgs$TestClass";

        Optional<Object> constructor;
        constructor = inspector.invokeMethodOrConstructor(defaultConstructor, Optional.empty(), new LinkedList<Object>(), false);
        Assert.assertTrue(constructor.isPresent());
        Assert.assertTrue(constructor.get() instanceof TestClass);
    }

    public static class TestClass { }
}
