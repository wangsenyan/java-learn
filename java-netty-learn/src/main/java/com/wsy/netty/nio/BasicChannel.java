package com.wsy.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 一。 通道Channel
 * 1. 可读写
 * 2. 异步读写数据
 * 3. 读写缓冲区
 * 4. FileOutputStream包含了FileChannel
 * 二。 常用Channel (java.nio.channels)
 * 1. SocketChannel 类似 Socket tcp
 * 2. DatagramChannel udp
 * 3. ServerSocketChannel 类似 ServerSocket tcp
 * 4. FileChannel  file
 *  - public int read(ByteBuffer dst) 从通道读取数据并放到缓冲区中
 *  - public int write(ByteBuffer src) 把缓冲区的数据写到通道中
 *  - private long transferFromFileChannel(FileChannelImpl src,
 *                                          long position, long count) 从目标通道复制数据到当前通道
 *  - public long transferTo(long position, long count,
 *                            WritableByteChannel target) 从数据从当前通道复制给目标通道
 *
 * 5. 注意事项
 *  - ByteBuffer支持类型化的put和get,应该一致,否则抛出异常 BufferUnderflowException 超过边界
 *  - 将普通Buffer转换为只读Buffer  byteBuffer.asReadOnlyBuffer();
 *  - MappedByteBuffer 让文件直接在内存(堆外内存)中进行修改,同步到文件由NIO实现,操作系统不必重新拷贝
 */
public class BasicChannel {
    public static void main(String[] args) throws Exception {
        BasicChannel  basicChannel = new BasicChannel();
        //basicChannel.NIOFileChannel01();
        //basicChannel.NIOFileChannel02();
        //basicChannel.NIOFileChannel03();
        //basicChannel.NIOFileChannel04();
        //basicChannel.NIOByteBuffer01();
        basicChannel.MappedByteBuffer01();
    }

    public void NIOFileChannel01() throws Exception {
        String str = "hello,world";
        FileOutputStream fileOutputStream = new FileOutputStream("f:\\file01.txt");
        //通过FileOutputStream获取FileChannel
        //fileChannel真实类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将str放入byteBuffer
        byteBuffer.put(str.getBytes());
        //对byteBuffer进行反转
        byteBuffer.flip();
        //将byteBuffer数据写入fileChannel
        fileChannel.write(byteBuffer);
        //关闭流
        fileOutputStream.close();
    }
    public void NIOFileChannel02() throws  Exception {
        File file = new File("f:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel =  fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());
        //将通道的数据放入缓冲区
        fileChannel.read(byteBuffer);
        //array()字节数组
        System.out.println(new String(byteBuffer.array()));
    }
    //一个buffer完成文件的读写
    // 1.txt -> fileInputStream(fileChannel01) -> byteBuffer
    // 2.txt <- fileOutputStream(fileChannel02) <-
    public void NIOFileChannel03() throws  Exception {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        while (true){
            byteBuffer.clear(); //把标志位重置
            int read = fileChannel01.read(byteBuffer);
            System.out.println("read " + read);
            if(read ==-1)
                break;
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }
    }
    //transferFrom
    public void NIOFileChannel04() throws  Exception {
         FileInputStream fileInputStream = new FileInputStream("d:\\a.jpg");
         FileChannel sour = fileInputStream.getChannel();
         FileOutputStream fileOutputStream = new FileOutputStream("d:\\b.jpg");
         FileChannel dest = fileOutputStream.getChannel();
         //传输
         dest.transferFrom(sour,0, sour.size());
         //关闭相关流
         sour.close();
         dest.close();
         fileOutputStream.close();
         fileInputStream.close();
    }
    //Buffer.get和Put 一致才能正确
    public void NIOByteBuffer01(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        byteBuffer.putInt(100);
        byteBuffer.putLong(9);
        byteBuffer.putChar('滚');
        byteBuffer.putShort((short) 4);
        byteBuffer.flip();
        //ByteBuffer buffer =  byteBuffer.asReadOnlyBuffer();
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
        //buffer.clear();
        //buffer.putInt(1);

    }

    public void MappedByteBuffer01() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        //参数1: 读写模式
        //参数2: 可以直接修改的起始位置
        //参数3: 映射到内存的大小
        //实际类型是DirectByteBuffer
        MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0,(byte) 'H');
        map.put(3,(byte) '9');
        System.out.println("修改成功");

    }
}
