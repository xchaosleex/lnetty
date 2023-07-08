package nettybase.javaio.dataio;

import java.io.*;

public class TestDataIO {
    public static void main(String[] args) {
        // 使用 DataOutputStream 写入数据
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"))) {
            dos.writeInt(123);
            dos.writeFloat(123.45f);
            dos.writeDouble(123.45);
            dos.writeBoolean(true);
            dos.writeUTF("Hello, World!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用 DataInputStream 读取数据
        try (DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"))) {
            int i = dis.readInt();
            float f = dis.readFloat();
            double d = dis.readDouble();
            boolean b = dis.readBoolean();
            String s = dis.readUTF();
            System.out.println(i + ", " + f + ", " + d + ", " + b + ", " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
