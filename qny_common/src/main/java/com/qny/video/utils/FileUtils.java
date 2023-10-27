package com.qny.video.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.function.Consumer;

/**
 * 文件工具类
 * @author ttpfx
 * @since 2023/10/27
 */
public class FileUtils {
    /**
     * 递归操作文件
     * @param folderPath 文件夹路径
     * @param task 对文件进行的操作
     */
    public static void readFiles(String folderPath, Consumer<Path> task){
        // 指定要遍历的目录路径
        Path startDir = Paths.get(folderPath);
        // 调用walkFileTree方法遍历目录和子目录
        try {
            Files.walkFileTree(startDir, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new FileVisitor<Path>() {

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    task.accept(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
