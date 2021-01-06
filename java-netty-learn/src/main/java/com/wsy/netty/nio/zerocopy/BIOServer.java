package com.wsy.netty.nio.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7001);
        while (true){
            Socket socket = serverSocket.accept();
            //protocol engine -> socket buffer
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            try {
                long total = 0;
                byte[] bytes = new byte[4096];
                while (true){
                    //socket buffer -> user buffer
                    int read = dataInputStream.read(bytes, 0, bytes.length);
                    if(read == -1 )
                        break;
                    total+=read;
                }
                System.out.println("接收到"+socket.getRemoteSocketAddress() + " "+ total + "字节");
            }catch (IOException e){
                socket.close();
                //e.printStackTrace();
            }
        }
    }
}
