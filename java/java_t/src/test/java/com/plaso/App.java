package com.plaso;

import com.plaso.io.FileUtil;
import com.plaso.string.Util;

public class App {
    public static void main(String[] args) {
//        String str = FileUtil.readFile("D:\\Temp\\各种资料\\tmp.txt");

        String str = Util.moveString("abcd", 2);
        System.out.println(str);

    }
}
