package com.plaso;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

public class HadoopApp {
    FileSystem fileSystem = null;
    Configuration configuration = null;

    /**
     * HDFS的路径,core-site.xml中配置的端口号
     */
    public static final String HDFS_PATH = "hdfs://192.168.1.22:8020";
    /**
     * 解决无权限访问,设置远程hadoop的linux用户名称
     */
    public static final String USER = "w";

    /**
     * 单元测试之前的准备工作,准备环境,加载配置
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        System.out.println("HDFSApp is setUp.....");
        configuration = new Configuration();
        fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, USER);
    }

    /**
     * 创建目录
     *
     * @throws Exception
     */
    @Test
    public void mkDir() throws Exception {
        fileSystem.mkdirs(new Path("/hdfsapi"));
    }

    /**
     * 单元测试之后的工作,清理环境,释放资源等等
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        configuration = null;
        fileSystem = null;
        System.out.println("HDFSApp is tearDown....");
    }
}
