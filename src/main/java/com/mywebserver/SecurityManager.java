package main.java.com.mywebserver;

import main.java.com.mywebserver.Config;

import java.util.HashSet;
import java.util.Set;

public class SecurityManager {
    private static Set<String> allowedIPs = new HashSet<>();

    static {
        // 从配置文件中读取允许的IP列表
        String ips = Config.getProperty("security.allowedIPs", "127.0.0.1");
        for (String ip : ips.split(",")) {
            allowedIPs.add(ip.trim());
        }
    }

    public static boolean isSecure(HttpRequest request) {
        String clientIP = request.getClientIP();
        if ("0:0:0:0:0:0:0:1".equals(clientIP)) {
            clientIP = "127.0.0.1"; // 将IPv6本地回环地址转换为IPv4
        }
        System.out.println("Client IP: " + clientIP); // 输出客户端IP地址进行调试
        return allowedIPs.contains(clientIP);
    }
}