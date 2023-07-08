package nettybase.javaio.fileio;

import java.io.*;

public class BufferedInputOutput {

    public static void main(String[] args) {
        // 使用 BufferedInputStream 读取文件
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("input.txt"))) {
            int data;
            while ((data = bis.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用 BufferedOutputStream 写入文件
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("output.txt"))) {
            String content = "Hello, World!";
            bos.write(content.getBytes());
            bos.flush();  // 确保所有数据都被写出
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
