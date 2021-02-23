package hbase.demo;

import java.io.*;

public class FileT {
    public static void main(String[] args) throws Exception {
        File file = new File("D:/a.txt");
        InputStream is = new FileInputStream(file);
        Reader reader = new InputStreamReader(is);
        BufferedReader fileReader = new BufferedReader(reader);
        while (true){
            String line = fileReader.readLine();
            if(line == null){
                break;
            }
            System.out.println(line);
            String[] values = line.split("\t");
            //取出每一个值
            String rowKey = values[0];//学号
            String name = values[1];//姓名
            String age = values[2];//年龄
        }

    }
}
