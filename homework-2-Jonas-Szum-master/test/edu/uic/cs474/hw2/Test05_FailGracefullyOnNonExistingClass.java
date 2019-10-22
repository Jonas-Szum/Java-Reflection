package edu.uic.cs474.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class Test05_FailGracefullyOnNonExistingClass {

    @Test
    public void test() {
        InspectorImplementation inspector = new InspectorImplementation();

        String fqn = "edu.uic.cs474.hw2.Test05_FailGracefullyOnNonExistingClass$NonExistentClass";
        Optional<ClassInfo> info = inspector.findClass(fqn, HowMuchData.DECLARED);

        Assert.assertEquals(info, Optional.empty());
    }

    //  No class exists
}
