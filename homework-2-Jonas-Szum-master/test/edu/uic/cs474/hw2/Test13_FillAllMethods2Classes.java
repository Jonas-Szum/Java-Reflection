package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Optional;

public class Test13_FillAllMethods2Classes {

    @Test
    public void test() {
        String parentName = "edu.uic.cs474.hw2.Test13_FillAllMethods2Classes$ParentClass";

        ClassInfo expected = new ClassInfo();
        expected.name = "edu.uic.cs474.hw2.Test13_FillAllMethods2Classes$ChildClass";

        {
            //  Default constructor
            MethodInfo defaultConstructor = new MethodInfo();
            defaultConstructor.declarerClassName = expected.name;
            defaultConstructor.name = Optional.empty();
            defaultConstructor.returnType = Optional.empty();
            expected.constructors.add(defaultConstructor);
        }

        // ParentClass.getVar is overridden

        {
            // ParentClass.decrement
            MethodInfo increment = new MethodInfo();
            increment.name = Optional.of("increment");
            increment.declarerClassName = parentName;
            increment.argumentTypes.add("int");
            increment.returnType = Optional.of("void");
            expected.methods.add(increment);
        }

        {
            // ParentClass.increment
            MethodInfo decrement = new MethodInfo();
            decrement.name = Optional.of("decrement");
            decrement.declarerClassName = parentName;
            decrement.argumentTypes = new LinkedList<>();
            decrement.argumentTypes.add("int");
            decrement.returnType = Optional.of("void");
            expected.methods.add(decrement);
        }

        {
            // ChildClass.getVar
            MethodInfo getVar = new MethodInfo();
            getVar.name = Optional.of("getVar");
            getVar.declarerClassName = expected.name;
            getVar.returnType = Optional.of("int");
            expected.methods.add(getVar);
        }

        {
            // ChildClass.increment
            MethodInfo increment = new MethodInfo();
            increment.name = Optional.of("increment");
            increment.declarerClassName = expected.name;
            increment.argumentTypes.add("int");
            increment.argumentTypes.add("int");
            increment.returnType = Optional.of("void");
            expected.methods.add(increment);
        }

        {
            // ChildClass.decrement
            MethodInfo decrement= new MethodInfo();
            decrement.name = Optional.of("decrement");
            decrement.declarerClassName = expected.name;
            decrement.argumentTypes.add("int");
            decrement.argumentTypes.add("int");
            decrement.returnType = Optional.of("void");
            expected.methods.add(decrement);
        }

        Test12_FillAllMethods.addJavaLangObjectMethods(expected);

        ClassInfo actual = new ClassInfo();
        actual.name = expected.name;

        InspectorImplementation inspector = new InspectorImplementation();
        inspector.fillInMethodsAndConstructors(actual, HowMuchData.ALL);
        Assert.assertEquals(expected, actual);
    }

    public static interface InterfaceOne { public void thisMethodShouldNotBeAnywhere(); }
    public static interface InterfaceTwo extends InterfaceOne { }

    public abstract static class ParentClass implements InterfaceTwo {
        int varOne;
        public int getVar(){ return varOne; }
        private void increment(int i){varOne+=i;}
        private void decrement(int i){varOne-=i;}
    }

    public abstract static class ChildClass extends ParentClass{
        int varTwo;
        public int getVar(){ return varOne + varTwo; }
        private void increment(int i, int j){varOne+=i;varTwo+=j;}
        private void decrement(int i, int j){varOne-=i;varTwo-=j;}
    }


}
