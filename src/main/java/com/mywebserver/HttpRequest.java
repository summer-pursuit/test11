package main.java.com.mywebserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 29753
 */
public class HttpRequest {
    private String method;
    private String path;
    private String version;
    private final Map<String, String> headers = new HashMap<>();
    private String body;
    private String clientIP;

    public HttpRequest(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        if (line != null && !line.isEmpty()) {
            String[] requestLine = line.split(" ");
            if (requestLine.length == 3) {
                method = requestLine[0];
                path = requestLine[1];
                version = requestLine[2];
            }

            while (!(line = reader.readLine()).isEmpty()) {
                String[] header = line.split(": ", 2);
                if (header.length == 2) {
                    headers.put(header[0], header[1]);
                }
            }

            if ("POST".equalsIgnoreCase(method)) {
                StringBuilder bodyBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    bodyBuilder.append(line);
                }
                body = bodyBuilder.toString();
            }
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getVersion() {
        return version;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }
}