package com.wsy.basis.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Reader {
    public void scanner(){
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        input.close();
    }
    public void bufferReader() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String s = input.readLine();
    }
}
