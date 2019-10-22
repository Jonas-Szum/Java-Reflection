package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class Test19_ReadPrivateField {

    @Test
    public void test() {

        FieldInfo fieldOneOfParentClass = new FieldInfo();
        fieldOneOfParentClass.declarerClassName = "edu.uic.cs474.hw2.Test19_ReadPrivateField$ParentClass";
        fieldOneOfParentClass.name = "field";
        fieldOneOfParentClass.type = "char";

        FieldInfo fieldOneOfChildClass = new FieldInfo();
        fieldOneOfChildClass.declarerClassName = "edu.uic.cs474.hw2.Test19_ReadPrivateField$ChildClass";
        fieldOneOfChildClass.name = "field";
        fieldOneOfChildClass.type = "double";

        InspectorImplementation inspector = new InspectorImplementation();

        ParentClass p = new ParentClass();
        ChildClass c = new ChildClass();

        {
            // Read p.field
            Optional read = inspector.readWriteField(fieldOneOfParentClass, Optional.of(p), Optional.empty(), true);
            Assert.assertTrue(read.isPresent());
            Assert.assertEquals('☺', read.get());

            read = inspector.readWriteField(fieldOneOfParentClass, Optional.of(p), Optional.empty(), false);
            Assert.assertFalse(read.isPresent());
        }

        {
            // Read p.field in c
            Optional read = inspector.readWriteField(fieldOneOfParentClass, Optional.of(c), Optional.empty(), true);
            Assert.assertTrue(read.isPresent());
            Assert.assertEquals('☺', read.get());

            read = inspector.readWriteField(fieldOneOfParentClass, Optional.of(c), Optional.empty(), false);
            Assert.assertFalse(read.isPresent());
        }

        {
            // Read c.field
            Optional read = inspector.readWriteField(fieldOneOfChildClass, Optional.of(c), Optional.empty(), true);
            Assert.assertTrue(read.isPresent());
            Assert.assertEquals(Math.PI, read.get());

            read = inspector.readWriteField(fieldOneOfChildClass, Optional.of(c), Optional.empty(), false);
            Assert.assertTrue(read.isPresent());
            Assert.assertEquals(Math.PI, read.get());
        }
    }

    private static class ParentClass {
        private char field = '☺';
    }
    private static class ChildClass extends ParentClass {
        public double field = Math.PI;
    }

}
