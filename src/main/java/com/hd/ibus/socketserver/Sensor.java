package com.hd.ibus.socketserver;

public class Sensor {

    public enum Instruction {
        PH,     //PH
        DO,     //溶解氧
        COND,   //电导
        NTU,    //浊度
        NH4,    //氨氮
        TEMP,   //温度
        ALL,
    }

    public byte[] getByteByInstruction(Instruction instruction) {
        int instLength = 8;
        int offset = 2;
        byte[] bytes = new byte[instLength];
        bytes[0] = (byte) 0x01;
        bytes[1] = (byte) 0x04;
        bytes[2] = (byte) 0x00;
        bytes[3] = (byte) 0x01;
        bytes[4] = (byte) 0x00;
        bytes[5] = (byte) 0x01;
        bytes[6] = (byte) 0x60;
        bytes[7] = (byte) 0x0A;

        switch (instruction) {
            case PH:
                bytes[3] = (byte) 0x01;
                break;
            case DO:
                bytes[3] = (byte) 0x02;
                break;
            case COND:
                bytes[3] = (byte) 0x03;
                break;
            case TEMP:
                bytes[3] = (byte) 0x04;
                break;
            case NTU:
                bytes[3] = (byte) 0x05;
                break;
            case NH4:
                bytes[3] = (byte) 0x06;
                break;
            case ALL:
                bytes[3] = (byte) 0x07;
                break;
        }
        byte[] crc = intToBytes(getCrc16(bytes, offset));
        for (int i = 0; i < offset; i++) {
            bytes[instLength - 1 - i] = crc[i];
        }

        return bytes;
    }

    public int getValue(Instruction instruction, byte[] responseData) {
        int trueValue = 0;
        switch (instruction) {
            case PH:
                break;
            case DO:
                break;
            case COND:
                break;
            case NTU:
                break;
            case NH4:
                break;
            case TEMP:
                break;
        }
        return trueValue;
    }

    public int getCrc16(byte[] arr_buff, int offset) {
        int len = arr_buff.length - offset;

        //预置 1 个 16 位的寄存器为十六进制FFFF, 称此寄存器为 CRC寄存器。
        int crc = 0xFFFF;
        int i, j;
        for (i = 0; i < len; i++) {
            //把第一个 8 位二进制数据 与 16 位的 CRC寄存器的低 8 位相异或, 把结果放于 CRC寄存器
            crc = ((crc & 0xFF00) | (crc & 0x00FF) ^ (arr_buff[i] & 0xFF));
            for (j = 0; j < 8; j++) {
                //把 CRC 寄存器的内容右移一位( 朝低位)用 0 填补最高位, 并检查右移后的移出位
                if ((crc & 0x0001) > 0) {
                    //如果移出位为 1, CRC寄存器与多项式A001进行异或
                    crc = crc >> 1;
                    crc = crc ^ 0xA001;
                } else
                    //如果移出位为 0,再次右移一位
                    crc = crc >> 1;
            }
        }
        return crc;
    }

    public byte[] intToBytes(int value) {
        byte[] src = new byte[2];
        src[0] = (byte) ((value >> 8) & 0xFF);
        src[1] = (byte) (value & 0xFF);
        return src;
    }

    public int bytesToInt(byte[] data) {
        int value = 0;
        value = (data[1] & 0xFF) | ((data[0] & 0xFF) << 8);
        return value;
    }

    public int bytesToInt(byte low, byte high) {
        int value = 0;
        value = (high & 0xFF) | ((low & 0xFF) << 8);
        return value;
    }

}
