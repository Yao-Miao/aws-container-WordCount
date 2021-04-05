package com.example;



import java.io.*;

//used for read file
public class FileIO {
    public static String readFile(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];

        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException var8) {
            var8.printStackTrace();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException var7) {
            System.err.println("The OS does not support " + encoding);
            var7.printStackTrace();
            return null;
        }
    }

    public static boolean checkFile(String fileName){
        File file = new File(fileName);
        return file.exists() && file.isFile();
    }
}
