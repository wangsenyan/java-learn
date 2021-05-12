package com.wsy.circle.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * 对象头（64位） Object header(Mark Word(64 bits) + Klass pointer(64 bits)) 未指针压缩
 * - Mark word 同步状态,标识,hashcode,GC状态等
 * - Klass pointer 对象的类型指针 +UseCompressedOops 开启指针压缩会压缩为32位
 * |--------------------------------------------------------------------------------------------------------------|
 * |                                              Object Header (128 bits)                                        |
 * |--------------------------------------------------------------------------------------------------------------|
 * |                        Mark Word (64 bits)                                    |      Klass Word (64 bits)    |
 * |--------------------------------------------------------------------------------------------------------------|
 * |  unused:25 | identity_hashcode:31 | unused:1 | age:4 | biased_lock:1 | lock:2 |     OOP to metadata object   |  无锁
 * |----------------------------------------------------------------------|--------|------------------------------|
 * |  thread:54 |         epoch:2      | unused:1 | age:4 | biased_lock:1 | lock:2 |     OOP to metadata object   |  偏向锁
 * |----------------------------------------------------------------------|--------|------------------------------|
 * |                     ptr_to_lock_record:62                            | lock:2 |     OOP to metadata object   |  轻量锁
 * |----------------------------------------------------------------------|--------|------------------------------|
 * |                     ptr_to_heavyweight_monitor:62                    | lock:2 |     OOP to metadata object   |  重量锁
 * |----------------------------------------------------------------------|--------|------------------------------|
 * |                                                                      | lock:2 |     OOP to metadata object   |    GC
 * |--------------------------------------------------------------------------------------------------------------|
 */
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