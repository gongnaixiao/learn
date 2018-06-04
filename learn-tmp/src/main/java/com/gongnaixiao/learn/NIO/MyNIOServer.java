package com.gongnaixiao.learn.NIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by xg on 2018/5/30.
 */
public class MyNIOServer {
    private static final Logger log = LoggerFactory.getLogger(MyNIOServer.class);

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel socket = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress("localhost", 1111);

        socket.bind(addr);

        socket.configureBlocking(false);

        int ops = socket.validOps();
        SelectionKey selectKy = socket.register(selector, ops, null);

        while(true) {
            log.info("server waiting for new connection and buffer select ...");

            selector.select();

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey>  iterator = keys.iterator();

            while(iterator.hasNext()) {
                SelectionKey myKey = iterator.next();

                if (myKey.isAcceptable()) {
                    SocketChannel client = socket.accept();
                    client.configureBlocking(false);

                    client.register(selector, SelectionKey.OP_READ);
                    log.info("Connection Accepted: " + client.getLocalAddress());
                } else if (myKey.isReadable()) {
                    SocketChannel client = (SocketChannel) myKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    client.read(buffer);

                    String result = new String(buffer.array()).trim();
                    log.info("Message received: " + result);

                    if (result.equals("close")) {
                        client.close();
                        log.info("Connection closed!");
                    }
                }
                iterator.remove();
            }
        }
    }

}
