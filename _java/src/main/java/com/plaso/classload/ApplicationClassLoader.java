package com.plaso.classload;

public class ApplicationClassLoader {
    public static void main(String[] args) {

        String tmp = System.getProperty("java.class.path");
        String[] arr = tmp.split(";");
        for (String elem: arr) {
            System.out.println(elem);
        }

        System.out.println(ApplicationClassLoader.class.getClassLoader());
    }
}
