package com.plaso.io;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileUtil {
    public static String readFile(String path) {
        StringBuffer sb = new StringBuffer();
        try {
            InputStream io = new FileInputStream(path);
            byte[] bArr = new byte[100];
            while (io.read(bArr) >= 0){
                sb.append(new String(bArr));
                bArr = new byte[100];
            }
        }catch (Exception err){
            System.out.println(err);
        }
        return sb.toString();
    }
}
