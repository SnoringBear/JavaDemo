package net_demo.socket;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
    /** 日志 */
    public static final Logger logger = (Logger) LoggerFactory.getLogger(SocketClient.class);

    public static void main(String[] args) {
        // 服务器的主机名或IP地址
        String serverName = "localhost";
        // 服务器监听的端口号
        int port = 12345;

        try (Socket clientSocket = new Socket(serverName, port);
             // 输入流
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             // 输出流
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             // 等待用户输入
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            logger.info("已连接到服务器：[{}] ,在端口:[{}]",serverName,port);

            // 从标准输入读取用户输入，并发送到服务器
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                // 发送数据到服务器
                out.println(userInput);
                if ("exit".equalsIgnoreCase(userInput)) {
                    // 如果用户输入"exit"，则断开连接
                    break;
                }
                // 从服务器接收响应并打印到标准输出
                String response = in.readLine();
                logger.info("服务器响应：[{}]",response);
            }
        } catch (UnknownHostException e) {
            logger.error("无法找到服务器:[{}]",e.getMessage());
        } catch (IOException e) {
            logger.error("I/O错误:[{}]",e.getMessage());
        }
    }
}
