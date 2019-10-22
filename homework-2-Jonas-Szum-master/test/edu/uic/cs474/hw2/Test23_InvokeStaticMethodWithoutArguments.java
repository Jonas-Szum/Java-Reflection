package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Optional;

public class Test23_InvokeStaticMethodWithoutArguments {

    @Test
    public void test() throws Throwable {
        MethodInfo m = new MethodInfo();
        m.name = Optional.of("valueOf");
        m.returnType = Optional.of("java.lang.String");
        m.argumentTypes.add("boolean");
        m.declarerClassName = "java.lang.String";

        String obj = "This string is not used";
        Object wrongObject = new Object();

        LinkedList<Object> args = new LinkedList<>();
        args.add(true);

        Inspector inspector = new InspectorImplementation();
        Optional ret = inspector.invokeMethodOrConstructor(m, Optional.empty(), args, false);

        Assert.assertTrue(ret.isPresent());
        Assert.assertEquals("true",ret.get());

        ret = inspector.invokeMethodOrConstructor(m, Optional.of(obj), args, false);

        Assert.assertTrue(ret.isPresent());
        Assert.assertEquals("true",ret.get());

        ret = inspector.invokeMethodOrConstructor(m, Optional.of(wrongObject), args, false);

        Assert.assertTrue(ret.isPresent());
        Assert.assertEquals("true",ret.get());
    }
}
