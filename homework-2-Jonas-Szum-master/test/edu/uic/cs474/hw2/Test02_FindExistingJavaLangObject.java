package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;

public class Test02_FindExistingJavaLangObject {

    @Test
    public void test() {
        InspectorImplementation inspector = new InspectorImplementation();

            ClassInfo expected = new ClassInfo();

            expected.name = "java.lang.Object";
            expected.parent = Optional.empty();

            Optional<ClassInfo> info = inspector.findClass("java.lang.Object", HowMuchData.DECLARED);

            Assert.assertEquals(Optional.of(expected), info);
        }

}
