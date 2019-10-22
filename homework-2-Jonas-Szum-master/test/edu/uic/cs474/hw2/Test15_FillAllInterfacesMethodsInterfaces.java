package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Optional;

public class Test15_FillAllInterfacesMethodsInterfaces {

    @Test
    public void test()
    {
        ClassInfo expected = new ClassInfo();
        expected.name = "edu.uic.cs474.hw2.Test15_FillAllInterfacesMethodsInterfaces$ABCTwo";

        Test12_FillAllMethods.addJavaLangObjectMethods(expected);

        {
            // InterfaceOne.MethodOne
            MethodInfo i1m1 = new MethodInfo();
            i1m1.declarerClassName = "edu.uic.cs474.hw2.Test15_FillAllInterfacesMethodsInterfaces$InterfaceOne";
            i1m1.name = Optional.of("MethodOne");
            i1m1.returnType = Optional.of("int");
            i1m1.argumentTypes.add("int");
            i1m1.argumentTypes.add("boolean");
            expected.methods.add(i1m1);
        }

        {
            // InterfaceTwo.MethodTwo
            MethodInfo i2m1 = new MethodInfo();
            i2m1.declarerClassName = "edu.uic.cs474.hw2.Test15_FillAllInterfacesMethodsInterfaces$InterfaceTwo";
            i2m1.name = Optional.of("MethodTwo");
            i2m1.returnType = Optional.of("void");
            i2m1.argumentTypes.add("java.lang.Object");
            i2m1.argumentTypes.add("java.lang.String");
            expected.methods.add(i2m1);
        }

        {
            // ABCOne.ABCMethodOne
            MethodInfo abc1m1 = new MethodInfo();
            abc1m1.declarerClassName = "edu.uic.cs474.hw2.Test15_FillAllInterfacesMethodsInterfaces$ABCOne";
            abc1m1.name = Optional.of("ABCMethodOne");
            abc1m1.returnType = Optional.of("org.junit.Test");
            expected.methods.add(abc1m1);
        }

        {
            // ABCTwo.ABCMethodTwo
            MethodInfo abc2m1 = new MethodInfo();
            abc2m1.declarerClassName = expected.name;
            abc2m1.name = Optional.of("ABCMethodTwo");
            abc2m1.returnType = Optional.of("edu.uic.cs474.hw2.Test15_FillAllInterfacesMethodsInterfaces");
            expected.methods.add(abc2m1);
        }

        {
            // ABCTwo default constructor
            MethodInfo abc2cons = new MethodInfo();
            abc2cons.declarerClassName = expected.name;
            abc2cons.name = Optional.empty();
            abc2cons.returnType = Optional.empty();
            abc2cons.argumentTypes = new LinkedList<String>();
            expected.constructors.add(abc2cons);
        }

        ClassInfo actual = new ClassInfo();
        actual.name = expected.name;

        InspectorImplementation inspector = new InspectorImplementation();
        inspector.fillInMethodsAndConstructors(actual, HowMuchData.ALL_INTERFACES);

        Assert.assertEquals(expected, actual);
    }

    public static interface InterfaceOne {
        public int MethodOne(int a, boolean b);
    }

    public static interface InterfaceTwo {
        public void MethodTwo(Object o, String s);
    }

    public static interface InterfaceThree extends InterfaceOne, InterfaceTwo { }

    public static abstract class ABCOne implements InterfaceThree {
        public Test ABCMethodOne() {
            return null;
        }
    }

    public static abstract class ABCTwo extends ABCOne implements InterfaceThree {
        public Test15_FillAllInterfacesMethodsInterfaces ABCMethodTwo() {
            return null;
        }
    }
}
