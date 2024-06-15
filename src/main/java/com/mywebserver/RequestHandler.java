package main.java.com.mywebserver;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author 29753
 */
public class RequestHandler implements Runnable {
    private final Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            HttpRequest request = new HttpRequest(inputStream);
            request.setClientIP(socket.getInetAddress().getHostAddress()); // 设置客户端IP地址
            HttpResponse response = new HttpResponse();

            if (SecurityManager.isSecure(request)) {
                if (request.getPath() != null && request.getPath().startsWith("/dynamic")) {
                    new DynamicContentHandler().handle(request, response, outputStream);
                } else {
                    new StaticFileHandler().handle(request, response, outputStream);
                }
            } else {
                response.setStatusCode(403);
                response.setBody("Forbidden");
                response.send(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}