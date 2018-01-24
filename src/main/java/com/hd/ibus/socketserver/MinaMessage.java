package com.hd.ibus.socketserver;

public class MinaMessage {
    public enum MessageType {MESSAGE_TYPE_INSTRUCTION,
        MESSAGE_TYPE_SENSORINFO,
        MESSAGE_TYPE_HEARTBEAT}


    private MessageType messageType;
    //Instruction 类型消息内容
    private String messageInstruction;

    //Sensor 类型消息内容
    private Byte[] sensorReg = new Byte[8];

    public MinaMessage(MessageType inMessageType){
        this.messageType = inMessageType;
    }
    //获取消息类型
    public MessageType getMessageType(){
        return this.messageType;
    }
    //获取Instruction类型消息内容
    public String getMessageInstruction() {
        return messageInstruction;
    }
    //设置Instruction类型消息内容
    public void setMessageInstruction(String messageInstruction) {
        this.messageInstruction = messageInstruction;
    }
    //获取Sensor消息内容
    public Byte[] getSensorReg() {
        return sensorReg;
    }
    //设置Sensor消息内容
    public void setSensorReg(Byte[] sensorReg) {
        this.sensorReg = sensorReg;
    }

}
