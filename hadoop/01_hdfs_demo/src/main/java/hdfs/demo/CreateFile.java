package hdfs.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class CreateFile {
    public static void main(String[] args) throws IOException {
        FileSystem fs = Util.getFileSystem();
        // 打开一个输出流
        FSDataOutputStream outputStream = fs.create(new Path("hdfs:/newFile2.txt"));
        // 写入文件内容
        outputStream.write("我是文件内容".getBytes());
        outputStream.close();
        fs.close();
        System.out.println("创建文件成功！");
    }


}
