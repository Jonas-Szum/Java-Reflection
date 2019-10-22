package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.LinkedList;

public class Test14_FillAllMethodsInterfaces 
{

    @Test
    public void test() 
    {
        ClassInfo expected = new ClassInfo();
        expected.name = "edu.uic.cs474.hw2.Test14_FillAllMethodsInterfaces$ABCTwo";

        Test12_FillAllMethods.addJavaLangObjectMethods(expected);

        {
            // InterfaceOne.MethodOne
            MethodInfo i1m1 = new MethodInfo();
            i1m1.declarerClassName = "edu.uic.cs474.hw2.Test14_FillAllMethodsInterfaces$InterfaceOne";
            i1m1.name = Optional.of("MethodOne");
            i1m1.returnType = Optional.of("int");
            expected.methods.add(i1m1);
        }

        {
            // InterfaceTwo.MethodTwo
            MethodInfo i2m1 = new MethodInfo();
            i2m1.declarerClassName = "edu.uic.cs474.hw2.Test14_FillAllMethodsInterfaces$InterfaceTwo";
            i2m1.name = Optional.of("MethodTwo");
            i2m1.returnType = Optional.of("void");
            i2m1.argumentTypes = new LinkedList<String>();
            expected.methods.add(i2m1);
        }

        {
            // ABCOne.ABCMethodOne
            MethodInfo abc1m1 = new MethodInfo();
            abc1m1.declarerClassName = "edu.uic.cs474.hw2.Test14_FillAllMethodsInterfaces$ABCOne";
            abc1m1.name = Optional.of("ABCMethodOne");
            abc1m1.returnType = Optional.of("java.lang.String");
            abc1m1.argumentTypes = new LinkedList<String>();
            expected.methods.add(abc1m1);
        }

        {
            // ABCTwo.ABCMethodTwo
            MethodInfo abc2m1 = new MethodInfo();
            abc2m1.declarerClassName = expected.name;
            abc2m1.name = Optional.of("ABCMethodTwo");
            abc2m1.returnType = Optional.of("float");
            abc2m1.argumentTypes = new LinkedList<String>();
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
        inspector.fillInMethodsAndConstructors(actual, HowMuchData.ALL);

        Assert.assertEquals(expected, actual);
    }

    public static interface InterfaceOne {
        int MethodOne();
    }

    public static interface InterfaceTwo {
        void MethodTwo();
    }

    public static abstract class ABCOne implements InterfaceOne {
        public String ABCMethodOne() {
            return "0";
        }
    }

    public static abstract class ABCTwo extends ABCOne implements InterfaceTwo {
        public float ABCMethodTwo() {
            return 0.0F;
        }
    }
}
