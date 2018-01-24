package com.hd.ibus.socketserver;


import org.apache.mina.core.session.IoSession;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

public class AutoQuerySensor {

    public void autoQuerySensor(){
        System.out.println("starting query sensors");
        byte ins = (byte)0x0a;
        Map<String, IoSession> sessionMap = GlobalSessionData.getSessionMap();
        for (String id : sessionMap.keySet()) {
            sessionMap.get(id).write((byte)0x0a);
            System.out.println("query sensor, session id: " + id );
        }
    }
}
