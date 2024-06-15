package main.java.com.mywebserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 29753
 */
public class StaticFileHandler {
    public void handle(HttpRequest request, HttpResponse response, OutputStream outputStream) {
        String filePath = "web" + request.getPath();
        File file = new File(filePath);

        if (file.exists() && !file.isDirectory()) {
            response.setStatusCode(200);
            response.addHeader("Content-Type", getContentType(filePath));
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                StringBuilder bodyBuilder = new StringBuilder();
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    bodyBuilder.append(new String(buffer, 0, bytesRead));
                }
                response.setBody(bodyBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatusCode(500);
                response.setBody("Internal Server Error");
            }
        } else {
            response.setStatusCode(404);
            response.setBody("File Not Found");
        }
        response.send(outputStream);
    }

    private String getContentType(String filePath) {
        if (filePath.endsWith(".html")) {
            return "text/html";
        } else if (filePath.endsWith(".css")) {
            return "text/css";
        } else if (filePath.endsWith(".js")) {
            return "application/javascript";
        } else if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (filePath.endsWith(".png")) {
            return "image/png";
        } else {
            return "application/octet-stream";
        }
    }
}