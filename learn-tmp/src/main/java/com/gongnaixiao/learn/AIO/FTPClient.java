package com.gongnaixiao.learn.AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by xg on 2018/5/31.
 */
public class FTPClient {

    public static void main(String[] args) throws IOException {
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        client.connect(new InetSocketAddress("ftp.gnu.org", 21), client, new CompletionHandler<Void, AsynchronousSocketChannel>() {
                    @Override
                    public void completed(Void result, AsynchronousSocketChannel attachment) {
                        FTPClient client = new FTPClient();
                        client.start(attachment);
                    }

                    @Override
                    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
                        exc.printStackTrace();
                    }
                }
        );
        //connect 的调用异步执行，马上完成，阻止 JVM 退出
        System.in.read();
    }

    AsynchronousSocketChannel socket;

    void readResponse() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(128);
        read(buffer);
    }

    void read(ByteBuffer buffer) {
        socket.read(buffer, buffer, reader);
    }

    CompletionHandler<Integer, ByteBuffer> reader = new CompletionHandler<Integer, ByteBuffer>() {
        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            if (result > 0) {
                int position = attachment.position() - 1;
                if (attachment.get(position - 1) == 13 &&
                        attachment.get(position) == 10) {
                    if (isValidReply(attachment, 0)) {
                        attachment.flip();
                        showReply(attachment);
                        // 状态逻辑，处理响应
                        onReply(getReplyCode(attachment, 0));
                    } else {
                        removeLine(attachment, position - 2);
                        if (isValidReply(attachment, 0)) {
                            attachment.flip();
                            showReply(attachment);
                            onReply(getReplyCode(attachment, 0));
                        } else
                            read(attachment);
                    }
                } else {
                    if (!attachment.hasRemaining())
                        removeLine(attachment, position - 1);
                    read(attachment);
                }
            } else {
                System.out.println("remote server closed");
            }
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            exc.printStackTrace();
        }
    };

    public void start(AsynchronousSocketChannel channel) {
        this.socket = channel;
        readResponse();
    }

    protected void onReply(int replyCode) {
        // 按照前面定义好的步骤，处理状态逻辑
        if (replyCode == 220)
            login();
        if (replyCode == 230)
            writeCommand("size README");
        else if (replyCode == 213)
            writeCommand("QUIT");
        else if (replyCode == 221)
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    void writeCommand(String cmd) {
        System.out.print("==>");
        System.out.println(cmd);
        ByteBuffer buffer = ByteBuffer.wrap((cmd + "\r\n").getBytes());
        write(buffer);
    }
    void write(ByteBuffer buffer) {
        socket.write(buffer, buffer, writer);
    }

    CompletionHandler<Integer, ByteBuffer> writer =
            new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if (attachment.hasRemaining())
                        socket.write(attachment, attachment, this);
                    else
                        readResponse();
                }
                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace();
                }
            };

    protected void login() {
        String user = "user anonymous";
        writeCommand(user);
    }


    protected void removeLine(ByteBuffer buffer, int position) {
        int limit = buffer.position();
        byte c;
        while (position >= 0) {
            c = buffer.get(position);
            if (c == 13 || c == 10) {
                showReply(buffer, position);
                buffer.position(position + 1);
                buffer.limit(limit);
                buffer.compact();
                break;
            }
            position--;
        }
    }

    protected void showReply(ByteBuffer buffer) {
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
    }

    protected void showReply(ByteBuffer buffer, int position) {
        for (int i = 0; i < position; i++) {
            System.out.print((char) buffer.get(i));
        }
    }

    public static int getReplyCode(ByteBuffer buffer, int start) {
        return Character.digit(buffer.get(start), 10) * 100
                + Character.digit(buffer.get(start + 1), 10) * 10
                + Character.digit(buffer.get(start + 2), 10);
    }

    public static boolean isValidReply(ByteBuffer buffer, int start) {
        return buffer.get(start + 3) == 32
                && Character.isDigit(buffer.get(start))
                && Character.isDigit(buffer.get(start + 1))
                && Character.isDigit(buffer.get(start + 2));
    }

    public static int findCRLF(ByteBuffer buffer, int start, int end) {
        while (start < end) {
            if (buffer.get(start++) == 13) {
                if (start < end) {
                    if (buffer.get(start) == 10) {
                        return start + 1;
                    }
                }
            }
        }
        return -1;
    }
}
