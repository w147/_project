package com.plaso.log;


import org.apache.log4j.Logger;

public class TestLog4j {
    static Logger logger = Logger.getLogger("log4j");

    public static void main(String[] args) {
        logger.info("log4j");
    }
}
