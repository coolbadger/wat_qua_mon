package com.hd.ibus.socketserver;

public class SensorPojo {

    private double valuePH;
    private double valueDO;
    private double valueCOND;
    private double valueNTU;
    private double valueNH4;
    private double valueTEMP;

    public SensorPojo(byte[] data) {
        Sensor sensor = new Sensor();
        this.setValuePH(sensor.bytesToInt(data[3], data[4]) / (double) 100);
        this.setValueDO(sensor.bytesToInt(data[5], data[6]) / (double) 100);
        this.setValueCOND(sensor.bytesToInt(data[7], data[8]) / (double) 1000);
        this.setValueNH4(sensor.bytesToInt(data[9], data[10]) / (double) 100);
        this.setValueNTU(sensor.bytesToInt(data[11], data[12]) / (double) 100);
        this.setValueTEMP(sensor.bytesToInt(data[13], data[14]) / (double) 100);

        System.out.println("PH:" + getValuePH() + "\nDO:" + getValueDO() + "\nCOND:" + getValueCOND() + "\nNTU:" + getValueNTU() + "\nNH4:" + getValueNH4() + "\nTEMP:" + getValueTEMP());
    }

    public double getValuePH() {
        return valuePH;
    }

    public void setValuePH(double valuePH) {
        this.valuePH = valuePH;
    }

    public double getValueDO() {
        return valueDO;
    }

    public void setValueDO(double valueDO) {
        if (valueDO >= 0 && valueDO <= 20)
            this.valueDO = valueDO;
        else
            this.valueDO = -1;
    }

    public double getValueCOND() {
        return valueCOND;
    }

    public void setValueCOND(double valueCOND) {
        if (valueCOND >= 0 && valueCOND <= 80)
            this.valueCOND = valueCOND;
        else
            this.valueCOND = -1;
    }

    public double getValueNTU() {
        return valueNTU;
    }

    public void setValueNTU(double valueNTU) {
        if (valueNTU >= 0 && valueNTU <= 80)
            this.valueNTU = valueNTU;
        else
            this.valueNTU = -1;
    }

    public double getValueNH4() {
        return valueNH4;
    }

    public void setValueNH4(double valueNH4) {
        if (valueNH4 >= 0 && valueNH4 <= 1000)
            this.valueNH4 = valueNH4;
        else
            this.valueNH4 = -1;
    }

    public double getValueTEMP() {
        return valueTEMP;
    }

    public void setValueTEMP(double valueTEMP) {
        if (valueTEMP >= 0 && valueTEMP <= 1000)
            this.valueTEMP = valueTEMP;
        else
            this.valueTEMP = -1;
    }

}
