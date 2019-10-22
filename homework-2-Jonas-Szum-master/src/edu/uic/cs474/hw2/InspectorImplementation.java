package edu.uic.cs474.hw2;

/* ----------------------------------------- */
/* You can add all the imports you want here */
import java.lang.reflect.*;
import java.util.*;
/* ----------------------------------------- */

public class InspectorImplementation implements Inspector {
    /* ---------------------------------------- */
    /* You can add all the fields you want here */

    /* ---------------------------------------- */

    /* ----------------------------------------- */
    /* You can add all the methods you want here */

    /* ----------------------------------------- */

    /* -------------------------------------------------------------------------------------- */
    /* You have to implement the methods below                                                */
    /* You cannot change the signature (i.e., name, return type, number and type of arguments */
    @Override
    public Optional<ClassInfo> findClass(String fullyQualifiedName, HowMuchData howMuch) {
        try{
            Class<?> c = Class.forName(fullyQualifiedName); //get the class with fullyQualifiedName
            Type superName = c.getGenericSuperclass();      //get superclass Type of this class
            Class<?> temp[] =  c.getInterfaces();           //get all interfaces of this class
            TreeSet<ClassInfo> myInterfaces = new TreeSet<>(); //initialize treeset of interfaces

            for(Class<?> i : temp) //for every interface that class c has
            {
                Optional<ClassInfo> singleInterface;
                if(howMuch == HowMuchData.ALL_INTERFACES) //recursive call based on value of howMuch
                    singleInterface = findClass(i.getTypeName(), howMuch);
                else
                    singleInterface = findClass(i.getTypeName(), HowMuchData.DECLARED);

                if(singleInterface != null) { //add this interface to c's list if not null
                    ClassInfo interfaceParent = new ClassInfo();
                    Type superInterface = i.getGenericSuperclass();
                    if (superInterface != null) {
                        interfaceParent.name = superInterface.getTypeName();
                        singleInterface.get().parent = Optional.of(interfaceParent);
                    }
                    myInterfaces.add(singleInterface.get());
                }
            }

            ClassInfo ret = new ClassInfo();
            if(superName != null) //find class of c's parent
            {
                ret.parent = findClass(superName.getTypeName(), howMuch);
            }
            ret.name = c.getName();
            if(howMuch != HowMuchData.DECLARED)
                ret.interfaces = myInterfaces;
            return Optional.of(ret);
        } catch (ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public void fillInFields(ClassInfo info, HowMuchData howMuch) {
        try {
            TreeSet<FieldInfo> myFields = new TreeSet<>();
            Class<?> c = Class.forName(info.name); //get the class
            Field childFields[] = c.getDeclaredFields(); //get this class's declared fields
            Class<?> interfaceFields[] = c.getInterfaces();

            //initialize and define all declared fields of this class
            for(Field i : childFields) {
                FieldInfo temp = new FieldInfo();
                temp.declarerClassName = i.getDeclaringClass().getName();
                temp.name = i.getName();
                temp.type = i.getType().getName();
                myFields.add(temp);
            }
            //if we're going all the way, we have to search through the parents too
            if(howMuch == HowMuchData.ALL || howMuch == HowMuchData.ALL_INTERFACES) {
                Type parent = c.getGenericSuperclass();
                if(parent != null) { //recursively call this function until there's no more parents
                    ClassInfo parentInfo = new ClassInfo();
                    parentInfo.name = parent.getTypeName();
                    fillInFields(parentInfo, HowMuchData.ALL);
                    myFields.addAll(parentInfo.fields);
                    }
                for(Class<?> inter : interfaceFields) {
                    ClassInfo tempInter = new ClassInfo();
                    tempInter.name = inter.getName();
                    fillInFields(tempInter, howMuch);
                    myFields.addAll(tempInter.fields);
                }
            }
            info.fields = myFields; //fill in the fields of this class
        } catch (ClassNotFoundException e) {
           throw new Error(e);
        }
        //throw new Error("fillInFields not implemented");
    }

    @Override
    public void fillInMethodsAndConstructors(ClassInfo info, HowMuchData howMuch) {
        try {
            //prepare the indrect return values
            TreeSet<MethodInfo> myMethods = new TreeSet<>();
            TreeSet<MethodInfo> myConstructors = new TreeSet<>();

            //get the methods, constructors, and interfaces
            Class<?> thisClass = Class.forName(info.name);
            Method definedMethods[] = thisClass.getDeclaredMethods();
            Constructor definedConstructors[] = thisClass.getConstructors();
            Class<?> theseInterfaces[] = thisClass.getInterfaces();
            //define the declared methods
            for(Method m : definedMethods) {
                MethodInfo singleMethod = new MethodInfo();
                //name the single method
                singleMethod.name = Optional.of(m.getName());
                //add the return type
                singleMethod.returnType = Optional.of(m.getReturnType().getName());
                //add the argument types
                singleMethod.argumentTypes = new LinkedList<>();
                Class<?> tmp[] = m.getParameterTypes();
                for (Class<?> paramType : tmp) {
                    singleMethod.argumentTypes.add(paramType.getName());
                }

                //add the declarer
                singleMethod.declarerClassName = thisClass.getName();
                myMethods.add(singleMethod);
            }
            //define the declared constructors
            for(Constructor c : definedConstructors) {
                MethodInfo singleConst = new MethodInfo();
                //name the single method
                singleConst.name = Optional.empty();
                //add the return type
                singleConst.returnType = Optional.empty();
                //add the argument types
                singleConst.argumentTypes = new LinkedList<>();
                Class<?> tmp[] = c.getParameterTypes();
                for(Class<?> paramType : tmp) {
                    singleConst.argumentTypes.add(paramType.getName());
                }
                //add the declarer
                singleConst.declarerClassName = thisClass.getName();
                myConstructors.add(singleConst);
            }

            //get the information from interfaces
            for(Class<?> inter : theseInterfaces) {
                Method interMethods[] = inter.getDeclaredMethods();
                //copy interface methods if ALL_INTERFACES flag is set
                if(howMuch == HowMuchData.ALL_INTERFACES) {
                    Class<?> moreInters[] = inter.getInterfaces();
                    for(Class<?> interception : moreInters) {
                        ClassInfo tmp = new ClassInfo();
                        tmp.name = interception.getName();
                        fillInMethodsAndConstructors(tmp, howMuch);
                        myMethods.addAll(tmp.methods);
                    }
                }
                //set all of the methods in the interface
                for(Method m : interMethods) {
                    MethodInfo singleMethod = new MethodInfo();
                    //name the single method
                    singleMethod.name = Optional.of(m.getName());
                    //add the return type
                    singleMethod.returnType = Optional.of(m.getReturnType().getName());
                    //add the argument types
                    singleMethod.argumentTypes = new LinkedList<>();
                    Class<?> tmp[] = m.getParameterTypes();
                    for (Class<?> paramType : tmp) {
                        singleMethod.argumentTypes.add(paramType.getName());
                    }

                    //add the declarer
                    singleMethod.declarerClassName = inter.getName();
                    myMethods.add(singleMethod);
                }
            }

            //start the recursive cascading
            if(howMuch == HowMuchData.ALL || howMuch == HowMuchData.ALL_INTERFACES) {
                Type parent = thisClass.getGenericSuperclass();
                //fill in info for parent class
                if (parent != null) {
                    ClassInfo parentInfo = new ClassInfo();
                    parentInfo.name = parent.getTypeName();
                    fillInMethodsAndConstructors(parentInfo, howMuch);
                    for (MethodInfo j : parentInfo.methods) {
                        boolean overridden = false;
                        for (MethodInfo i : myMethods) {
                            //if there is already a child class that overrides the parent method, dont add
                            if (i.name.equals(j.name) && i.returnType.equals(j.returnType)
                                    && i.argumentTypes.equals(j.argumentTypes))
                            {
                                overridden = true;
                                break;
                            }
                        }
                        if (!overridden)
                            myMethods.add(j);
                    }
                }
                for (Class<?> inter : theseInterfaces) {
                    ClassInfo tempInter = new ClassInfo();
                    tempInter.name = inter.getName();
                    fillInFields(tempInter, howMuch);
                    for (MethodInfo j : tempInter.methods) {
                        boolean overridden = false;
                        for (MethodInfo i : myMethods) {
                            //if there is already a child class that overrides the parent method, dont add
                            if (i.name.equals(j.name) && i.returnType.equals(j.returnType)
                                    && i.argumentTypes.equals(j.argumentTypes))
                            {
                                overridden = true;
                                break;
                            }
                        }
                        if (!overridden)
                            myMethods.add(j);
                    }
                }
            }
            info.constructors = myConstructors;
            info.methods = myMethods;
        }
        catch(ClassNotFoundException e)
        {
            //should never get here
            throw new Error(e);
        }
    }

    @Override
    public Optional<Object> readWriteField(FieldInfo field, Optional<Object> o, Optional<Object> newValue, boolean force) {
        try{
            Object obj;
            Class<?> c = Class.forName(field.declarerClassName);
            Field fi = c.getDeclaredField(field.name);

            if(fi == null) {
                return Optional.empty();
            }
            if(force)
                fi.setAccessible(true);


            if(Modifier.isStatic(fi.getModifiers()))  //static case, return inspector.NULL
        {
            obj = Inspector.NULL;
            return Optional.of(obj);
        }
        else if(!o.isPresent()) //if empty value is passed, return empty value
            return Optional.empty();
        if(newValue.isPresent() && !c.getName().equals(newValue.get().getClass().getName()))
        {
            return Optional.empty();
        }
        try { // check to see if new value needs to be set
            fi.set(o.get(), newValue.get());
            return Optional.of(Inspector.SUCCESS);

        } catch(Exception e) {}
        try {
            switch (fi.getType().getName()) {
                case "boolean":
                    obj = (boolean) fi.get(o.get());
                    break;
                case "int":
                    obj = (int) fi.get(o.get());
                    break;
                case "float":
                    obj = (float) fi.get(o.get());
                    break;
                case "short":
                    obj = (short) fi.get(o.get());
                    break;
                case "long":
                    obj = (long) fi.get(o.get());
                    break;
                case "byte":
                    obj = (byte) fi.get(o.get());
                    break;
                case "double":
                    obj = (double) fi.get(o.get());
                    break;
                default:
                    obj = (Object) fi.get(o.get());
                    break;
                 }
             } catch(Exception e) {
                 return Optional.empty();
             }


         return Optional.of(obj);
        } catch(Exception e)
        {
            throw new Error(e);
        }
    }

    @Override
    public Optional<Object> invokeMethodOrConstructor(MethodInfo method, Optional<Object> receiver, LinkedList<Object> arguments, boolean force) throws Throwable {
        Class<?> c = Class.forName(method.declarerClassName);

        //dont allow for private invocations unless it's forced
        if(receiver.isPresent() && receiver.get() instanceof Exception && !force)
            return Optional.empty();

        Class[] args = new Class[method.argumentTypes.size()];
        for(int i = 0; i < args.length; i++) {
            switch(method.argumentTypes.get(i)) { //check for primitive types
                case "boolean":
                    args[i] = boolean.class;
                    break;
                case "int":
                    args[i] = int.class;
                    break;
                case "float":
                    args[i] = float.class;
                    break;
                case "short":
                    args[i] = short.class;
                    break;
                case "long":
                    args[i] = long.class;
                    break;
                case "byte":
                    args[i] = byte.class;
                    break;
                case "double":
                    args[i] = double.class;
                    break;
                default:
                    args[i] = Class.forName(method.argumentTypes.get(i));
                    break;
            }

            //primitive types
        }
        Method m;
        Constructor cons;
        Object obj = null;
        if(method.name.isPresent()) {
            m = c.getDeclaredMethod(method.name.get(), args); //if no method name, its constructor
            if(!m.isAccessible() && force) //only set accessible to true if we force it
                m.setAccessible(true);
            if(receiver.isPresent()) //careful not to execute commands that aren't forced
            {
                //if a private command is forced, object is set to inspector.NULL
                try {
                    obj = m.invoke(receiver.get(), arguments.toArray()); //if receiver is empty, it's a static method and this breaks
                } catch (IllegalAccessException e) { //if you cant force it and you can't invoke it, fuck it
                    return Optional.empty();
                }
                if(obj == null && force)
                    obj = Inspector.NULL;

            }
            else if (!receiver.isPresent()) {
                obj = m.invoke(null, arguments.toArray());
                if(obj == null && force)
                    obj = Inspector.NULL;
            }
        }
        else {
            cons = c.getDeclaredConstructor(args);
            if(!cons.isAccessible())
                cons.setAccessible(true);
            obj = cons.newInstance(arguments.toArray());
        }
        //
        if(obj == null)
            return Optional.empty();

        return Optional.of(obj);
    }
    /* -------------------------------------------------------------------------------------- */
}
