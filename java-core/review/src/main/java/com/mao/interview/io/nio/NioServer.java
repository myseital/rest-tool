package com.mao.interview.io.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author myseital
 * @date 2021/4/1 22:26
 */
public class NioServer {
    public static void main(String[] args) {
        // 以上代码创建了两个 Socket 客户端，用于收取和打印服务器端的消息。
        // 其中，服务器端通过 SelectionKey（选择键）获取到 SocketChannel（通道），
        // 而通道都注册到 Selector（选择器）上，所有的客户端都可以获得对应的通道，
        // 而不是所有客户端都排队堵塞等待一个服务器连接，这样就实现多路复用的效果了。
        // 多路指的是多个通道（SocketChannel），而复用指的是一个服务器端连接重复被不同的客户端使用。
        int port = 6666;
        new Thread(() -> {
            try (Selector selector = Selector.open();
                 ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();) {
                serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                while (true) {
                    selector.select(); // 阻塞等待就绪的 Channel
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        try (SocketChannel channel = ((ServerSocketChannel) key.channel()).accept()) {
                            channel.write(Charset.defaultCharset().encode("老王，你好~ "));
                        }
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
