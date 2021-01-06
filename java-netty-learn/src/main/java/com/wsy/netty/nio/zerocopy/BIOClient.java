package com.wsy.netty.nio.zerocopy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1",7001);
        String fileName = "F:\\seata-1.4.0.zip";
        //Hard drive --DMR--> kernel buffer
        InputStream inputStream = new FileInputStream(fileName);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCount = 0;
        long total = 0;
        long startTime = System.currentTimeMillis();
        //kernel buffer --cpu copy--> user buffer
        while ((readCount=inputStream.read(buffer))>=0){
            total += readCount;
            //user buffer --cpu copy--> socket buffer
            //socket buffer -> protocol engine (隐藏)
            dataOutputStream.write(buffer);
        }
        System.out.println("发送总字节数:" +total + ",耗时:" + (System.currentTimeMillis() - startTime));
        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
