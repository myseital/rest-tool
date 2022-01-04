package com.mao.interview.io.aio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author myseital
 * @date 2021/4/1 22:44
 */
public class AioClient {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        int port = 8888;
        // Socket 客户端
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        Future<Void> future = client.connect(new InetSocketAddress(InetAddress.getLocalHost(), port));
        future.get();
        ByteBuffer buffer = ByteBuffer.allocate(100);
        client.read(buffer, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                System.out.println("客户端打印：" + new String(buffer.array()));
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(10 * 1000);
    }
}
