import java.io.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        //  Files helper class is added in Java 7 as part of NIO.2
        //  This class provides many useful functions in a straight-forward way
        //  https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/nio/file/Files.html
        copyFileBinary();
        copyFileCharacter();
        readAndStream();
    }

    static void copyFileBinary() {
        try {
            Files.write(
                    Path.of("earth-copy.png"),
                    Files.readAllBytes(Path.of("earth.png"))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void copyFileCharacter() {
        try {
            Files.write(
                    Path.of("sample-copy.txt"),
                    Files.readAllLines(Path.of("sample.txt"))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readAndStream() {
        try {
            //  Since readAllLines returns List<String>,
            //  it can be combined with stream processing capabilities
            Files.readAllLines(Path.of("sample.txt"))
                    .stream()
                    .map(String::toUpperCase)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}