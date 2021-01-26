package com.plaso;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class MyMap extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = new String(value.getBytes(), 0, value.getLength(), "UTF-8");
        StringTokenizer itr = new StringTokenizer(line);
        String strName = itr.nextToken();// 学生姓名部分
        String strScore = itr.nextToken();// 成绩部分
        System.out.println(strName + "  " + strScore);
        Text name = new Text(strName);
        int scoreInt = Integer.parseInt(strScore);
        // 张三   分数
        context.write(name, new IntWritable(scoreInt));
    }
}
