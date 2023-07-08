package nettybase.javaio.socketio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DemoClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("Hello, World!");  // 发送一行数据
            String message = reader.readLine();  // 读取一行数据
            System.out.println("Received: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
