package com.hd.ibus.socketserver;

import com.hd.ibus.pojo.SessionData;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.SimpleBufferAllocator;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.logging.LoggingFilter;

import javax.validation.constraints.Min;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Map;

public class MinaLogginFilter extends LoggingFilter {
    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        super.messageReceived(nextFilter, session, message);
    }
}
