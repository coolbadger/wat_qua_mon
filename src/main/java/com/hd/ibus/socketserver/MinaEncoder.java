package com.hd.ibus.socketserver;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

/**
 * Created by lekoxnfx on 2017/7/13.
 */
public class MinaEncoder extends TextLineEncoder {
    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        //普通指令
        if(message instanceof String){
            super.encode(session, message, out);

        }
        //透传指令
        else if(message instanceof Byte[]){
            encodeBytes(session,(byte[])message,out);
        }
        //不明指令，忽略
        else {

        }

    }
    public void encodeBytes(IoSession session, byte[] message, ProtocolEncoderOutput out){
        IoBuffer buffer = IoBuffer.allocate(8);
        buffer.put(message);
        buffer.flip();
        out.write(buffer);
        System.out.println("session write:" + bytesToHexString(message));
    }
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
}
