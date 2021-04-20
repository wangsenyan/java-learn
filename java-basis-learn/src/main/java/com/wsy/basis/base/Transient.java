package com.wsy.basis.base;

import java.io.*;

public class Transient implements Serializable {
    private String firstName;

    private transient String middleName;

    private String lastName;

    public Transient (String fName, String mName, String lName){
        this.firstName = fName;

        this.middleName = mName;

        this.lastName = lName;

    }
    @Override
    public String toString() {
        return "NameStore{" +

                "firstName='" + firstName + '\'' +

                ", middleName='" + middleName + '\'' +

                ", lastName='" + lastName + '\'' +

                '}';

    }
    public static void main(String[] args) throws Exception {
        Transient aTransient = new Transient("Steve", "Middle", "Jobs");

        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("aTransient"));

        o.writeObject(aTransient);

        o.close();


        ObjectInputStream in = new ObjectInputStream(new FileInputStream("aTransient"));

        Transient nameStore1 = (Transient) in.readObject();

        System.out.println(nameStore1);
    }
}
