package main.java.com.mywebserver;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new RequestHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(Config.getProperty("server.port", "8080"));
        new HttpServer(port).start();
    }
}