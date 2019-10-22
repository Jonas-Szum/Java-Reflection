package edu.uic.cs474.hw2;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class Test08_FillAllFieldsThreeClasses 
{
    @Test
    public void test()
    {

        String topName = "edu.uic.cs474.hw2.Test08_FillAllFieldsThreeClasses$ClassTop";
        String midName = "edu.uic.cs474.hw2.Test08_FillAllFieldsThreeClasses$ClassMiddle";

        ClassInfo expected  = new ClassInfo();
        expected.name = "edu.uic.cs474.hw2.Test08_FillAllFieldsThreeClasses$ClassBottom";

        {
            // ClassTop.pubF1
            FieldInfo ctf1 = new FieldInfo();
            ctf1.declarerClassName = topName;
            ctf1.name = "pubF1";
            ctf1.type = "int";
            expected.fields.add(ctf1);
        }

        {
            // ClassTop.priF2
            FieldInfo ctf2 = new FieldInfo();
            ctf2.declarerClassName = topName;
            ctf2.name = "priF2";
            ctf2.type = "int";
            expected.fields.add(ctf2);
        }

        {
            // ClassBottom.pubF2
            FieldInfo cbf1 = new FieldInfo();
            cbf1.declarerClassName = expected.name;
            cbf1.name = "pubF2";
            cbf1.type = "int";
            expected.fields.add(cbf1);
        }

        {
            // ClassBottom.priF2
            FieldInfo cbf2 = new FieldInfo();
            cbf2.declarerClassName = expected.name;
            cbf2.name = "priF2";
            cbf2.type = "int";
            expected.fields.add(cbf2);
        }

        ClassInfo actual  = new ClassInfo();
        actual.name = expected.name;

        InspectorImplementation inspector = new InspectorImplementation();
        inspector.fillInFields(actual, HowMuchData.ALL);
        Assert.assertEquals(expected, actual);
    }

    public static class ClassTop 
    {
        public int pubF1;
        private int priF2;
    }

    public static class ClassMiddle extends ClassTop {}

    public static class ClassBottom extends ClassMiddle
    {
        public int pubF2;
        private int priF2;
    }
}
