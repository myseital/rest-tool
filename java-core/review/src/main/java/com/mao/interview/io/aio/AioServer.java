package com.mao.interview.io.aio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author myseital
 * @date 2021/4/1 22:44
 */
public class AioServer {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        int port = 8888;
        new Thread(new Runnable() {
            @Override
            public void run() {
                AsynchronousChannelGroup group = null;
                try {
                    group = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(4));
                    AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group)
                            .bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
                    server.accept(null, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
                        @Override
                        public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {
                            server.accept(null, this); // 接收下一个请求
                            try {
                                Future<Integer> f = result.write(Charset.defaultCharset().encode("Hi, 老王"));
                                f.get();
                                System.out.println("服务端发送时间：" + DateFormat.getDateTimeInstance().format(new Date()));
                                result.close();
                            } catch (InterruptedException | ExecutionException | IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, AsynchronousServerSocketChannel
                                attachment) {
                        }
                    });
                    group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(10 * 1000);
    }
}
