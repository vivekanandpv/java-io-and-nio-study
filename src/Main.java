import java.io.*;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        //  Java IO has two styles: traditional IO in java.io package
        //  and modern non-blocking IO in java.nio package
        //  NIO was introduced in Java 1.4 and further enhanced in Java 7 as NIO.2
        //  NIO.2 brought file IO related enhancements to NIO
        //  However, NIO.2 is not a package

        //  Traditionally, IO has two categories: character-based and binary-based
        //  Character-based IO has Reader and Writer
        //  Binary-based IO has InputStream and OutputStream
        //  This further is enhanced by several adapter decorators such as BufferedReader and like

        //  https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/package-summary.html
        readUsingReader();
        writeUsingWriter();
    }

    static void readUsingReader() {
        //  Path.of() is a Java 11 feature
        //  Paths.get() for older versions
        Path filePath = Path.of("sample.txt");

        //  BufferedReader provides character buffering to provide for the
        //  efficient reading of characters, arrays, and lines
        try (Reader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            int ch;

            //  reader.read() returns an int so that -1 represents the end of reading
            //  Recall that char is unsigned in Java

            //  ch = reader.read() statement returns the value of ch after assignment
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }

            System.out.println("\nEnd of file reading");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeUsingWriter() {
        //  Paths.get() for older versions
        Path filePath = Path.of("demo.txt");

        //  This try-with-resources closes the writer, flushing it first.
        //  Flush: If the stream has saved any characters from the various write() methods
        //  in a buffer, write them immediately to their intended destination.
        try (Writer writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            String message = "Hey there!";

            //  If the file exists, writer.write(...) overwrites
            //  Use writer.append(...) if you want to append the content
            writer.write(message);
            //  also possible, albeit low-level
            //  writer.write(message.toCharArray());

            System.out.println("End of file writing");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}