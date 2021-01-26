package com.plaso;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class MyReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        int n = 0;
        Iterator<IntWritable> iterable = values.iterator();
        while (iterable.hasNext()){
            sum += iterable.next().get();
            n++;
        }
        int average = sum / n;
        System.out.println(key.toString() + "  " + average);
        context.write(key, new IntWritable(average));
    }
}
