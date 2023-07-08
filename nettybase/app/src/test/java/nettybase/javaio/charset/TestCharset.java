package nettybase.javaio.charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TestCharset {
    public static void main(String[] args) {
        // 获取默认字符集
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println(defaultCharset);

        // 获取指定字符集
        Charset utf8 = StandardCharsets.UTF_8;

        // 使用指定字符集编码和解码
        String s = "Hello, World!";
        byte[] data = s.getBytes(utf8);
        String s2 = new String(data, utf8);
        System.out.println(s2);
    }
}