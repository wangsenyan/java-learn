package com.wsy.algorithm.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class ReadFile {
    public static String readJsonFile(String fileName){
        String jsonStr = "";
        try {
            String path = "testInstance/" + fileName;
            jsonStr = getString(path);
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getString(String path) throws IOException {
        String jsonStr;
        File jsonFile = new ClassPathResource(path).getFile();
        FileReader fileReader = new FileReader(jsonFile);
        Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        jsonStr = sb.toString();
        return jsonStr;
    }
}
