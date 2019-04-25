package com.poison.wechart.utils;

import java.io.*;

public class WriteText {
    public static void writeToText(String content,String path) throws IOException {

        File file = new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        // write
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content+"\r\n");
        bw.flush();
        bw.close();
        fw.close();

//        // read
//        FileReader fr = new FileReader(file);
//        BufferedReader br = new BufferedReader(fr);
//        String str = br.readLine();
    }
}
