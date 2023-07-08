package nettybase.javaio.serial;

import java.io.*;

public class TestSerial {
    public static void main(String[] args) {
        // 创建一个对象
        Student student = new Student("John", 20);
        // 使用 ObjectOutputStream 写入对象
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            oos.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用 ObjectInputStream 读取对象
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student s = (Student) ois.readObject();
            System.out.println(s.name + ", " + s.age);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Student implements Serializable {
    String name;
     int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
