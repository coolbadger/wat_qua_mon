package MinaTest;

import com.hd.ibus.socketserver.Sensor;
import com.hd.ibus.socketserver.*;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class TestServer {
    private static final int PORT = 10088;

    public static void main(String[] args) throws IOException, InterruptedException {
        //首先，我们为服务端创建IoAcceptor，NioSocketAcceptor是基于NIO的服务端监听器
        IoAcceptor acceptor = new NioSocketAcceptor();
        //接着，如结构图示，在Acceptor和IoHandler之间将设置一系列的Fliter


        //配置事务处理Handler，将请求转由TimeServerHandler处理。
        acceptor.setHandler(new MinaIOHandler());


        //包括记录过滤器和编解码过滤器。其中TextLineCodecFactory是mina自带的文本解编码器
        acceptor.getFilterChain().addLast("executor", new ExecutorFilter());
//        acceptor.getFilterChain().addLast("loggingFilter", new MinaLogginFilter());
        acceptor.getFilterChain().addLast("codecFilter",
                new ProtocolCodecFilter(new MinaCodeFactory()));
        acceptor.getFilterChain().addLast("mdcInjectionFilter", new MdcInjectionFilter(MdcInjectionFilter.MdcKey.remoteAddress));


        //绑定端口
        acceptor.bind(new InetSocketAddress(PORT));

        while (true) {

            Map<String, IoSession> sessionMap = GlobalSessionData.getSessionMap();

            System.out.println("请求传感器状态》》》》》》》》》");
            int instruction = 7;

            Sensor sensor = new Sensor();
            Sensor.Instruction instructionType;
            switch (instruction) {
                case 1:
                    instructionType = Sensor.Instruction.PH;
                    break;
                case 2:
                    instructionType = Sensor.Instruction.DO;
                    break;
                case 3:
                    instructionType = Sensor.Instruction.COND;
                    break;
                case 4:
                    instructionType = Sensor.Instruction.TEMP;
                    break;
                case 5:
                    instructionType = Sensor.Instruction.NTU;
                    break;
                case 6:
                    instructionType = Sensor.Instruction.NH4;
                    break;
                default:
                    instructionType = Sensor.Instruction.ALL;
                    break;
            }
            byte[] bytes = sensor.getByteByInstruction(instructionType);
            for (String id : sessionMap.keySet()) {
                IoBuffer buffer = IoBuffer.allocate(8);
                buffer.put(bytes);
                buffer.flip();
                sessionMap.get(id).write(buffer);
                System.out.println("session write:" + new MinaDecoder().bytesToHexString(bytes));
            }


            Thread.sleep(10000);

        }
    }

    private final static char[] mChars = "0123456789ABCDEF".toCharArray();

    public static String str2HexStr(String str) {
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();

        for (int i = 0; i < bs.length; i++) {
            sb.append(mChars[(bs[i] & 0xFF) >> 4]);
            sb.append(mChars[bs[i] & 0x0F]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static byte[] hexStr2Bytes(String src) {
        /*对输入值进行规范化整理*/
        src = src.trim().replace(" ", "").toUpperCase(Locale.US);
        //处理值初始化
        int m = 0, n = 0;
        int iLen = src.length() / 2; //计算长度
        byte[] ret = new byte[iLen]; //分配存储空间

        for (int i = 0; i < iLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
        }
        return ret;
    }

}
