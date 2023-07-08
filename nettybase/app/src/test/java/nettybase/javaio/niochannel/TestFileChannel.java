package nettybase.javaio.niochannel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestFileChannel {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
             FileChannel channel = file.getChannel()) {
            // 创建一个 ByteBuffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 从 FileChannel 读取数据到 ByteBuffer
            int bytesRead = channel.read(buffer);

            while (bytesRead != -1) {
                buffer.flip();  // 切换到读模式

                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }

                buffer.clear();  // 清空缓冲区
                bytesRead = channel.read(buffer);  // 读取更多数据
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
