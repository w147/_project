package com.plaso;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import java.io.InputStream;

/**
 * 查询HDFS文件内容并输出
 */
public class FileSystemCat {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://plaso:8020");
        FileSystem fs = FileSystem.get(conf);
        InputStream in = fs.open(new Path("hdfs://file.txt"));
        IOUtils.copyBytes(in, System.out, 4096, false);
        IOUtils.closeStream(in);
    }
}
