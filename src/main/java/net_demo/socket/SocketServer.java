package net_demo.socket;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer {
    /** 日志 */
    public final static Logger logger = (Logger) LoggerFactory.getLogger(SocketServer.class);

    public static void main(String[] args) {
        // 服务器监听的端口号
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器已启动，正在监听端口:[{}] ", port);
            while (true) {
                try {
                    // 等待客户端连接
                    Socket clientSocket = serverSocket.accept();
                    logger.info("连接的远程客户端地址:[{}] ", clientSocket.getInetAddress().getHostAddress());
                    // 处理客户端连接
                    new Thread(() -> handleClient(clientSocket)).start();
                } catch (IOException e) {
                    logger.info("服务器异常000,异常信息：[{}]", e.getMessage());
                    break;
                }
            }
        } catch (IOException e) {
            logger.info("服务器异常111,异常信息：[{}]", e.getMessage());
        }
    }

    /**
     * 处理客户端的连接事件
     *
     * @param clientSocket 连接客户端
     */
    private static void handleClient(Socket clientSocket) {
        try (
                // 输入流
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // 输出流
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine;
            // 会阻塞
            while ((inputLine = in.readLine()) != null) {
                logger.info("接收到客户端消息:[{}] ", inputLine);
                // 回复客户端
                out.println("服务器已收到: " + inputLine);
            }
        } catch (IOException e) {
            logger.info("服务器异常222,异常信息：[{}]", e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                logger.info("服务器异常333,异常信息：[{}]", e.getMessage());
            }
        }
    }
}
