package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class Test07_FillAllFieldsTwoClasses {

    @Test
    public void test()
    {
        String parentName = "edu.uic.cs474.hw2.Test07_FillAllFieldsTwoClasses$ParentClass";

        ClassInfo expectedChildClass  = new ClassInfo();
        expectedChildClass.name = "edu.uic.cs474.hw2.Test07_FillAllFieldsTwoClasses$ChildClass";

        FieldInfo fieldOfParentClass = new FieldInfo();
        fieldOfParentClass.declarerClassName = parentName;
        fieldOfParentClass.name = "parentVariable";
        fieldOfParentClass.type = "int";
        expectedChildClass.fields.add(fieldOfParentClass);

        FieldInfo fieldOfChildClass = new FieldInfo();
        fieldOfChildClass.declarerClassName = expectedChildClass.name;
        fieldOfChildClass.name = "childVariable";
        fieldOfChildClass.type = "java.lang.String";
        expectedChildClass.fields.add(fieldOfChildClass);

        ClassInfo actualChildClass  = new ClassInfo();
        actualChildClass.name = expectedChildClass.name;

        InspectorImplementation inspector = new InspectorImplementation();
        inspector.fillInFields(actualChildClass, HowMuchData.ALL);
        Assert.assertEquals(expectedChildClass, actualChildClass);
    }

    public static class ParentClass {
        int parentVariable;
    }
    public static class ChildClass extends ParentClass{
        String childVariable;
    }
}
