package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class Test20_WritePrivateFieldForce {

    @Test
    public void test()
    {

        FieldInfo c1f1 = new FieldInfo();
        c1f1.name = "pubF1";
        c1f1.type = "edu.uic.cs474.hw2.Test20_WritePrivateFieldForce$ClassOne";
        c1f1.declarerClassName = "edu.uic.cs474.hw2.Test20_WritePrivateFieldForce$ClassOne";

        InspectorImplementation inspector = new InspectorImplementation();

        {
            // null receiver, not force
            Optional val = inspector.readWriteField(c1f1, Optional.empty(), Optional.of(new ClassOne()), false);

            Assert.assertFalse(val.isPresent());
        }

        {
            // bad receiver, not force
            String str = "This is the wrong type of object";
            Optional val = inspector.readWriteField(c1f1, Optional.of(str), Optional.of(new ClassOne()), false);

            Assert.assertFalse(val.isPresent());
        }

        {
            // bad new value, not force
            String wrong = "This is the wrong string";
            String right = "This is the right string";
            ClassOne obj = new ClassOne();
            ((ClassTwo)obj).pubF1 = right;
            Optional val = inspector.readWriteField(c1f1, Optional.of(obj), Optional.of(wrong), false);

            Assert.assertFalse(val.isPresent());
            Assert.assertEquals(right, ((ClassTwo)obj).pubF1);
        }

        {
            // good receiver, not force
            ClassOne recv = new ClassOne();
            recv.pubF1 = recv;
            ClassOne obj = new ClassOne();

            Optional val = inspector.readWriteField(c1f1, Optional.of(recv), Optional.of(obj), false);

            Assert.assertFalse(val.isPresent());
            Assert.assertTrue(recv.pubF1 == recv); // Nothing was written
        }

        {
            // null receiver, force
            Optional val = inspector.readWriteField(c1f1, Optional.empty(), Optional.of(new ClassOne()), true);

            Assert.assertFalse(val.isPresent());
        }

        {
            // bad receiver, force
            Optional val = inspector.readWriteField(c1f1, Optional.of(new Object()), Optional.of(new ClassOne()), true);

            Assert.assertFalse(val.isPresent());
        }

        {
            // good receiver, force
            ClassOne recv = new ClassOne();
            recv.pubF1 = recv;
            ClassOne obj = new ClassOne();

            Optional val = inspector.readWriteField(c1f1, Optional.of(recv), Optional.of(obj), true);

            Assert.assertTrue(val.isPresent());
            Assert.assertTrue(val.get() == Inspector.SUCCESS);
            Assert.assertTrue(recv.pubF1 == obj);

            // setAccessible was reset
            val = inspector.readWriteField(c1f1, Optional.of(recv), Optional.of(new ClassOne()), false);

            Assert.assertFalse(val.isPresent());
            Assert.assertTrue(recv.pubF1 == obj);
        }

        {
            // bad new value, force
            String wrong = "This is the wrong string";
            String right = "This is the right string";
            ClassOne obj = new ClassOne();
            ((ClassTwo)obj).pubF1 = right;
            Optional val = inspector.readWriteField(c1f1, Optional.of(obj), Optional.of(wrong), true);

            Assert.assertFalse(val.isPresent());
            Assert.assertEquals(right, ((ClassTwo)obj).pubF1);
        }
    }

    public static class ClassTwo
    {
        private String pubF1;
    }

    public static class ClassOne extends ClassTwo
    {
        private ClassOne pubF1;
    }
}
