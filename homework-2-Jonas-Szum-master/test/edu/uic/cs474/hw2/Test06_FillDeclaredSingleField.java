package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Optional;

public class Test06_FillDeclaredSingleField {

    @Test
    public void test() {
        ClassInfo expected = new ClassInfo();
        expected.name = "edu.uic.cs474.hw2.Test06_FillDeclaredSingleField$ChildClass";

        FieldInfo fieldOfParentClass = new FieldInfo();
        fieldOfParentClass.declarerClassName = expected.name;
        fieldOfParentClass.name = "childVariable";
        fieldOfParentClass.type = "java.lang.String";
        expected.fields.add(fieldOfParentClass);

        ClassInfo actual  = new ClassInfo();
        actual.name = expected.name;

        InspectorImplementation inspector = new InspectorImplementation();
        inspector.fillInFields(actual, HowMuchData.DECLARED);
        Assert.assertEquals(expected, actual);
    }

    public static class ParentClass {
        int parentVariable;
    }
    public static class ChildClass extends ParentClass{
        String childVariable;
    }
}
