package nettybase.javaio.niochannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestSocketChannelServer {
    public static void main(String[] args) {
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.bind(new InetSocketAddress(12345));

            while (true) {
                try (SocketChannel clientChannel = serverChannel.accept()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    clientChannel.read(buffer);
                    buffer.flip();
                    clientChannel.write(buffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
