package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class Test10_FillAllInterfacesFieldsInterfaces 
{
    @Test
    public void test() 
    {
        String classOneName = "edu.uic.cs474.hw2.Test10_FillAllInterfacesFieldsInterfaces$ClassOne";
        String classTwoName = "edu.uic.cs474.hw2.Test10_FillAllInterfacesFieldsInterfaces$ClassTwo";

        ClassInfo expected = new ClassInfo();
        expected.name = classTwoName;

        {
            // InterfaceOne
            FieldInfo i1f1 = new FieldInfo();
            i1f1.declarerClassName = "edu.uic.cs474.hw2.Test10_FillAllInterfacesFieldsInterfaces$InterfaceOne";
            i1f1.name = "staticF1";
            i1f1.type = "int";
            expected.fields.add(i1f1);
        }

        {
            // InterfaceTwo
            FieldInfo i2f1 = new FieldInfo();
            i2f1.declarerClassName = "edu.uic.cs474.hw2.Test10_FillAllInterfacesFieldsInterfaces$InterfaceTwo";
            i2f1.name = "staticF2";
            i2f1.type = "int";
            expected.fields.add(i2f1);

        }

        {
            // InterfaceThree
            FieldInfo i3f1 = new FieldInfo();
            i3f1.declarerClassName = "edu.uic.cs474.hw2.Test10_FillAllInterfacesFieldsInterfaces$InterfaceThree";
            i3f1.name = "staticF3";
            i3f1.type = "int";
            expected.fields.add(i3f1);
        }

        {
            // InterfaceFour
            FieldInfo i4f1 = new FieldInfo();
            i4f1.declarerClassName = "edu.uic.cs474.hw2.Test10_FillAllInterfacesFieldsInterfaces$InterfaceFour";
            i4f1.name = "staticF4";
            i4f1.type = "int";
            expected.fields.add(i4f1);
        }

        {
            // ClassOne.pubF1
            FieldInfo c1f1 = new FieldInfo();
            c1f1.declarerClassName = classOneName;
            c1f1.name = "pubF1";
            c1f1.type = "int";
            expected.fields.add(c1f1);
        }

        {
            // ClassOne.priF2
            FieldInfo c1f2 = new FieldInfo();
            c1f2.declarerClassName = classOneName;
            c1f2.name = "priF2";
            c1f2.type = "int";
            expected.fields.add(c1f2);
        }

        {
            // ClassTwo.pubF1
            FieldInfo c2f1 = new FieldInfo();
            c2f1.declarerClassName = classTwoName;
            c2f1.name = "pubF1";
            c2f1.type = "int";
            expected.fields.add(c2f1);
        }

        {
            // ClassTwo.priF2
            FieldInfo c2f2 = new FieldInfo();
            c2f2.declarerClassName = classTwoName;
            c2f2.name = "priF2";
            c2f2.type = "int";
            expected.fields.add(c2f2);
        }


        ClassInfo actual = new ClassInfo();
        actual.name = expected.name;

        InspectorImplementation inspector = new InspectorImplementation();
        inspector.fillInFields(actual, HowMuchData.ALL_INTERFACES);

        Assert.assertEquals(expected, actual);
    }


    public static interface InterfaceOne 
    {
        int staticF1 = 0;
    }

    public static interface InterfaceTwo 
    {
        int staticF2 = 0;
    }

    public static interface InterfaceThree extends InterfaceOne
    {
        int staticF3 = 1;
    }

    public static interface InterfaceFour extends InterfaceTwo
    {
        int staticF4 = 1;
    }

    public static class ClassOne implements InterfaceThree //parent
    {
        public int pubF1;
        private int priF2;
    }

    public static class ClassTwo extends ClassOne implements InterfaceFour
    {
        public int pubF1;
        private int priF2;
    }

}
