package mapreduce.demo;

import java.util.StringTokenizer;

public class Test {
    public static void main(String[] args) {
        StringTokenizer itr = new StringTokenizer("abc dfg");
        while (itr.hasMoreTokens()) {
            System.out.println(itr.nextToken());
        }
    }
}
