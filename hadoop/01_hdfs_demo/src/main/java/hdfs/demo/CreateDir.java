package hdfs.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class CreateDir {
    public static void main(String[] args) throws IOException {
        FileSystem hdfs = Util.getFileSystem();
        boolean isok = hdfs.mkdirs(new Path("hdfs:/mydir"));
        if(isok){
            System.out.println("创建目录成功！");
        }else {
            System.out.println("创建目录失败！");
        }
        hdfs.close();
    }
}
