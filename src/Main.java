import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //  In Windows, install telnet client through Windows Features
        //  In PowerShell: telnet 127.0.0.1 3000
        //  This application mirrors your input
        try (ServerSocket serverSocket = new ServerSocket(3000);
             Socket incomingRequest = serverSocket.accept();
             InputStream inputStream = incomingRequest.getInputStream();
             OutputStream outputStream = incomingRequest.getOutputStream();
             Scanner scanner = new Scanner(inputStream, "UTF-8");
             PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
        ) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.trim().equals("q")) {
                    System.out.println("To Terminal >> Will close the connection");
                    printWriter.println(">> Server will close the connection now...");
                    break;
                }

                System.out.printf("To Terminal >> Received: %s\n", line);
                printWriter.printf(">> Server mirrors: %s\n", line);
            }
        }
    }
}