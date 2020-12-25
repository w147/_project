package com.plaso.classload;

public class BootStrapClassLoader {
    public static void main(String[] args) {
        System.out.println("Bootstrap:" + String.class.getClassLoader());
        String tmp = System.getProperty("sun.boot.class.path");
        String[] arr = tmp.split(";");
        for (String elem: arr) {
            System.out.println(elem);
        }
    }
}
