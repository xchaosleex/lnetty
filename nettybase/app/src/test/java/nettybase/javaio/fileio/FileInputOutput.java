package nettybase.javaio.fileio;

import java.io.*;

public class FileInputOutput {


    public static void main(String[] args) {

//        // 使用 FileOutputStream 写入文件
//        try (FileOutputStream fos = new FileOutputStream("output.txt")) {
//            String content = "Hello, World!";
//            fos.write(content.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 使用 FileInputStream 读取文件
//        try (FileInputStream fis = new FileInputStream("output.txt")) {
//            int data;
//            while ((data = fis.read()) != -1) {
//                System.out.println((char) data);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        // 使用 FileReader 读取文件
        try (FileReader fr = new FileReader("output.txt")) {
            int data;
            while ((data = fr.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用 FileWriter 写入文件
        try (FileWriter fw = new FileWriter("output.txt")) {
            String content = "Hello, World!";
            fw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
