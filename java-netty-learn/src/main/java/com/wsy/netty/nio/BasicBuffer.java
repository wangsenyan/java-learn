package com.wsy.netty.nio;
import java.nio.IntBuffer;

/**
 * 一。Buffer类型
 * IntBuffer (java.nio)
 * FloatBuffer (java.nio)
 * CharBuffer (java.nio)
 * DoubleBuffer (java.nio)
 * ShortBuffer (java.nio)
 * LongBuffer (java.nio)
 * ByteBuffer (java.nio)
 * 二。NIO和BIO的比较
 * 1. BIO以流的方式处理数据,NIO以块的方式处理数据,块I/O的效率比流I/O高很多
 * 2. BIO是阻塞的,NIO是非阻塞的
 * 3. BIO基于字节流和字符流进行操作,NIO基于Channel和Buffer进行操作
 *    NIO 通道 <=> 缓冲区
 *    Selector监听多个通道的事件,单线程监听多个客户端
 * 三。NIO的Selector,Channel,Buffer的关系
 * 1. 一个Thread对应一个Selector
 * 2. 多个Channel(连接)注册到同一个Selector
 * 3. 一个Channel对应一个Buffer
 * 4. 程序切换到哪个Channel是由事件决定的,Event是一个重要概念
 * 5. Selector会根据不同的事件,在各个通道上切换
 * 6. Buffer就是一个内存块,底层是有一个数组
 * 7. 数据的读取写入通过Buffer,和BIO本质不同
 *    BIO要么是输入流,或是输出流,不能双向
 *    NIO的Buffer是可以读也可以写,但要flip切换
 * 8. Channel是双向的
 * 四。Buffer
 * 1.参数
 *  -     private int mark = -1;
 *  -     private int position = 0; //下一个读取位置
 *  -     private int limit;        //读取限制,position<limit才能读
 *  -     private int capacity;     //容量
 *  -     Type hb[];                //具体子类才有，Type:byte,char,short,int,float,double,long
 */
public class BasicBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //向buffer中传数据
        for (int i = 0; i < intBuffer.capacity() ; i++) {
            intBuffer.put(i*2);
        }
        /** position当前的位置
         * 由读->写: 只能写已被读过的区域?不会覆盖?
         * 由写->读: 只能读写入的数据
         *     public Buffer flip() {
         *         limit = position;
         *         position = 0;
         *         mark = -1;
         *         return this;
         *     }
         */
        //将buffer转换,读写切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            //get有索引,下次读取位置
            System.out.println(intBuffer.get());
        }
    }
}
