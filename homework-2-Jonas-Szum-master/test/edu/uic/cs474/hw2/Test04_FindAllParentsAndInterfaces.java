package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class Test04_FindAllParentsAndInterfaces {

    @Test
    public void test() {
        InspectorImplementation inspector = new InspectorImplementation();

        ClassInfo parent = new ClassInfo();
        parent.name = "java.lang.Object";

        ClassInfo emptyClassOneWithTwoInterfaces = new ClassInfo();
        ClassInfo emptyClassTwoWithTwoInterfaces = new ClassInfo();

        ClassInfo interfaceOne =  new ClassInfo();
        interfaceOne.name = "edu.uic.cs474.hw2.Test04_FindAllParentsAndInterfaces$InterfaceOne";
        interfaceOne.parent = Optional.empty();

        ClassInfo interfaceTwo =  new ClassInfo();
        interfaceTwo.name = "edu.uic.cs474.hw2.Test04_FindAllParentsAndInterfaces$InterfaceTwo";
        interfaceTwo.parent = Optional.empty();
        interfaceTwo.interfaces.add(interfaceOne);

        ClassInfo interfaceThree =  new ClassInfo();
        interfaceThree.name = "edu.uic.cs474.hw2.Test04_FindAllParentsAndInterfaces$InterfaceThree";
        interfaceThree.parent = Optional.empty();

        ClassInfo interfaceFour =  new ClassInfo();
        interfaceFour.name = "edu.uic.cs474.hw2.Test04_FindAllParentsAndInterfaces$InterfaceFour";
        interfaceFour.parent = Optional.empty();
        interfaceFour.interfaces.add(interfaceThree);

        emptyClassOneWithTwoInterfaces.name = "edu.uic.cs474.hw2.Test04_FindAllParentsAndInterfaces$EmptyClassOneWithTwoInterfaces";
        emptyClassOneWithTwoInterfaces.interfaces.add(interfaceTwo);
        emptyClassOneWithTwoInterfaces.parent = Optional.of(parent);

        emptyClassTwoWithTwoInterfaces.name = "edu.uic.cs474.hw2.Test04_FindAllParentsAndInterfaces$EmptyClassTwoWithTwoInterfaces";
        emptyClassTwoWithTwoInterfaces.interfaces.add(interfaceFour);
        emptyClassTwoWithTwoInterfaces.parent = Optional.of(emptyClassOneWithTwoInterfaces);

        Optional<ClassInfo> info = inspector.findClass("edu.uic.cs474.hw2.Test04_FindAllParentsAndInterfaces$EmptyClassTwoWithTwoInterfaces", HowMuchData.ALL_INTERFACES);
        Assert.assertTrue(info.isPresent());
        Assert.assertEquals(emptyClassTwoWithTwoInterfaces, info.get());
    }

    public static interface InterfaceOne {}
    public static interface InterfaceTwo extends InterfaceOne {}
    public static interface InterfaceThree{}
    public static interface InterfaceFour extends InterfaceThree {}

    public static class EmptyClassOneWithTwoInterfaces implements InterfaceTwo {}
    public static class EmptyClassTwoWithTwoInterfaces extends EmptyClassOneWithTwoInterfaces implements InterfaceFour{}
}
