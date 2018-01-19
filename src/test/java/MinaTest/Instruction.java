package MinaTest;

import com.hd.ibus.socketserver.Sensor;
import com.hd.ibus.socketserver.MinaDecoder;

public class Instruction {

    public static void main(String[] args) {
        new Instruction().strat();
    }

    public void process() {
        byte[] demo = new byte[8];
        demo[0] = (byte) 0x01;
        demo[1] = (byte) 0x04;
        demo[2] = (byte) 0x00;
        demo[3] = (byte) 0x01;
        demo[4] = (byte) 0x00;
        demo[5] = (byte) 0x01;
        demo[6] = (byte) 0x60;
        demo[7] = (byte) 0x0a;
        String hex = new MinaDecoder().bytesToHexString(demo);
        System.out.println(hex);


    }

    public void strat() {

        Sensor sensor = new Sensor();
        byte[] bytes = sensor.getByteByInstruction(Sensor.Instruction.TEMP);
        System.out.println(new MinaDecoder().bytesToHexString(bytes));
        byte[] demo = new byte[2];
        demo[0] = (byte) 0x02;
        demo[1] = (byte) 0xBC;

        System.out.println(sensor.bytesToInt(demo));
    }


}
