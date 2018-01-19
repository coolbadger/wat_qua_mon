package com.hd.ibus.socketserver;

import com.hd.ibus.pojo.SessionData;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

import java.util.Date;
import java.util.Map;

/**
 * Created by lekoxnfx on 2017/7/13.
 */
public class MinaDecoder extends TextLineDecoder {

    @Override
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {

        byte[] data = ioBufferToByte(in);
        in.flip();
        SensorPojo sensorPojo;

        String hex = bytesToHexString(data);

        System.out.println(hex);

        if (hex.length() < 50 && hex.startsWith("01040C02")) {
            try {
                sensorPojo = new SensorPojo(data);
            } catch (Exception e) {

            }
        } else if (hex.length() < 50 && hex.startsWith("AABBCCDDEEFF")) {
            //心跳包
        } else {
            super.decode(session, in, out);
        }
    }

    public byte[] ioBufferToByte(Object message) {
        if (!(message instanceof IoBuffer)) {
            return null;
        }
        IoBuffer ioBuffer = (IoBuffer) message;
        byte[] b = new byte[ioBuffer.limit()];
        ioBuffer.get(b);
        return b;
    }

    //将byte[]转为Hex
    public String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }

            stringBuilder.append(hv.toUpperCase());
        }
        return stringBuilder.toString();
    }

    public String byteToHexString(byte src) {
        byte[] bytes = {src};
        return bytesToHexString(bytes);
    }

    //将byte[]转换为ASCII,0x00除外
    public String bytesASCIIToString(byte[] src) {
        int tRecvCount = src.length;
        String nRcvString;
        StringBuffer tStringBuf = new StringBuffer();
        for (int i = 0; i < tRecvCount; i++) {
            if (src[i] != 0x00) {
                tStringBuf.append((char) src[i]);
            }
        }
        nRcvString = tStringBuf.toString();
        return nRcvString;
    }

    public String byteASCIIToString(byte src) {
        byte[] bytes = {src};
        return bytesASCIIToString(bytes);
    }
}
