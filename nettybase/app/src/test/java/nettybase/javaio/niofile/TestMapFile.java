package nettybase.javaio.niofile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class TestMapFile {

    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("data.txt", "rw");
             FileChannel channel = file.getChannel()) {
            // 创建一个内存映射文件
            MappedByteBuffer buffer = channel.
                    map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
            // 写入数据
            buffer.put((byte) 123);
            // 读取数据
            buffer.flip();
            byte b = buffer.get();
            System.out.println("Read one byte: " + b);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
