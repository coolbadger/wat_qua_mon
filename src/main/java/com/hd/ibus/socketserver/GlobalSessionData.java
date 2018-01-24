package com.hd.ibus.socketserver;

import com.hd.ibus.pojo.SessionData;
import org.apache.mina.core.session.IoSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ThinkPad on 2017-08-04.
 */
public class GlobalSessionData {
    private static final Map<Long, SessionData> sessionDataMap = new HashMap();
    private static final Map<String, IoSession> sessionMap = new HashMap<String, IoSession>();

//    private static Double[] sensorData = new Double[6];

    private static SensorPojo sensorPojo = null;

    public static Map<Long, SessionData> getSessionDataMap() {
        return sessionDataMap;
    }

    public static Map<String, IoSession> getSessionMap() {
        return sessionMap;
    }

    public static synchronized void putSessionData(Long sessionId, SessionData sessionData) {
        sessionDataMap.put(sessionId, sessionData);
    }

    public static synchronized void putSession(String dtuId, IoSession session) {
        sessionMap.put(dtuId, session);
    }

    public static IoSession getSession(String dtuId) {
        return sessionMap.get(dtuId);
    }

    public static synchronized void deleteSession(IoSession session) {

        sessionMap.remove(session.getId());

    }

    public synchronized static void setSensorPojo(SensorPojo inSensorPojo){
        try {
            sensorPojo = (SensorPojo) inSensorPojo.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    public static SensorPojo getSensorPojo(){
        return sensorPojo;
    }

//    public synchronized static void setSensorData(Double[] inSensorData){
//        if (inSensorData != null && inSensorData.length == 6){
//            sensorData = Arrays.copyOf(inSensorData,6);
//            System.out.println("set sensor data: " + sensorData[0] + "  "
//                                + sensorData[1] + "  " + sensorData[2] + "  "
//                    + sensorData[3] + "  " + sensorData[4] + "  " + sensorData[5] + ";");
//        }
//    }
//
//    public Double[] getSensorData(){
//        System.out.println("get sensor data: " + sensorData[0] + "  "
//                + sensorData[1] + "  " + sensorData[2] + "  "
//                + sensorData[3] + "  " + sensorData[4] + "  " + sensorData[5] + ";");
//        return sensorData;
//    }

}
