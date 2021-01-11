package hdfs.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;

public class Util {
    public static FileSystem getFileSystem() {
        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://192.168.170.133:9000");
        FileSystem fs = null;
        try {
            fs = FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return fs;
    }
}
