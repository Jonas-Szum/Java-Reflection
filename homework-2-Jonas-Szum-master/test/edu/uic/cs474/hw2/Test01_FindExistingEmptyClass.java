package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class Test01_FindExistingEmptyClass {

    @Test
    public void test() {
        InspectorImplementation inspector = new InspectorImplementation();

        ClassInfo expected = new ClassInfo();
        ClassInfo parent = new ClassInfo();
        parent.name = "java.lang.Object";
        expected.parent = Optional.of(parent);
        expected.name = "edu.uic.cs474.hw2.Test01_FindExistingEmptyClass$ExistingEmptyClass";

        Optional<ClassInfo> info = inspector.findClass("edu.uic.cs474.hw2.Test01_FindExistingEmptyClass$ExistingEmptyClass", HowMuchData.DECLARED);

        Assert.assertEquals(Optional.of(expected), info);
    }

    public static class ExistingEmptyClass { }
}