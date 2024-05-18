import java.io.*;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        copyFileBinary();
    }

    static void copyFileBinary() {
        Path originalFilePath = Path.of("earth.png");
        Path copyFilePath = Path.of("earth-copy.png");

        try (
                InputStream inputStream = new BufferedInputStream(new FileInputStream(originalFilePath.toFile()));
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(copyFilePath.toFile()))
        ) {

            outputStream.write(inputStream.readAllBytes());

            System.out.println("\nEnd of file copying");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}