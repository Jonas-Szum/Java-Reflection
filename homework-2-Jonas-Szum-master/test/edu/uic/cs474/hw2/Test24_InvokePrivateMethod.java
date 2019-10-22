package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Optional;

public class Test24_InvokePrivateMethod {
    @Test
    public void test() throws Throwable {
        MethodInfo m = new MethodInfo();
        m.name = Optional.of("linkFirst");
        m.returnType = Optional.of("void");
        m.argumentTypes.add("java.lang.Object");
        m.declarerClassName = "java.util.LinkedList";

        Inspector inspector = new InspectorImplementation();

        {
            Exception e = new Exception();
            Optional ret = inspector.invokeMethodOrConstructor(m, Optional.of(e), new LinkedList<>(), false);

            Assert.assertFalse(ret.isPresent());
        }
    }
}
