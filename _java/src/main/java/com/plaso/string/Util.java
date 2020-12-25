package com.plaso.string;

import java.util.Arrays;

public class Util {

    /**
     * 该算法实现将字符串循环左移num位
     * @param str
     * @param num
     * @return
     */
    public static String moveString(String str, int num){
        char[] source = str.toCharArray();
        char[] resChar = new char[source.length];
        int idx = 0;
        while (true){
            resChar[idx++] = source[(num++) % source.length];
            if(idx == source.length) break;
        }
        return new String(resChar);
    }

    /**
     * 将给定字符串中所有的空格去掉
     * @param str
     * @return
     */
    public static String trim(String str){
        char[] source = str.toCharArray();
        char[] resChar = new char[source.length];
        int j = 0;
        for (int i = 0; i < source.length; i++){
            if(source[i] == ' ') {
                continue;
            }
            resChar[j++] = source[i];
        }
        char[] chArr = Arrays.copyOf(resChar, j);
        return new String(chArr);
    }

    /**
     * TopN
     * @param arr
     * @param n
     * @return
     */
    public static Integer[] topN(Integer[] arr, int n){
        Arrays.sort(arr, new Comp());
        Integer[] res = Arrays.copyOf(arr, n);
        return res;
    }

    /**
     * topTwo
     * @param arr
     * @return
     */
    public static int[] topTwo(int[] arr){
        int[] res = new int[]{0,0};
        for (int i=0; i<arr.length; i++){
            if(arr[i] > res[0]){
                res[1] = res[0];
                res[0] = arr[i];
            }else if(arr[i] > res[1]){
                res[1] = arr[i];
            }
        }
        return res;
    }

    /**
     * topTwo
     * @param arr
     * @return
     */
    public static String rep(String str){

        return str;
    }
}