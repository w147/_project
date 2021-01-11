package com.plaso;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class WordCount {
    public static void main( String[] args ) throws Exception {
        // 初始化Configuration类
        Configuration conf = new Configuration();
        // 通过实例化对象GenericOptionsParser可以获得程序执行所传入的参数
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: wordcount <in> [<in>...] <out>");
            System.exit(2);
        }
        // 构建任务对象
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(MyMapper.class);
        job.setCombinerClass(MyReducer.class);
        job.setReducerClass(MyReducer.class);
        // 设置输出结果的数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        for (int i = 0; i < otherArgs.length - 1; i++) {
            // 设置需要统计的文件的输入路径
            FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
        }
        // 设置统计结果的输出路径
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[(otherArgs.length - 1)]));
        // 提交任务给Hadoop集群
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}
