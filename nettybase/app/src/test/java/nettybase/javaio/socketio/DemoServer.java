package nettybase.javaio.socketio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            Socket socket = serverSocket.accept();  // 等待客户端连接
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            String message = reader.readLine();  // 读取一行数据
            writer.println("Received chaox.cc: " + message);  // 发送一行数据
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
