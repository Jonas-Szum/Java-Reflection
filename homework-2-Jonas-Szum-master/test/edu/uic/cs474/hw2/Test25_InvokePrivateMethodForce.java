package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Optional;

public class Test25_InvokePrivateMethodForce {

    @Test
    public void test() throws Throwable {
        MethodInfo m = new MethodInfo();
        m.name = Optional.of("linkFirst");
        m.returnType = Optional.of("void");
        m.argumentTypes.add("java.lang.Object");
        m.declarerClassName = "java.util.LinkedList";

        LinkedList<Object> args = new LinkedList<>();
        args.add("This should be the first string");

        Inspector inspector = new InspectorImplementation();

        {
            LinkedList<String> expected = new LinkedList<>();
            expected.add("This should be the first string");
            expected.add("This should be the last string");

            LinkedList<String> obj = new LinkedList<>();
            obj.add("This should be the last string");
            Optional ret = inspector.invokeMethodOrConstructor(m, Optional.of(obj), args, true);

            Assert.assertTrue(ret.isPresent());
            Assert.assertTrue(ret.get() == Inspector.NULL);
            Assert.assertEquals(expected, obj);
        }

        {
            LinkedList<String> expected = new LinkedList<>();
            expected.add("This should be the last string");

            LinkedList<String> obj = new LinkedList<>();
            obj.add("This should be the last string");
            Optional ret = inspector.invokeMethodOrConstructor(m, Optional.of(obj), args, false);

            Assert.assertFalse(ret.isPresent());
            Assert.assertEquals(expected, obj);
        }
    }
}
