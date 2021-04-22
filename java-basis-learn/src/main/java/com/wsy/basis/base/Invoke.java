package com.wsy.basis.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invoke {
    public static void main(String[] args) {

        String [] names ={"tom","tim","allen","alice"};
        Class<?> clazz = Test.class;

        try {
            Method method = clazz.getMethod("sayHi", String.class);
            for(String name:names)
                method.invoke(clazz.newInstance(),name);
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            e.printStackTrace();
        }
    }
}
class Test{
    public void sayHi(String name)
    {
        System.out.println("Hi "+name);
    }
}