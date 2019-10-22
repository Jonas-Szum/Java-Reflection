package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class Test17_ReadStaticFieldNull {

    @Test
    public void test() {
        InspectorImplementation inspector = new InspectorImplementation();

        FieldInfo fieldInfo = new FieldInfo();
        fieldInfo.name = "STATIC_FIELD";
        fieldInfo.type = "java.lang.Object";
        fieldInfo.declarerClassName = "edu.uic.cs474.hw2.Test17_ReadStaticFieldNull$ModelClass";

        {
            // Good read, empty receiver, not force
            Optional<Object> staticVariable = inspector.readWriteField(fieldInfo, Optional.empty(), Optional.empty(), false);
            Assert.assertTrue(staticVariable.isPresent());
            Assert.assertTrue(staticVariable.get() == Inspector.NULL);
        }

        {
            // Good read, some receiver, not force
            Optional<Object> staticVariable = inspector.readWriteField(fieldInfo, Optional.of(new ModelClass()), Optional.empty(), false);
            Assert.assertTrue(staticVariable.isPresent());
            Assert.assertTrue(staticVariable.get() == Inspector.NULL);
        }

        {
            // Good read, bad receiver, not force
            Optional<Object> staticVariable = inspector.readWriteField(fieldInfo, Optional.of("This is not the correct type but it's ok because we are reading a static field"), Optional.empty(), false);
            Assert.assertTrue(staticVariable.isPresent());
            Assert.assertTrue(staticVariable.get() == Inspector.NULL);
        }

        {
            // Good read, empty receiver, force
            Optional<Object> staticVariable = inspector.readWriteField(fieldInfo, Optional.empty(), Optional.empty(), true);
            Assert.assertTrue(staticVariable.isPresent());
            Assert.assertTrue(staticVariable.get() == Inspector.NULL);
        }

        {
            // Good read, some receiver, force
            Optional<Object> staticVariable = inspector.readWriteField(fieldInfo, Optional.of(new ModelClass()), Optional.empty(), true);
            Assert.assertTrue(staticVariable.isPresent());
            Assert.assertTrue(staticVariable.get() == Inspector.NULL);
        }

        {
            // Good read, bad receiver, force
            Optional<Object> staticVariable = inspector.readWriteField(fieldInfo, Optional.of("This is not the correct type but it's ok because we are reading a static field"), Optional.empty(), true);
            Assert.assertTrue(staticVariable.isPresent());
            Assert.assertTrue(staticVariable.get() == Inspector.NULL);
        }
    }

    public static class ModelClass{
        public static Object STATIC_FIELD = null;
    }
}
