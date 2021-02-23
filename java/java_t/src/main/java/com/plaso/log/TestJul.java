package com.plaso.log;

import java.util.logging.Logger;

public class TestJul {
    static Logger logger = Logger.getLogger("jul");

    public static void main(String[] args) {
        logger.info("jul");
    }
}
