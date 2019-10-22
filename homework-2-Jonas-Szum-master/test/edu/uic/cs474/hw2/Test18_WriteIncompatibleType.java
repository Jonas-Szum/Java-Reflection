package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class Test18_WriteIncompatibleType {

    @Test
    public void test()
    {

        FieldInfo c1f1 = new FieldInfo();
        c1f1.name = "pubF1";
        c1f1.type = "float";
        c1f1.declarerClassName = "edu.uic.cs474.hw2.Test18_WriteIncompatibleType$ClassOne";

        InspectorImplementation inspector = new InspectorImplementation();

        {
            // bad new value, not force
            ClassOne obj = new ClassOne();
            obj.pubF1 = Float.MIN_VALUE;

            Optional val = inspector.readWriteField(c1f1, Optional.of(obj), Optional.of("Wrong"), false);

            Assert.assertFalse(val.isPresent());
            Assert.assertTrue(obj.pubF1 == Float.MIN_VALUE);
        }

        {
            // bad new value, force
            ClassOne obj = new ClassOne();
            obj.pubF1 = -1.0F;

            Optional val = inspector.readWriteField(c1f1, Optional.of(obj), Optional.of("Wrong again"), true);

            Assert.assertFalse(val.isPresent());
            Assert.assertTrue(obj.pubF1 == -1.0F);
        }
    }

    public static class ClassOne
    {
        private float pubF1;
    }
}
