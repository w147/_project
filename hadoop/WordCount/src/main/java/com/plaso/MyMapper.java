package com.plaso;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class MyMapper extends Mapper<Object, Text, Text, IntWritable> {
    private static final IntWritable one = new IntWritable(2);
    private final Text word = new Text();

    public void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context)
            throws IOException, InterruptedException {
        System.out.println(value.toString());
        // 默认根据空格、制表符\t、换行符\n、回车符\r分割字符串
        StringTokenizer itr = new StringTokenizer(value.toString());
        // 循环输出每个单词与数量
        while (itr.hasMoreTokens()) {
            this.word.set(itr.nextToken());
            // 输出单词与数量
            context.write(this.word, one);
        }
    }
}
