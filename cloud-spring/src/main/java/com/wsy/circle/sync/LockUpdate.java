package com.wsy.circle.sync;

import org.openjdk.jol.info.ClassLayout;

public class LockUpdate {
    public static void main(String[] args) {
//        User[] user = new User[10];
        User user = new User();
        Runnable RUNNABLE = ()->{
            while (!Thread.interrupted()){
                synchronized (user){
                    String SPLITE_STR = "=========";
                    System.out.println(SPLITE_STR);
                    System.out.println(ClassLayout.parseInstance(user).toPrintable());
                    System.out.println(SPLITE_STR);
                }
                try {
                    System.out.println(Integer.toBinaryString(user.hashCode()));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(RUNNABLE).start();
        }
        //00000000 00000000 00000010 00001100 11000111 00111101 11101101 00000010
    }

}
//-XX:-UseCompressedOops 取消指针压缩
class User{
    private boolean myboolean;
}