package nettybase.javaio.asyncio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestAsyncIO {
    public static void main(String[] args) throws IOException {
//        Path path = Paths.get("data.txt");
//        AsynchronousFileChannel fileChannel = AsynchronousFileChannel
//                .open(path, StandardOpenOption.READ);
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        long position = 0;
//        fileChannel.read(buffer, position, buffer, new CompletionHandler<>() {
//            @Override
//            public void completed(Integer result, ByteBuffer attachment) {
//                System.out.println("Read " + result + " bytes");
//            }
//
//            @Override
//            public void failed(Throwable exc, ByteBuffer attachment) {
//                System.out.println("Failed to read file");
//                exc.printStackTrace();
//            }
//        });


        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 12345),
                null, new CompletionHandler<Void, Void>() {
            @Override
            public void completed(Void result, Void attachment) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put((byte) 123);
                buffer.flip();
                socketChannel.write(buffer, null, new CompletionHandler<Integer, Void>() {
                    @Override
                    public void completed(Integer result, Void attachment) {
                        System.out.println("Sent " + result + " bytes");
                    }
                    @Override
                    public void failed(Throwable exc, Void attachment) {
                        System.out.println("Failed to send data");
                        exc.printStackTrace();
                    }
                });
            }
            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("Failed to connect");
                exc.printStackTrace();
            }
        });


    }
}
