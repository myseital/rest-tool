package com.mao.interview.io.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author myseital
 * @date 2021/4/1 22:49
 */
public class NioClient2 {

    public static void main(String[] args) {
        int port = 6666;
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                // Socket 客户端 2（接收信息并打印）
                try (Socket cSocket = new Socket(InetAddress.getLocalHost(), port)) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
                    bufferedReader.lines().forEach(s -> System.out.println("客户端 2 打 印：" + s + finalI));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
