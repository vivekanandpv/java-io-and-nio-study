import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        //  In Windows, install telnet client through Windows Features
        //  In PowerShell: telnet 127.0.0.1 3000
        //  This application mirrors your input
        //  Create multiple telnet sessions.
        //  Every session will get a different thread from the pool.

        try (ExecutorService service = Executors.newFixedThreadPool(4);
             ServerSocket serverSocket = new ServerSocket(3000)
        ) {
            while (true) {
                service.submit(() -> {
                    try {
                        handleRequest(serverSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    static void handleRequest(ServerSocket serverSocket) throws IOException {
        try (Socket incomingRequest = serverSocket.accept();
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

                System.out.printf("To Terminal [%s] >> Received: %s\n", Thread.currentThread().getName(), line);
                printWriter.printf(">> [%s] Server mirrors: %s\n", Thread.currentThread().getName(), line);
            }
        }
    }
}