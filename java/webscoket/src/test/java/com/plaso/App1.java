package com.plaso;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.plaso.obj.SocketMsg;

public class App1 {
    public static void main(String[] args) {
        String message = "{type:1, fromUser:'a', toUser:'b', msg:'msg'}";
        JSONObject obj = JSON.parseObject(message);
        SocketMsg socketMsg = new SocketMsg();
        socketMsg.setType((int)obj.get("type"));
        socketMsg.setFromUser((String)obj.get("fromUser"));
        socketMsg.setToUser((String)obj.get("toUser"));
        socketMsg.setMsg((String)obj.get("msg"));
        System.out.println(socketMsg);
//        socketMsg = objectMapper.readValue(message, SocketMsg.class);
    }
}
