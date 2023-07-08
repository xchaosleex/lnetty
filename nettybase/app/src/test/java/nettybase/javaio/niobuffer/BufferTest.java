package nettybase.javaio.niobuffer;

import java.nio.ByteBuffer;

public class BufferTest {
    public static void main(String[] args) {
        // 分配一个容量为 8 的 ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(8);

        // 写入数据
        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        // 切换到读模式
        buffer.flip();

        // 输出缓冲区的状态
        System.out.println("Position: " + buffer.position());
        System.out.println("Limit: " + buffer.limit());
        System.out.println("Capacity: " + buffer.capacity());

        // 读取一个字节
        byte b = buffer.get();
        System.out.println("Read one byte: " + b);
        System.out.println("Position: " + buffer.position());

        // 标记当前位置
        buffer.mark();
        System.out.println("Mark at position: " + buffer.position());

        // 读取两个字节
        buffer.get();
        buffer.get();
        System.out.println("Position: " + buffer.position());

        // 恢复到标记的位置
        buffer.reset();
        System.out.println("Position after reset: " + buffer.position());
    }
}
