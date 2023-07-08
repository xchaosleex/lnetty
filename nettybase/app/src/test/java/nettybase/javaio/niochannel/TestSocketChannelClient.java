package nettybase.javaio.niochannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TestSocketChannelClient {
    public static void main(String[] args) {
        try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 12345))) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put((byte) 123);
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
            channel.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
