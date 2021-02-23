package com.plaso;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
    public static void main( String[] args ){

        //1. 加载Hibernate的核心配置文件
        Configuration configuration = new Configuration().configure();

        //2. 创建SessionFactory对象，类似于JDBC中的连接池
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //3. 通过SessionFactory获取到Session对象，类似于JDBC中的Connection
        Session session = sessionFactory.openSession();

        //4. 手动开启事务，（最好是手动开启事务）
        Transaction transaction = session.beginTransaction();

        //5. 编写代码
        Student student = new Student();
        student.setId(102);
        student.setLoginname("寇季");
        session.save(student);//保存一个用户

        Student user = (Student) session.get(Student.class, 101);
        System.out.println(user.getId());

        //6. 事务提交
        transaction.commit();

        //7. 释放资源
        session.close();
        sessionFactory.close();
        System.out.println("success");
    }

}
