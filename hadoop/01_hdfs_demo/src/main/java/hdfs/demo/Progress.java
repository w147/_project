package hdfs.demo;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;

public class Progress {
    public static void main(String[] args) throws IOException {
        FileSystem fs = Util.getFileSystem();
        // 打开一个输出流
        InputStream in = new BufferedInputStream(new FileInputStream("D:/Temp/各种资料/1024.MP4"));
        FSDataOutputStream outputStream = fs.create(new Path("hdfs:/1024.mp4"), new Progressable() {
            public void progress() { // 回调方法显示进度
                System.out.println(".");
            }
        });
        IOUtils.copyBytes(in, outputStream, 4096, false);
    }
}
