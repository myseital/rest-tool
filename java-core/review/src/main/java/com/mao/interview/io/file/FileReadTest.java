package com.mao.interview.io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author myseital
 * @date 2021/4/1 23:04
 */
public class FileReadTest {
    public static void main(String[] args) throws IOException {

        // 16 * 1024
        System.out.println(16384/ 1024);
        // 读取文件
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/myseital/Desktop/io.txt"));
        // 写入文件
        Files.write(Paths.get("/Users/myseital/Desktop/io.txt"), "追加内容".getBytes(), StandardOpenOption.APPEND);
    }
}
