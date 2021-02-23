package com.plaso.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("lx");
        factory.setPort(5672);
        factory.setVirtualHost("0701");
        factory.setUsername("admin");
        factory.setPassword("888888");
        Connection connection = factory.newConnection();
        return connection;
    }

}
