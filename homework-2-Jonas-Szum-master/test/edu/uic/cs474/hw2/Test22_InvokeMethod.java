package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Optional;

public class Test22_InvokeMethod {

    @Test
    public void test() throws Throwable {
        InspectorImplementation inspector = new InspectorImplementation();

        MethodInfo m = new MethodInfo();
        m.name = Optional.of("replace");
        m.returnType = Optional.of("java.lang.String");
        m.argumentTypes.add("java.lang.CharSequence");
        m.argumentTypes.add("java.lang.CharSequence");
        m.declarerClassName = "java.lang.String";

        String obj = "CS\n474";
        LinkedList<Object> args = new LinkedList<>();
        args.add("\n");
        args.add("\\n");
        Optional ret = inspector.invokeMethodOrConstructor(m, Optional.of(obj), args, false);

        Assert.assertTrue(ret.isPresent());
        Assert.assertEquals("CS\\n474",ret.get());

        // Twice for good measure
        ret = inspector.invokeMethodOrConstructor(m, Optional.of(obj), args, false);

        Assert.assertTrue(ret.isPresent());
        Assert.assertEquals("CS\\n474",ret.get());
    }
}
