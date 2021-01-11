package com.wsy.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 *
 */
public class TUnpooled {
    public static void main(String[] args) {
        //1.创建一个对象包含数组arr[10]
        ByteBuf bytebuf = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            bytebuf.writeByte(i);
        }
        System.out.println("capacity= " + bytebuf.capacity());
        //2. netty中的不需要flip,因为底层维护了readerIndex和writeIndex
        //   readerIndex 读位置
        //   writeIndex  写位置
        //
        for (int i = 0; i < bytebuf.capacity(); i++) {
            System.out.println(bytebuf.getByte(i));//readerIndex不会变化！！！
            System.out.println(bytebuf.readByte());//readerIndex会变化.到最后继续读会报错
        }
    }
}
class TUnpooled1 {
    public static void main(String[] args) {
        //容量大于实际字符空间大小
        //UnpooledByteBufAllocator
        ByteBuf buf = Unpooled.copiedBuffer("hello,world", CharsetUtil.UTF_8);
        if(buf.hasArray()){
            byte[] array = buf.array();
            //对array转换成字符串
            System.out.println(new String(array, Charset.forName("utf-8")));
            System.out.println("buf=" + buf);//UnpooledByteBufAllocator
            System.out.println(buf.arrayOffset());//0
            System.out.println(buf.readerIndex());//0
            System.out.println(buf.writerIndex());//12
            System.out.println(buf.capacity());
            System.out.println(buf.readableBytes());//可读取字节数

            for (int i = 0; i <buf.readableBytes()  ; i++) {
                System.out.println((char) buf.getByte(i));
            }
            System.out.println(buf.getCharSequence(0,4,Charset.forName("utf-8")));
        }
    }
}
