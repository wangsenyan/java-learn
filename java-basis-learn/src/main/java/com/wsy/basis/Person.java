package com.wsy.basis;
@Hints({@Hint("hint1"),@Hint("hint2")})
class Person1 {
}
@Hint("hint1")
@Hint("hint2")
class Person2{
}

public class Person{
    public void testAnnotation(){
        Hint hint = Person2.class.getAnnotation(Hint.class);
        System.out.println(hint);
        Hints hints1 = Person2.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length);

        Hint[] hints2 = Person2.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.testAnnotation();
    }
}