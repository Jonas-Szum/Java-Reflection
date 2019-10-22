package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Optional;

public class Test11_FillDeclaredSingleConstructor {

    @Test
    public void test() {
        ClassInfo expected = new ClassInfo();
        expected.name = "edu.uic.cs474.hw2.Test11_FillDeclaredSingleConstructor$ModelClass";

        //  Default constructor
        MethodInfo defaultConstructor = new MethodInfo();
        defaultConstructor.name = Optional.empty();
        defaultConstructor.returnType = Optional.empty();
        defaultConstructor.argumentTypes = new LinkedList<>();
        defaultConstructor.declarerClassName = expected.name;
        expected.constructors.add(defaultConstructor);

        ClassInfo actual = new ClassInfo();
        actual.name = expected.name;

        InspectorImplementation inspector = new InspectorImplementation();
        inspector.fillInMethodsAndConstructors(actual, HowMuchData.DECLARED);

        Assert.assertEquals(expected, actual);
    }

    public static class ModelClass {}
}
