package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

public class Test09_FillAllFieldsInterfaces {

    @Test
    public void test() {
        String parentClassName = "edu.uic.cs474.hw2.Test09_FillAllFieldsInterfaces$ParentClass";

        ClassInfo expected = new ClassInfo();
        expected.name = "edu.uic.cs474.hw2.Test09_FillAllFieldsInterfaces$ChildClass";

        {
            // ChildClass.publicVariable
            FieldInfo fieldOneOfChildClass = new FieldInfo();
            fieldOneOfChildClass.declarerClassName = expected.name;
            fieldOneOfChildClass.name = "publicVariable";
            fieldOneOfChildClass.type = "int";
            expected.fields.add(fieldOneOfChildClass);
        }

        {
            // ChildClass.privateVariable
            FieldInfo fieldTwoOfChildClass = new FieldInfo();
            fieldTwoOfChildClass.declarerClassName = expected.name;
            fieldTwoOfChildClass.name = "privateVariable";
            fieldTwoOfChildClass.type = "int";
            expected.fields.add(fieldTwoOfChildClass);
        }

        {
            // ParentClass.publicVariable
            FieldInfo fieldOneOfParentClass = new FieldInfo();
            fieldOneOfParentClass.declarerClassName = parentClassName;
            fieldOneOfParentClass.name = "publicVariable";
            fieldOneOfParentClass.type = "int";
            expected.fields.add(fieldOneOfParentClass);
        }

        {
            // ParentClass.privateVariable
            FieldInfo fieldTwoOfParentClass = new FieldInfo();
            fieldTwoOfParentClass.declarerClassName = parentClassName;
            fieldTwoOfParentClass.name = "privateVariable";
            fieldTwoOfParentClass.type = "int";
            expected.fields.add(fieldTwoOfParentClass);
        }

        {
            // InterfaceOne.constantVariable
            FieldInfo interfaceOneField = new FieldInfo();
            interfaceOneField.declarerClassName = "edu.uic.cs474.hw2.Test09_FillAllFieldsInterfaces$InterfaceOne";
            interfaceOneField.name = "constantVariable";
            interfaceOneField.type = "int";
            expected.fields.add(interfaceOneField);
        }

        {
            // InterfaceTwo.constantVariable
            FieldInfo interfaceTwoField = new FieldInfo();
            interfaceTwoField.declarerClassName = "edu.uic.cs474.hw2.Test09_FillAllFieldsInterfaces$InterfaceTwo";
            interfaceTwoField.name = "constantVariable";
            interfaceTwoField.type = "int";
            expected.fields.add(interfaceTwoField);
        }

        ClassInfo actual = new ClassInfo();
        actual.name = expected.name;

        InspectorImplementation inspector = new InspectorImplementation();
        inspector.fillInFields(actual, HowMuchData.ALL);

        Assert.assertEquals(expected, actual);
    }

    public static interface InterfaceOne {
        public static int constantVariable = 0;
    }

    public static interface InterfaceTwo {
        public static int constantVariable = 0;
    }

    public static class ParentClass implements InterfaceOne{
        public int publicVariable;
        private int privateVariable;
    }

    public static class ChildClass extends ParentClass implements InterfaceTwo{
        public int publicVariable;
        private int privateVariable;
    }

}
