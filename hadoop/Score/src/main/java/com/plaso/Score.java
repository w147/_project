package com.plaso;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class Score {
    public static void main( String[] args ) throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println( "Hello World!" );
        Configuration conf = new Configuration();
        // 这句话很关键
        conf.set("fs.default.name", "hdfs://192.168.170.133:9000");
        //  conf.setLong("mapred.min.split.size",10);
        Job job = Job.getInstance(conf, "Score Average");
        job.setJarByClass(Score.class);

        // 设置Map、Reduce处理类
        job.setMapperClass(MyMap.class);
        job.setReducerClass(MyReduce.class);

        // 设置输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 将输入的数据集分割成小数据块splites，提供一个RecordReder的实现
        job.setInputFormatClass(TextInputFormat.class);
        // 提供一个RecordWriter的实现，负责数据输出
        job.setOutputFormatClass(TextOutputFormat.class);

        // 设置输入和输出目录
        FileInputFormat.addInputPath(job, new Path("/input2/"));
        FileOutputFormat.setOutputPath(job, new Path("/output6"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
