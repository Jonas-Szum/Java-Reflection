package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;
import java.util.Optional;

public class Test16_ReadPublicField 
{
    @Test
    public void test() 
    {

        FieldInfo c1f1 = new FieldInfo();
        c1f1.name = "pubF1";
        c1f1.type = "int";
        c1f1.declarerClassName = "edu.uic.cs474.hw2.Test16_ReadPublicField$ClassOne";

        InspectorImplementation inspector = new InspectorImplementation();

        {
            // null receiver, not force
            Optional val = inspector.readWriteField(c1f1, Optional.empty(), Optional.empty(), false);

            Assert.assertFalse(val.isPresent());
        }

        {
            // bad receiver, not force
            String str = "This is the wrong type of object";
            Optional val = inspector.readWriteField(c1f1, Optional.of(str), Optional.empty(), false);

            Assert.assertFalse(val.isPresent());
        }

        {
            // good receiver, not force
            Optional val = inspector.readWriteField(c1f1, Optional.of(new ClassOne()), Optional.empty(), false);

            Assert.assertTrue(val.isPresent());
            Assert.assertEquals(1, val.get());
        }

        {
            // null receiver, force
            Optional val = inspector.readWriteField(c1f1, Optional.empty(), Optional.empty(), true);

            Assert.assertFalse(val.isPresent());
        }

        {
            // bad receiver, force
            Optional val = inspector.readWriteField(c1f1, Optional.of(new Object()), Optional.empty(), true);

            Assert.assertFalse(val.isPresent());
        }

        {
            // good receiver, force
            Optional val = inspector.readWriteField(c1f1, Optional.of(new ClassOne()), Optional.empty(), true);

            Assert.assertTrue(val.isPresent());
            Assert.assertEquals(1, val.get());
        }
    }

    public static class ClassOne
    {
        public int pubF1 = 1;
    }
}
