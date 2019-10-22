package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class Test03_FindClassWithInterfaces {

    @Test
    public void test() {
        InspectorImplementation inspector = new InspectorImplementation();

        ClassInfo parent = new ClassInfo();
        parent.name = "java.lang.Object";

        ClassInfo interfaceTwo =  new ClassInfo();
        interfaceTwo.name = "edu.uic.cs474.hw2.Test03_FindClassWithInterfaces$InterfaceTwo";
        interfaceTwo.parent = Optional.empty();

        ClassInfo emptyClassOneWithTwoInterfaces = new ClassInfo();
        emptyClassOneWithTwoInterfaces.name = "edu.uic.cs474.hw2.Test03_FindClassWithInterfaces$EmptyClassOneWithTwoInterfaces";
        emptyClassOneWithTwoInterfaces.interfaces.add(interfaceTwo);
        emptyClassOneWithTwoInterfaces.parent = Optional.of(parent);

        Optional<ClassInfo> info = inspector.findClass("edu.uic.cs474.hw2.Test03_FindClassWithInterfaces$EmptyClassOneWithTwoInterfaces", HowMuchData.ALL);
        Assert.assertEquals(Optional.of(emptyClassOneWithTwoInterfaces), info);
    }

    public static interface InterfaceOne {}
    public static interface InterfaceTwo extends InterfaceOne {}

    public static class EmptyClassOneWithTwoInterfaces implements InterfaceTwo{}
}
