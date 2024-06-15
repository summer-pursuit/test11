package main.java.com.mywebserver;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 29753
 */
public class HttpResponse {
    private int statusCode;
    private String statusMessage;
    private final Map<String, String> headers = new HashMap<>();
    private String body;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        this.statusMessage = getStatusMessage(statusCode);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void send(OutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        String fullResponse = getFullResponse();
        System.out.println(fullResponse); // 输出完整的响应报文到控制台
        writer.print(fullResponse);
        writer.flush();
    }

    private String getFullResponse() {
        StringBuilder responseBuilder = new StringBuilder();
        String version = "HTTP/1.1";
        responseBuilder.append(version).append(" ").append(statusCode).append(" ").append(statusMessage).append("\r\n");
        for (Map.Entry<String, String> header : headers.entrySet()) {
            responseBuilder.append(header.getKey()).append(": ").append(header.getValue()).append("\r\n");
        }
        responseBuilder.append("\r\n");
        if (body != null) {
            responseBuilder.append(body);
        }
        return responseBuilder.toString();
    }

    private String getStatusMessage(int statusCode) {
        return switch (statusCode) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            case 403 -> "Forbidden";
            default -> "Unknown";
        };
    }
}