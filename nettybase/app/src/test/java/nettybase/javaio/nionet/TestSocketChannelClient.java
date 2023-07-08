package nettybase.javaio.nionet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TestSocketChannelClient {
    public static void main(String[] args) {
        try (SocketChannel channel = SocketChannel
                .open(new InetSocketAddress("localhost", 12345))) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("Hello, World!".getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
