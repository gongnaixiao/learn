package com.gongnaixiao.learn.NIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xg on 2018/5/30.
 */
public class MyNIOClient {
    private static final Logger log = LoggerFactory.getLogger(MyNIOClient.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        InetSocketAddress addr = new InetSocketAddress("localhost", 1111);
        SocketChannel client = SocketChannel.open(addr);
        log.info("Connecting to server on port 1111 .... ");

        List<String> msgs = new ArrayList<>();

        msgs.add("Facebook");
        msgs.add("Twitter");
        msgs.add("IBM");
        msgs.add("Google");
        msgs.add("close");

        for (String msg : msgs) {
            ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
            client.write(buffer);

            log.info("Sending msg: " + msg);
            buffer.clear();

            Thread.sleep(2000);
        }
        client.close();
    }
}
