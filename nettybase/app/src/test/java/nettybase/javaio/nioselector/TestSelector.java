package nettybase.javaio.nioselector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TestSelector {

    public static void main(String[] args) {
        try (Selector selector = Selector.open();
             ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.bind(new InetSocketAddress(12345));
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int readyChannels = selector.select();
                if (readyChannels == 0) {
                    continue;
                }
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isAcceptable()) {
                        // a connection was accepted by a ServerSocketChannel.
                        SocketChannel clientChannel = serverChannel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        // a channel is ready for reading
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        clientChannel.read(buffer);
                        buffer.flip();
                        clientChannel.write(buffer);
                        clientChannel.close();
                    }
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
