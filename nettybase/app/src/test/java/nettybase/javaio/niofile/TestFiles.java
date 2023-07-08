package nettybase.javaio.niofile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TestFiles {
    public static void main(String[] args) {
//        try {
//            Path path = Paths.get("data.txt");
//            // 创建文件
//            if (!Files.exists(path)) {
//                Files.createFile(path);
//            }
//            // 删除文件
//            if (Files.exists(path)) {
//                Files.delete(path);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            Path path = Paths.get("data.txt");
            // 写入文件
            List<String> lines = Arrays.asList("Hello", "World");
            Files.write(path, lines, StandardCharsets.UTF_8);

            // 读取文件
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
