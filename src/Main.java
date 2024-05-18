import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://google.co.in");
        URLConnection urlConnection = url.openConnection();

        urlConnection.connect();

        Map<String, List<String>> headers = urlConnection.getHeaderFields();

        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue()) {
                System.out.println(key + ": " + value);
            }
        }

        System.out.println("------------------------------");

        String encoding = urlConnection.getContentEncoding();

        if (encoding == null) {
            encoding = "UTF-8";
        }

        try(Scanner scanner = new Scanner(urlConnection.getInputStream(), encoding)) {
            while (scanner.hasNextLine()) {
                System.out.println("> " + scanner.nextLine());
            }
        }
    }
}