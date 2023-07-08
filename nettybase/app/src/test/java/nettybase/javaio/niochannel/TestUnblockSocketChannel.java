package nettybase.javaio.niochannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestUnblockSocketChannel {
    public static void main(String[] args) {
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.bind(new InetSocketAddress(12345));
            serverChannel.configureBlocking(false);  // 设置为非阻塞模式
            while (true) {
                SocketChannel clientChannel = serverChannel.accept();
                if (clientChannel != null) {
                    // 有客户端连接
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    clientChannel.read(buffer);
                    buffer.flip();
                    clientChannel.write(buffer);
                    clientChannel.close();
                } else {
                    // 没有客户端连接，做其他事情
                    System.out.println("Waiting for connection...");
                    Thread.sleep(1000);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
